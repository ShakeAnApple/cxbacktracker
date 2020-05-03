package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;

import java.util.*;

public class DiagramBackwardExplainer implements DiagramOutputExplainer {

    private FunctionBlockComplex diagram;

    public DiagramBackwardExplainer(FunctionBlockComplex diagram) {
        this.diagram = diagram;
    }

    public ExplanationItem explain(String gateName, String blockName, int timestamp) {
        FunctionBlockBase fbToExplain = this.diagram.getInternalDiagram().getFunctionBlocks().stream()
                .filter(fb -> fb.getName().equals(blockName))
                .findFirst().orElse(null);

        if (fbToExplain == null) {
            OutputGate outputGate = this.diagram.fbInterface().getOutputs().get(gateName);
            if (outputGate != null) {
                ExplanationItem item = this.diagram.explain(outputGate, timestamp);
                return item;
            }
            InputGate inputGate = this.diagram.fbInterface().getInputs().get(gateName);
            if (inputGate != null) {
                return new ExplanationItem(new CausePathTree(Collections.singletonList(new CauseNode(inputGate, this.diagram.history().getVariableValueForStep(inputGate.getName(), timestamp), timestamp))), new ArrayList<>());
            }
        } else {
            return this.explainInternalBlock(gateName, blockName, timestamp);
        }
        throw new RuntimeException("can't find gate " + gateName + " for block " + blockName);
    }

    private ExplanationItem explainInternalBlock(String gateName, String blockName, int timestamp) {
        FunctionBlockBase fbToExplain = this.diagram.getInternalDiagram().getFunctionBlocks().stream()
                .filter(fb -> fb.getName().equals(blockName))
                .findFirst().orElse(null);

        if (fbToExplain == null) {
            throw new RuntimeException("Can't find FBlock for the Gate " + gateName);
        }

        CauseNode rootNode = null;
        CausePathTree causesTree = new CausePathTree();

        CauseNode parentNode;

        ExplanationItem res;
        OutputGate out = fbToExplain.fbInterface().getOutputs().get(gateName);
        if (out != null) {
            rootNode = new CauseNode(out, fbToExplain.history().getVariableValueForStep(out.getName(), timestamp), timestamp);
            res = fbToExplain.explain(out, timestamp);
            parentNode = rootNode;
        } else {
            InputGate in = fbToExplain.fbInterface().getInputs().get(gateName);
            if (in != null) {
                rootNode = new CauseNode(in, fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp);
                if (in.getIncomingConnection() != null && in.getIncomingConnection().fromGate() instanceof OutputGate) {
                    OutputGate gateToExplain = (OutputGate) in.getIncomingConnection().fromGate();
                    CauseNode childCause = new CauseNode(gateToExplain, gateToExplain.getOwner().history().getVariableValueForStep(gateToExplain.getName(), timestamp), timestamp);
                    rootNode.addChildNode(childCause);
                    parentNode = childCause;
                    res = gateToExplain.getOwner().explain(gateToExplain, timestamp);
                } else if (in.getIncomingConnection() != null) {
                    // connected to system input
                    CausePathTree tree = new CausePathTree();
                    CauseNode root = new CauseNode(in, fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp);
                    tree.addRoot(root);
                    Gate childGate = in.getIncomingConnection().fromGate();
                    CauseNode child = new CauseNode(childGate, childGate.getOwner().history().getVariableValueForStep(childGate.getName(), timestamp), timestamp);
                    root.addChildNode(child);
                    return new ExplanationItem(tree, new ArrayList<>() {{
                        add(child);
                    }});

                } else {
                    //const
                    if (!this.diagram.fbInterface().getInputs().containsKey(in.getName())) {
                        return new ExplanationItem(new CausePathTree(Collections.singletonList(new CauseNode(in, fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp))), new ArrayList<>());
                    }
                    return new ExplanationItem(new CausePathTree(Collections.singletonList(new CauseNode(in, this.diagram.history().getVariableValueForStep(in.getName(), timestamp), timestamp))), new ArrayList<>());
                }
            } else {
                throw new RuntimeException("Can't find the Gate " + gateName + " in Block " + blockName);
            }
        }

        causesTree.addRoot(rootNode);
        parentNode.addChildren(res.getTree().

                getRoots());
        Collection<CauseNode> causeNodes = res.getFreshNodes();
        Set<CauseNode> outputCauseNodes = new HashSet<>();

        ExplanationItem result = new ExplanationItem(causesTree, outputCauseNodes);
        for (
                CauseNode causeNode : causeNodes) {
            if (this.diagram.fbInterface().getInputs().get(causeNode.getGate().getName()) != null &&
                    this.diagram.fbInterface().getInputs().get(causeNode.getGate().getName()).equals(causeNode.getGate())
            ) {
                // System.out.println(String.format("InternalD: cause '%s' added to result", causeNode.getGate().getName()));
                CauseNode childNode = causeNode;
                outputCauseNodes.add(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                // System.out.println(String.format("InternalD: cause '%s' will be processed", causeNode.getGate().getName()));
                ExplanationItem childItem = this.explainInternalBlock(causeNode.getGate().getName(), causeNode.getGate().getOwner().getName(), causeNode.getTimestamp());
                outputCauseNodes.addAll(childItem.getFreshNodes());
//                causeNode.addChildren(childItem.getFreshNodes());
                causeNode.addChildren(childItem.getTree().getRoots());
                // System.out.println(String.format("InternalD: cause '%s' processed", causeNode.getGate().getName()));
            }
        }

        return result;
    }

    @Override
    public ExplanationItem explain(String varName, String blockName, int timestamp, boolean inContext) {
        return null;
    }

}
