package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph.CauseFinalNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph.CausePathFinalGraph;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangeStayedExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangedStayedCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangedStayedCausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.DelayFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.CausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeCausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;
//import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.Parser;
import shakeanapple.backtracker.parser.fblockdiagramnew.Parser;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunctionBlockComplex extends FunctionBlockBase {

    private Diagram internalDiagram;

    public FunctionBlockComplex(String name, String type, List<InputVariable> inputs, List<OutputVariable> outputs, Diagram internalDiagram, String pathInSystem) {
        super(name, type, inputs, outputs, pathInSystem);

        this.internalDiagram = internalDiagram;
        this.internalDiagram.setOwner(this);
        this.getClocks().onTick(this::tickChildrenTime);
        this.getClocks().onTick(this::releaseDelays);
        this.getClocks().onTick(this::releaseUnconnectedBlocks);
        this.getClocks().onTick(this::releaseInternalConstGates);
    }

    private void releaseInternalConstGates() {
        for (FunctionBlockBase fb : this.internalDiagram.getFunctionBlocks()) {
            for (InputGate inGate : fb.fbInterface().getInputs().values()) {
                if (inGate.getIncomingConnection() == null) {
                    inGate.propagateValue();
                }
            }
        }
    }

    private void tickChildrenTime() {
        for (FunctionBlockBase fb : this.internalDiagram.getFunctionBlocks()) {
            fb.tickSystemTime();
        }
    }

    private void releaseUnconnectedBlocks() {
        for (FunctionBlockBase fb : this.internalDiagram.getFunctionBlocks()) {
            if (fb.fbInterface().getInputs().values().stream().noneMatch(i -> i.getIncomingConnection() != null)) {
                //System.out.println(fb.getName() + ": no connected inputs, executed");
                fb.execute();
            }
        }
    }

    private void releaseDelays() {
        this.internalDiagram.getFunctionBlocks().stream().filter(fb -> fb instanceof DelayFunctionBlockBasic).forEach(fb -> {
            DelayFunctionBlockBasic delayFb = (DelayFunctionBlockBasic) fb;
            if (fb.fbInterface().getInputs().values().stream().anyMatch(i -> i.getIncomingConnection() != null) &&
                    !this.fbInterface().getInputs().containsKey(delayFb.fbInterface().getInputs().get(delayFb.getInput().getName()).getName())) {
                // System.out.println(fb.getName() + ": delay, executed");
                fb.execute();
            }
        });
    }

    public FunctionBlockBase extractInternal(List<String> blockPath) {
        FunctionBlockBase parent = this;

        for (String s : blockPath) {
            if (parent instanceof FunctionBlockComplex) {
                parent = this.findBlock((FunctionBlockComplex) parent, s);
            } else {
                throw new RuntimeException("Can't search in basic block");
            }
        }
        return parent.clone();
    }

    public FunctionBlockBase extractInternal(String blockName) {
        FunctionBlockBase block = this.internalDiagram.getFunctionBlocks().stream().filter(fb -> fb.getName().equals(blockName)).findFirst().orElse(null);
        return block.clone();
    }

    private FunctionBlockBase findBlock(FunctionBlockComplex parent, String name) {
        return parent.internalDiagram.getFunctionBlocks().stream().filter(fb -> fb.getName().equals(name)).findFirst().orElse(null);
    }

    public Diagram getInternalDiagram() {
        return this.internalDiagram;
    }

    // FIXME something weird
    @Override
    public void executeImpl() {
        this.internalDiagram.execute();
    }

    @Override
    protected ExplanationItem explainImpl(OutputGate output, Integer timestamp) {
        if (output.getIncomingConnection() == null) {
            return this.emptyExplanation(output, timestamp);
        }

        OutputGate gateToExplain = (OutputGate) output.getIncomingConnection().fromGate();

        CausePathTree causesTree = new CausePathTree();

        CauseNode rootNode = new CauseNode(output, this.history().getVariableValueForStep(output.getName(), timestamp), timestamp);
        causesTree.addRoot(rootNode);

        ExplanationItem item = this.internalDiagram.explain(gateToExplain, timestamp);
        Collection<CauseNode> causeNodes = item.getFreshNodes();
        rootNode.addChildren(item.getTree().getRoots());
        Set<CauseNode> outputCauseNodes = new HashSet<>();

        ExplanationItem result = new ExplanationItem(causesTree, outputCauseNodes);
        for (CauseNode causeNode : causeNodes) {
            if (this.fbInterface().getInputs().get(causeNode.getGate().getName()) != null &&
                    this.fbInterface().getInputs().get(causeNode.getGate().getName()).equals(causeNode.getGate())
            ) {
                CauseNode childNode = causeNode;
                outputCauseNodes.add(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                ExplanationItem childItem = this.explain((OutputGate) causeNode.getGate(), causeNode.getTimestamp());
                outputCauseNodes.addAll(childItem.getFreshNodes());
                causeNode.addChildren(childItem.getFreshNodes());
            }
        }

        return result;
    }

    @Override
    protected ChangeExplanationItem explainChangeImpl(OutputGate output, Integer timestamp) {
        if (output.getIncomingConnection() == null) {
            return this.emptyChangeExplanation(output, timestamp);
        }

        OutputGate gateToExplain = (OutputGate) output.getIncomingConnection().fromGate();

        ChangeCausePathTree causesTree = new ChangeCausePathTree();

        ChangeCauseNode rootNode = new ChangeCauseNode(output, this.history().getVariableValueForStep(output.getName(), timestamp), timestamp,
                this.history().getVariableValueForStep(output.getName(), timestamp), timestamp);
        causesTree.addRoot(rootNode);

        ChangeExplanationItem item = this.internalDiagram.explainChange(gateToExplain, timestamp);
        Collection<ChangeCauseNode> causeNodes = item.getFreshNodes();
        rootNode.addChildren(item.getTree().getRoots());
        Set<ChangeCauseNode> outputCauseNodes = new HashSet<>();

        ChangeExplanationItem result = new ChangeExplanationItem(causesTree, outputCauseNodes);
        for (ChangeCauseNode causeNode : causeNodes) {
            if (this.fbInterface().getInputs().get(causeNode.getGate().getName()) != null &&
                    this.fbInterface().getInputs().get(causeNode.getGate().getName()).equals(causeNode.getGate())
            ) {
                ChangeCauseNode childNode = causeNode;
                outputCauseNodes.add(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                ChangeExplanationItem childItem = this.explainChange((OutputGate) causeNode.getGate(), causeNode.getChange().getCurrentStep());
                outputCauseNodes.addAll(childItem.getFreshNodes());
                causeNode.addChildren(childItem.getFreshNodes());
            }
        }

        return result;
    }

    @Override
    protected ChangeExplanationItem explainHistoryChangeImpl(OutputGate output, Integer timestamp) {
        if (output.getIncomingConnection() == null) {
            return this.emptyChangeExplanation(output, timestamp);
        }

        OutputGate gateToExplain = (OutputGate) output.getIncomingConnection().fromGate();

        ChangeCausePathTree causesTree = new ChangeCausePathTree();

        ChangeCauseNode rootNode = new ChangeCauseNode(output, this.history().getVariableValueForStep(output.getName(), timestamp), timestamp,
                this.history().getVariableValueForStep(output.getName(), timestamp), timestamp);
        causesTree.addRoot(rootNode);

        ChangeExplanationItem item = this.internalDiagram.explainHistoryChange(gateToExplain, timestamp);
        Collection<ChangeCauseNode> causeNodes = item.getFreshNodes();
        rootNode.addChildren(item.getTree().getRoots());
        Set<ChangeCauseNode> outputCauseNodes = new HashSet<>();

        ChangeExplanationItem result = new ChangeExplanationItem(causesTree, outputCauseNodes);
        for (ChangeCauseNode causeNode : causeNodes) {
            if (this.fbInterface().getInputs().get(causeNode.getGate().getName()) != null &&
                    this.fbInterface().getInputs().get(causeNode.getGate().getName()).equals(causeNode.getGate())
            ) {
                ChangeCauseNode childNode = causeNode;
                outputCauseNodes.add(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                ChangeExplanationItem childItem = this.explainHistoryChange((OutputGate) causeNode.getGate(), causeNode.getChange().getCurrentStep());
                outputCauseNodes.addAll(childItem.getFreshNodes());
                causeNode.addChildren(childItem.getFreshNodes());
            }
        }

        return result;
    }

    @Override
    protected ChangeStayedExplanationItem explainHistoryStayedChangedImpl(OutputGate output, Integer timestamp, Map<String, ChangeStayedExplanationItem> processedNodes) {
        if (processedNodes.containsKey(this.getStringHash(output, timestamp))) {
            return processedNodes.get(this.getStringHash(output, timestamp));
        }

        if (output.getIncomingConnection() == null) {
            return this.emptyStayedChangeExplanation(output, timestamp);
        }

        ChangedStayedCausePathTree causesTree = new ChangedStayedCausePathTree();

        ChangedStayedCauseNode rootNode = new ChangedStayedCauseNode(output, this.history().getVariableValueForStep(output.getName(), timestamp), timestamp,
                this.history().getVariableValueForStep(output.getName(), timestamp), timestamp, timestamp);

        causesTree.addRoot(rootNode);

        OutputGate gateToExplain = (OutputGate) output.getIncomingConnection().fromGate();
        ChangeStayedExplanationItem item = this.internalDiagram.explainHistoryStayedChanged(gateToExplain, timestamp, processedNodes);

        if (!processedNodes.containsKey(this.getStringHash(gateToExplain, timestamp))) {
            processedNodes.put(this.getStringHash(gateToExplain, timestamp), item);
        }

        Collection<ChangedStayedCauseNode> causeNodes = item.getFreshNodes();
        rootNode.addChildren(item.getTree().getRoots());
        List<ChangedStayedCauseNode> outputCauseNodes = new ArrayList<>();

        ChangeStayedExplanationItem result = new ChangeStayedExplanationItem(causesTree, outputCauseNodes);
        for (ChangedStayedCauseNode causeNode : causeNodes) {
            if (this.fbInterface().getInputs().get(causeNode.getGate().getName()) != null &&
                    this.fbInterface().getInputs().get(causeNode.getGate().getName()).equals(causeNode.getGate())
            ) {
                ChangedStayedCauseNode childNode = causeNode;
                outputCauseNodes.add(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                ChangeStayedExplanationItem childItem = this.explainHistoryStayedChanged((OutputGate) causeNode.getGate(), causeNode.getChange().getNewStep(), processedNodes);
                if (!processedNodes.containsKey(this.getStringHash(causeNode.getGate(), causeNode.getChange().getNewStep()))) {
                    processedNodes.put(this.getStringHash(causeNode.getGate(), causeNode.getChange().getNewStep()), childItem);
                }

                outputCauseNodes.addAll(childItem.getFreshNodes());
                causeNode.addChildren(childItem.getFreshNodes());
            }
        }
        item.getFreshNodes().clear();

        return result;
    }

    private ChangeStayedExplanationItem emptyStayedChangeExplanation(OutputGate output, int timestamp) {
        ChangedStayedCausePathTree causesTree = new ChangedStayedCausePathTree();
        ChangedStayedCauseNode rootNode = new ChangedStayedCauseNode(output, this.history().getVariableValueForStep(output.getName(), timestamp), timestamp,
                this.history().getVariableValueForStep(output.getName(), timestamp), timestamp, timestamp);
        causesTree.addRoot(rootNode);
        ChangeStayedExplanationItem result = new ChangeStayedExplanationItem(causesTree, new HashSet<>());
        return result;
    }

    private String getStringHash(Gate gate, int timestamp) {
        return gate.getFullName() + timestamp;
    }

    private ExplanationItem emptyExplanation(OutputGate output, int timestamp) {
        CausePathTree causesTree = new CausePathTree();
        CauseNode rootNode = new CauseNode(output, this.history().getVariableValueForStep(output.getName(), timestamp), timestamp);
        causesTree.addRoot(rootNode);
        ExplanationItem result = new ExplanationItem(causesTree, new HashSet<>());
        return result;
    }

    private ChangeExplanationItem emptyChangeExplanation(OutputGate output, int timestamp) {
        ChangeCausePathTree causesTree = new ChangeCausePathTree();
        ChangeCauseNode rootNode = new ChangeCauseNode(output, this.history().getVariableValueForStep(output.getName(), timestamp), timestamp,
                this.history().getVariableValueForStep(output.getName(), timestamp), timestamp);
        causesTree.addRoot(rootNode);
        ChangeExplanationItem result = new ChangeExplanationItem(causesTree, new HashSet<>());
        return result;
    }

    @Override
    public FunctionBlockBase clone() {
        return this.copy(this.getName());
    }

    @Override
    public List<CauseFinalNode> explainFinal(Gate gate, int timestamp, CauseFinalNode parent, CausePathFinalGraph graph) {
        // it's either input gate of internal block connected to input interface or constant
        if (gate instanceof InputGate) {
            if (gate.getIncomingConnection() != null && gate.getIncomingConnection().fromGate() instanceof InputGate) {
                // connected to system input
                BlockVariableHistoryItem lastChange = gate.getOwner().history().lastChangeForVarBeforeStep(gate.getName(), timestamp);
                CauseFinalNode root = new CauseFinalNode(gate, gate.getOwner().history().getVariableValueForStep(gate.getName(), timestamp), timestamp, graph, parent,
                        lastChange.getValueHolder(), lastChange.getTimestamp());
//                CauseFinalNode root = new CauseFinalNode(gate, gate.getOwner().history().getVariableValueForStep(gate.getName(), timestamp), timestamp, parent, graph);
                Gate childGate = gate.getIncomingConnection().fromGate();
                CauseFinalNode child = new CauseFinalNode(childGate, childGate.getOwner().history().getVariableValueForStep(childGate.getName(), timestamp), timestamp, root, graph);
                return new ArrayList<>() {{
                    add(child);
                }};
            } else if (gate.getIncomingConnection() == null) {
                //const in the internal diagram
                if (!this.fbInterface().getInputs().containsKey(gate.getName())) {
                    CauseFinalNode cause = new CauseFinalNode(gate, gate.getOwner().history().getVariableValueForStep(gate.getName(), timestamp), timestamp, parent, graph);
                    return new ArrayList<>() {{
                        add(cause);
                    }};
                }
                //const OR var in the interface diagram
                BlockVariableHistoryItem lastChange = gate.getOwner().history().lastChangeForVarBeforeStep(gate.getName(), timestamp);
                CauseFinalNode cause = new CauseFinalNode(gate, gate.getOwner().history().getVariableValueForStep(gate.getName(), timestamp), timestamp, graph, parent,
                        lastChange.getValueHolder(), lastChange.getTimestamp());
//                CauseFinalNode cause = new CauseFinalNode(gate, this.history().getVariableValueForStep(gate.getName(), timestamp), timestamp, parent, graph);
                return new ArrayList<>() {{
                    add(cause);
                }};
            }
        }

        List<CauseFinalNode> newNodes = new ArrayList<>();
        // or output of this diagram or some input of one of the internal blocks worth explaining
        // and it's an explanation target
        if (parent == null) {
            BlockVariableHistoryItem lastChange = gate.getOwner().history().lastChangeForVarBeforeStep(gate.getName(), timestamp);
            int[] stepsToExplain;
            if (lastChange.getTimestamp() == timestamp){
                stepsToExplain = IntStream.range(1,timestamp + 1).toArray();
            } else{
                stepsToExplain = IntStream.range(lastChange.getTimestamp() + 1, timestamp + 1).toArray();
            }
            // value stayed for some time
            if (stepsToExplain.length > 1) {
                CauseFinalNode root = new CauseFinalNode(gate, gate.getOwner().history().getVariableValueForStep(gate.getName(), timestamp), timestamp,
                        graph, null,
                        lastChange.getValueHolder(), lastChange.getTimestamp());
                root.isTimeNode(true);
                for (int step : stepsToExplain) {
                    newNodes.addAll(this.explainFinal(gate, step, root, graph));
                }
            } else {
                newNodes = this.getInternalDiagram().explainFinal(gate, timestamp, parent, graph);
            }
        } else {
            newNodes = this.getInternalDiagram().explainFinal(gate, timestamp, parent, graph);
        }

        if (parent != null && !parent.isTimeNode()) {
            this.mergeInternalNodes(graph, gate, timestamp, this, parent == null);
        } else if (this.internalDiagram.getFunctionBlocks().stream().anyMatch(fb -> fb.equals(gate.getOwner())) && !(gate instanceof InputGate)) {
            this.mergeInternalNodes(graph, gate, timestamp, this.internalDiagram.getFunctionBlocks().stream().filter(fb -> fb.equals(gate.getOwner())).findFirst().get(), parent == null);
        }
        return newNodes;
    }

    // If the gate preserved its value for several steps, explain for all of them
    private int[] getStepsToExplain(Gate gate, int timestamp) {
        ValueHolder val = gate.getOwner().history().getVariableValueForStep(gate.getName(), timestamp);
        Map<Integer, BlockVariableHistoryItem> gateHistory = gate.getOwner().history().ofVariable(gate.getName());
        int changeStep = 1;
        for (int step = timestamp - 1; step > 0; step--) {
            final BlockVariableHistoryItem gateHistoryItem = gateHistory.get(step);
            if (!gateHistoryItem.getValueHolder().getValue().equals(val.getValue())) {
                // explain the next one after the one with different value
                changeStep = step + 1;
                break;
            }
        }
        return IntStream.range(changeStep, timestamp + 1).toArray();
    }

    private void mergeInternalNodes(CausePathFinalGraph resultGraph, Gate explainedGate, int timestamp, FunctionBlockBase blockContext, boolean isRoot) {
        CauseFinalNode root = resultGraph.causeNodeFor(explainedGate, timestamp, isRoot);
        Set<CauseFinalNode> newChildren = this.findNewChildren(root, blockContext);
        root.clearChildren();
        root.addChildren(newChildren);
    }

    private Set<CauseFinalNode> findNewChildren(CauseFinalNode node, FunctionBlockBase blockContext) {
        Set<CauseFinalNode> newChildren = new HashSet<>();
        for (CauseFinalNode child : node.getChildren()) {
            if (this.gateBelongsToDiagram(child.getGate(), blockContext)) {
                newChildren.add(child);
            } else {
                newChildren.addAll(this.findNewChildren(child, blockContext));
            }
        }
        return newChildren;
    }

    private boolean gateBelongsToDiagram(Gate gate, FunctionBlockBase blockContext) {
//        return this.internalDiagram.getFunctionBlocks().stream().anyMatch(fb -> fb.equals(gate.getOwner()))
        return blockContext.fbInterface().getInputs().values().stream().anyMatch(in -> in.getFullName().equals(gate.getFullName()))
                || blockContext.fbInterface().getOutputs().values().stream().anyMatch(in -> in.getFullName().equals(gate.getFullName()));
    }


    public FunctionBlockBase copy(String newBlockName) {
        List<InputVariable> ins = this.fbInterface().getInputs().values().stream().map(inputGate -> inputGate.input().clone()).collect(Collectors.toList());
        List<OutputVariable> outs = this.fbInterface().getOutputs().values().stream().map(outputGate -> outputGate.output().clone()).collect(Collectors.toList());

        Diagram diagramClone = this.internalDiagram.clone();
        FunctionBlockComplex fbCopied = new FunctionBlockComplex(newBlockName, this.getType(), ins, outs, diagramClone, this.getStringPathInSystem());
        Map<String, FunctionBlockBase> internalBlocksCloned = diagramClone.getFunctionBlocks().stream().collect(Collectors.toMap(FunctionBlockBase::getName, fb -> fb));
        for (InputGate inGate : this.fbInterface().getInputs().values()) {
            for (Connection conn : inGate.getOutgoingConnections()) {
                DiagramElement from = fbCopied.fbInterface().getInputs().get(inGate.getName());
                InputGate fromGate = fbCopied.fbInterface().getInputs().get(inGate.getName());
                DiagramElement to = internalBlocksCloned.get(conn.to().getName());
                InputGate toGate = ((FunctionBlockBase) to).fbInterface().getInputs().get(conn.toGate().getName());
                fromGate.makeOutgoingConnection(toGate, from, to, conn.isInverted());
                toGate.makeIncomingConnection(fromGate, from, to, conn.isInverted());
            }
        }

        for (OutputGate outGate : this.fbInterface().getOutputs().values()) {
            Connection conn = outGate.getIncomingConnection();
            DiagramElement from = internalBlocksCloned.get(conn.from().getName());
            OutputGate fromGate = ((FunctionBlockBase) from).fbInterface().getOutputs().get(conn.fromGate().getName());
            DiagramElement to = fbCopied.fbInterface().getOutputs().get(outGate.getName());
            OutputGate toGate = fbCopied.fbInterface().getOutputs().get(outGate.getName());
            fromGate.makeOutgoingConnection(toGate, from, to, conn.isInverted());
            toGate.makeIncomingConnection(fromGate, from, to, conn.isInverted());
        }

        return fbCopied;
    }

    public static FunctionBlockComplex parse(String path) {
        try {
            Parser p = new Parser(path);
            return p.parse();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
