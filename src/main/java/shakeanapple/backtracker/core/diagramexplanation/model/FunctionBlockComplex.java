package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.Clocks;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.DelayFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.Parser;
import shakeanapple.backtracker.ui.explainer.model.Cause;

import java.io.IOException;
import java.util.*;

public class FunctionBlockComplex extends FunctionBlockBase {

    private Diagram internalDiagram;

    public FunctionBlockComplex(String name, String type, List<InputVariable> inputs, List<OutputVariable> outputs, Diagram internalDiagram) {
        super(name, type, inputs, outputs);

        this.internalDiagram = internalDiagram;
        Clocks.instance().onTick(this::releaseDelays);
        Clocks.instance().onTick(this::releaseUnconnectedBlocks);
    }

    private void releaseUnconnectedBlocks() {
        for (FunctionBlockBase fb: this.internalDiagram.getFunctionBlocks()){
            if (fb.fbInterface().getInputs().values().stream().noneMatch(i -> i.getIncomingConnection() != null)){
                System.out.println(fb.getName() + ": no connected inputs, executed");
                fb.execute();
            }
        }
    }

    private void releaseDelays() {
        this.internalDiagram.getFunctionBlocks().stream().filter(fb -> fb instanceof DelayFunctionBlockBasic).forEach(fb -> {
            DelayFunctionBlockBasic delayFb = (DelayFunctionBlockBasic) fb;
            if (fb.fbInterface().getInputs().values().stream().anyMatch(i -> i.getIncomingConnection() != null) &&
                    !this.fbInterface().getInputs().containsKey(delayFb.fbInterface().getInputs().get(delayFb.getInput().getName()).getName())) {
                System.out.println(fb.getName() + ": delay, executed");
                fb.execute();
            }
        });
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
        System.out.println(String.format("Upper Block: %s, Gate: %s, Timestamp: %s", this.getName(), output.getName(), timestamp));
        OutputGate gateToExplain = (OutputGate) output.getIncomingConnection().fromGate();

        CausePathTree causesTree = new CausePathTree();
        CauseNode rootNode = new CauseNode(output, output.getValue(), timestamp);
        causesTree.addRoot(rootNode);

//        CauseNode internalDiagramChildNode = new CauseNode(gateToExplain, gateToExplain.getValue(), timestamp);
//        rootNode.addChildNode(internalDiagramChildNode);

        ExplanationItem item = this.internalDiagram.explain(gateToExplain, timestamp);
        Collection<CauseNode> causeNodes = item.getFreshNodes();
        rootNode.addChildren(item.getTree().getRoots());
        Set<CauseNode> outputCauseNodes = new HashSet<>();

        ExplanationItem result = new ExplanationItem(causesTree, outputCauseNodes);
        for (CauseNode causeNode : causeNodes) {
            if (this.fbInterface().getInputs().get(causeNode.getGate().getName()) != null &&
                    this.fbInterface().getInputs().get(causeNode.getGate().getName()).equals(causeNode.getGate())
            ) {
                System.out.println(String.format("InternalD: cause '%s' added to result", causeNode.getGate().getName()));
                //CauseNode childNode = new CauseNode(causeNode.getGate(), causeNode.getValue(), causeNode.getTimestamp());
                CauseNode childNode = causeNode;
//                causeNode.addChildNode(childNode);
                outputCauseNodes.add(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                System.out.println(String.format("InternalD: cause '%s' will be processed", causeNode.getGate().getName()));
                ExplanationItem childItem = this.explain((OutputGate) causeNode.getGate(), causeNode.getTimestamp());
                outputCauseNodes.addAll(childItem.getFreshNodes());
                causeNode.addChildren(childItem.getFreshNodes());
                System.out.println(String.format("InternalD: cause '%s' processed", causeNode.getGate().getName()));
            }
        }

//        internalDiagramChildNode.addChildren(outputCauseNodes);
        System.out.println(String.format("Upper Block Processed: %s, Gate: %s, Timestamp: %s", this.getName(), output.getName(), timestamp));
//        return new ArrayList<>(outputCauseNodes);
        return result;
    }

    public static FunctionBlockComplex parse(String path, String blockDefsPath) {
//        try {
//            Parser p = new Parser(path, blockDefsPath);
//            return p.parse();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
        return null;
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
