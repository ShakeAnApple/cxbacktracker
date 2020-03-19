package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.DiagramExecutor;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.Clocks;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.DelayFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Block;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.Parser;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FunctionBlockComplex extends FunctionBlockBase {

    private Diagram internalDiagram;

    public FunctionBlockComplex(String name, String type, List<InputVariable> inputs, List<OutputVariable> outputs, Diagram internalDiagram) {
        super(name, type, inputs, outputs);

        this.internalDiagram = internalDiagram;
        this.getClocks().onTick(this::tickChildrenTime);
        this.getClocks().onTick(this::releaseDelays);
        this.getClocks().onTick(this::releaseUnconnectedBlocks);
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

    public FunctionBlockBase extractInternal(LinkedList<String> blockPath){
        FunctionBlockBase parent = this;

        for (String s : blockPath) {
            if (parent instanceof FunctionBlockComplex) {
                parent = this.findBlock((FunctionBlockComplex) parent, s);
            } else {
                throw new RuntimeException("Can't search in basic block");
            }
        }
        return parent;
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

    @Override
    public FunctionBlockBase clone() {
//        List<InputGate> inGates = inputs.stream().map(in -> new InputGate(in, this)).collect(Collectors.toList());
//        List<OutputGate> outGates = outputs.stream().map(out -> new OutputGate(out, this)).collect(Collectors.toList());
        List<InputVariable> ins = this.fbInterface().getInputs().values().stream().map(inputGate -> inputGate.input().clone()).collect(Collectors.toList());
        List<OutputVariable> outs = this.fbInterface().getOutputs().values().stream().map(outputGate -> outputGate.output().clone()).collect(Collectors.toList());

        Diagram diagramClone = this.internalDiagram.clone();
        FunctionBlockComplex fbCloned = new FunctionBlockComplex(this.getName(), this.getType(),
                ins,
                outs,
                diagramClone
                );
        Map<String, FunctionBlockBase> internalBlocksCloned = diagramClone.getFunctionBlocks().stream().collect(Collectors.toMap(FunctionBlockBase::getName, fb -> fb));
        for (InputGate inGate: this.fbInterface().getInputs().values()){
            for (Connection conn: inGate.getOutgoingConnections()){
                DiagramElement from = fbCloned.fbInterface().getInputs().get(inGate.getName());
                InputGate fromGate = fbCloned.fbInterface().getInputs().get(inGate.getName());
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
                DiagramElement to = fbCloned.fbInterface().getOutputs().get(outGate.getName());
                OutputGate toGate = fbCloned.fbInterface().getOutputs().get(outGate.getName());
                fromGate.makeOutgoingConnection(toGate, from, to, conn.isInverted());
                toGate.makeIncomingConnection(fromGate, from, to, conn.isInverted());
        }

        return fbCloned;
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
