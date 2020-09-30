package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.DelayFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;
//import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.Parser;
import shakeanapple.backtracker.parser.fblockdiagramnew.Parser;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FunctionBlockComplex extends FunctionBlockBase {

    private Diagram internalDiagram;

    public FunctionBlockComplex(String name, String type, List<InputVariable> inputs, List<OutputVariable> outputs, Diagram internalDiagram, String pathInSystem) {
        super(name, type, inputs, outputs, pathInSystem);

        this.internalDiagram = internalDiagram;
        this.getClocks().onTick(this::tickChildrenTime);
        this.getClocks().onTick(this::releaseDelays);
        this.getClocks().onTick(this::releaseUnconnectedBlocks);
        this.getClocks().onTick(this::releaseInternalConstGates);
    }

    private void releaseInternalConstGates() {
        for (FunctionBlockBase fb: this.internalDiagram.getFunctionBlocks()){
            for (InputGate inGate: fb.fbInterface().getInputs().values()){
                if (inGate.getIncomingConnection() == null){
                    inGate.propagateValue();
                }
            }
        }
    }

    private void tickChildrenTime() {
        for (FunctionBlockBase fb: this.internalDiagram.getFunctionBlocks()){
            fb.tickSystemTime();
        }
    }

    private void releaseUnconnectedBlocks() {
        for (FunctionBlockBase fb: this.internalDiagram.getFunctionBlocks()){
            if (fb.fbInterface().getInputs().values().stream().noneMatch(i -> i.getIncomingConnection() != null)){
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

    public FunctionBlockBase extractInternal(List<String> blockPath){
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

    public FunctionBlockBase extractInternal(String blockName){
        FunctionBlockBase block = this.internalDiagram.getFunctionBlocks().stream().filter(fb -> fb.getName().equals(blockName)).findFirst().orElse(null);
        return block.clone();
    }

    private FunctionBlockBase findBlock(FunctionBlockComplex parent, String name){
        return parent.internalDiagram.getFunctionBlocks().stream().filter(fb -> fb.getName().equals(name)).findFirst().orElse(null);
    }

    public Diagram getInternalDiagram() {
        return this.internalDiagram;
    }

    @Override
    public void executeImpl() {
        this.internalDiagram.execute();
    }

    @Override
    protected ExplanationItem explainImpl(OutputGate output, Integer timestamp) {
       // System.out.println(String.format("Upper Block: %s, Gate: %s, Timestamp: %s", this.getName(), output.getName(), timestamp));
        if (output.getIncomingConnection() == null){
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
              //  System.out.println(String.format("InternalD: cause '%s' added to result", causeNode.getGate().getName()));
                CauseNode childNode = causeNode;
                outputCauseNodes.add(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
            //    System.out.println(String.format("InternalD: cause '%s' will be processed", causeNode.getGate().getName()));
                ExplanationItem childItem = this.explain((OutputGate) causeNode.getGate(), causeNode.getTimestamp());
                outputCauseNodes.addAll(childItem.getFreshNodes());
                causeNode.addChildren(childItem.getFreshNodes());
            //    System.out.println(String.format("InternalD: cause '%s' processed", causeNode.getGate().getName()));
            }
        }

     //   System.out.println(String.format("Upper Block Processed: %s, Gate: %s, Timestamp: %s", this.getName(), output.getName(), timestamp));
        return result;
    }

    private ExplanationItem emptyExplanation(OutputGate output, int timestamp) {
        CausePathTree causesTree = new CausePathTree();
        CauseNode rootNode = new CauseNode(output, this.history().getVariableValueForStep(output.getName(), timestamp), timestamp);
        causesTree.addRoot(rootNode);
        ExplanationItem result = new ExplanationItem(causesTree, new HashSet<>());
        return result;
    }

    @Override
    public FunctionBlockBase clone() {
        return this.copy(this.getName());
    }

    public FunctionBlockBase copy(String newBlockName){
        List<InputVariable> ins = this.fbInterface().getInputs().values().stream().map(inputGate -> inputGate.input().clone()).collect(Collectors.toList());
        List<OutputVariable> outs = this.fbInterface().getOutputs().values().stream().map(outputGate -> outputGate.output().clone()).collect(Collectors.toList());

        Diagram diagramClone = this.internalDiagram.clone();
        FunctionBlockComplex fbCopied = new FunctionBlockComplex(newBlockName, this.getType(),ins,outs,diagramClone, this.getStringPathInSystem());
        Map<String, FunctionBlockBase> internalBlocksCloned = diagramClone.getFunctionBlocks().stream().collect(Collectors.toMap(FunctionBlockBase::getName, fb -> fb));
        for (InputGate inGate: this.fbInterface().getInputs().values()){
            for (Connection conn: inGate.getOutgoingConnections()){
                DiagramElement from = fbCopied.fbInterface().getInputs().get(inGate.getName());
                InputGate fromGate = fbCopied.fbInterface().getInputs().get(inGate.getName());
                DiagramElement to = internalBlocksCloned.get(conn.to().getName());
                InputGate toGate = ((FunctionBlockBase)to).fbInterface().getInputs().get(conn.toGate().getName());
                fromGate.makeOutgoingConnection(toGate, from, to, conn.isInverted());
                toGate.makeIncomingConnection(fromGate, from, to, conn.isInverted());
            }
        }

        for (OutputGate outGate: this.fbInterface().getOutputs().values()){
            Connection conn = outGate.getIncomingConnection();
            DiagramElement from = internalBlocksCloned.get(conn.from().getName());
            OutputGate fromGate = ((FunctionBlockBase)from).fbInterface().getOutputs().get(conn.fromGate().getName());
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
