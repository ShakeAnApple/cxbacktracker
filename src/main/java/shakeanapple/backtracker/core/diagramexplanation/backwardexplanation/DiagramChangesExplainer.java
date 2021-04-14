package shakeanapple.backtracker.core.diagramexplanation.backwardexplanation;

import shakeanapple.backtracker.core.diagramexplanation.DiagramOutputExplainer;
import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.changecausetree.ChangeCausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.model.changecausetree.ChangeExplanationItem;

import java.util.*;

public class DiagramChangesExplainer implements DiagramOutputExplainer {

    private FunctionBlockComplex diagram;

    public DiagramChangesExplainer(FunctionBlockComplex diagram) {
        this.diagram = diagram;
    }

    @Override
    public ExplanationItem explain(String outputName, List<String> blockPath, int timestamp) {
        return null;
    }

    @Override
    public ChangeExplanationItem explainChange(String gateName, List<String> blockPath, int timestamp) {
        FunctionBlockBase fbToExplain = this.diagram;
        for (int i = 0; i < blockPath.size(); i++) {
            String nextName = blockPath.get(i);
            fbToExplain = ((FunctionBlockComplex)fbToExplain).getInternalDiagram().getFunctionBlocks().stream()
                    .filter(fb -> fb.getName().equals(nextName))
                    .findFirst().orElse(null);
            if (fbToExplain == null) break;
        }

        if (fbToExplain == null) {
            OutputGate outputGate = this.diagram.fbInterface().getOutputs().get(gateName);
            if (outputGate != null) {
                ChangeExplanationItem item = this.diagram.explainChange(outputGate, timestamp);
                return item;
            }
            InputGate inputGate = this.diagram.fbInterface().getInputs().get(gateName);
            if (inputGate != null) {
                return new ChangeExplanationItem(new ChangeCausePathTree(Collections.singletonList(new ChangeCauseNode(inputGate, this.diagram.history().getVariableValueForStep(inputGate.getName(), timestamp), timestamp,
                        this.diagram.history().getVariableValueForStep(inputGate.getName(), timestamp), timestamp))), new ArrayList<>());
            }
        } else {
            return this.explainInternalBlock(gateName, fbToExplain, timestamp);
        }
        throw new RuntimeException("can't find gate " + gateName + " for block path" + Arrays.toString(blockPath.toArray()));
    }

    private ChangeExplanationItem explainInternalBlock(String gateName, FunctionBlockBase fbToExplain, int timestamp) {
        if (fbToExplain == null) {
            throw new RuntimeException("Can't find FBlock for the Gate " + gateName);
        }

        ChangeCauseNode rootNode = null;
        ChangeCausePathTree causesTree = new ChangeCausePathTree();

        ChangeCauseNode parentNode;

        ChangeExplanationItem res;
        OutputGate out = fbToExplain.fbInterface().getOutputs().get(gateName);
        if (out != null) {
            rootNode = new ChangeCauseNode(out, fbToExplain.history().getVariableValueForStep(out.getName(), timestamp), timestamp,
                    fbToExplain.history().getVariableValueForStep(out.getName(), timestamp), timestamp);
            res = fbToExplain.explainChange(out, timestamp);
            parentNode = rootNode;
        } else {
            InputGate in = fbToExplain.fbInterface().getInputs().get(gateName);
            if (in != null) {
                rootNode = new ChangeCauseNode(in, fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp,
                        fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp);
                if (in.getIncomingConnection() != null && in.getIncomingConnection().fromGate() instanceof OutputGate) {
                    OutputGate gateToExplain = (OutputGate) in.getIncomingConnection().fromGate();
                    ChangeCauseNode childCause = new ChangeCauseNode(gateToExplain, gateToExplain.getOwner().history().getVariableValueForStep(gateToExplain.getName(), timestamp), timestamp,
                            gateToExplain.getOwner().history().getVariableValueForStep(gateToExplain.getName(), timestamp), timestamp);
                    rootNode.addChildNode(childCause);
                    parentNode = childCause;
                    res = gateToExplain.getOwner().explainChange(gateToExplain, timestamp);
                } else if (in.getIncomingConnection() != null) {
                    // connected to system input
                    ChangeCausePathTree tree = new ChangeCausePathTree();
                    ChangeCauseNode root = new ChangeCauseNode(in, fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp,
                            fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp);
                    tree.addRoot(root);
                    Gate childGate = in.getIncomingConnection().fromGate();
                    ChangeCauseNode child = new ChangeCauseNode(childGate, childGate.getOwner().history().getVariableValueForStep(childGate.getName(), timestamp), timestamp,
                            childGate.getOwner().history().getVariableValueForStep(childGate.getName(), timestamp), timestamp);
                    root.addChildNode(child);
                    return new ChangeExplanationItem(tree, new ArrayList<>() {{
                        add(child);
                    }});

                } else {
                    //const
                    if (!this.diagram.fbInterface().getInputs().containsKey(in.getName())) {
                        return new ChangeExplanationItem(new ChangeCausePathTree(Collections.singletonList(new ChangeCauseNode(in, fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp,
                                fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp))), new ArrayList<>());
                    }
                    return new ChangeExplanationItem(new ChangeCausePathTree(Collections.singletonList(new ChangeCauseNode(in, this.diagram.history().getVariableValueForStep(in.getName(), timestamp), timestamp,
                            this.diagram.history().getVariableValueForStep(in.getName(), timestamp), timestamp))), new ArrayList<>());
                }
            } else {
                throw new RuntimeException("Can't find the Gate " + gateName + " in Block " + fbToExplain.getName());
            }
        }

        causesTree.addRoot(rootNode);
        parentNode.addChildren(res.getTree().getRoots());
        Collection<ChangeCauseNode> causeNodes = res.getFreshNodes();
        Set<ChangeCauseNode> outputCauseNodes = new HashSet<>();

        ChangeExplanationItem result = new ChangeExplanationItem(causesTree, outputCauseNodes);
        for (
                ChangeCauseNode causeNode : causeNodes) {
            if (this.diagram.fbInterface().getInputs().get(causeNode.getGate().getName()) != null &&
                    this.diagram.fbInterface().getInputs().get(causeNode.getGate().getName()).equals(causeNode.getGate())
            ) {
                // System.out.println(String.format("InternalD: cause '%s' added to result", causeNode.getGate().getName()));
                ChangeCauseNode childNode = causeNode;
                outputCauseNodes.add(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                // System.out.println(String.format("InternalD: cause '%s' will be processed", causeNode.getGate().getName()));
                ChangeExplanationItem childItem = this.explainInternalBlock(causeNode.getGate().getName(), causeNode.getGate().getOwner(), causeNode.getChange().getChangedStep());
                outputCauseNodes.addAll(childItem.getFreshNodes());
//                causeNode.addChildren(childItem.getFreshNodes());
                causeNode.addChildren(childItem.getTree().getRoots());
                // System.out.println(String.format("InternalD: cause '%s' processed", causeNode.getGate().getName()));
            }
        }

        return result;
    }

    @Override
    public List<Gate> extractNonObviousConstants(ExplanationItem explanationResult) {
        return null;
    }
}
