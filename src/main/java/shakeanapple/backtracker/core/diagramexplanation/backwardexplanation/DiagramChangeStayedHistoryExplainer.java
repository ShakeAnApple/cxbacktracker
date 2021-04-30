package shakeanapple.backtracker.core.diagramexplanation.backwardexplanation;

import shakeanapple.backtracker.core.diagramexplanation.DiagramOutputExplainer;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph.CausePathFinalGraph;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangeStayedExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangedStayedCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangedStayedCausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeCausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeExplanationItem;

import java.util.*;
import java.util.stream.Collectors;


public class DiagramChangeStayedHistoryExplainer implements DiagramOutputExplainer {

    private FunctionBlockComplex diagram;

    public DiagramChangeStayedHistoryExplainer(FunctionBlockComplex diagram) {
        this.diagram = diagram;
    }


    @Override
    public ChangeStayedExplanationItem explainChangedStayedHistory(String gateName, List<String> blockPath, int timestamp) {
        FunctionBlockBase fbToExplain = this.diagram;
        Map<String, ChangeStayedExplanationItem> processedCauses = new HashMap<>();

        for (int i = 0; i < blockPath.size(); i++) {
            String nextName = blockPath.get(i);
            fbToExplain = ((FunctionBlockComplex) fbToExplain).getInternalDiagram().getFunctionBlocks().stream()
                    .filter(fb -> fb.getName().equals(nextName))
                    .findFirst().orElse(null);
            if (fbToExplain == null) break;
        }

        if (fbToExplain == null) {
            OutputGate outputGate = this.diagram.fbInterface().getOutputs().get(gateName);
            if (outputGate != null) {
                ChangeStayedExplanationItem item = this.diagram.explainHistoryStayedChanged(outputGate, timestamp, processedCauses);
                return item;
            }
            InputGate inputGate = this.diagram.fbInterface().getInputs().get(gateName);
            if (inputGate != null) {
                return new ChangeStayedExplanationItem(new ChangedStayedCausePathTree(Collections.singletonList(new ChangedStayedCauseNode(inputGate, this.diagram.history().getVariableValueForStep(inputGate.getName(), timestamp), timestamp,
                        this.diagram.history().getVariableValueForStep(inputGate.getName(), timestamp), timestamp, timestamp))), new ArrayList<>());
            }
        } else {
            return this.explainInternalBlock(gateName, fbToExplain, timestamp, processedCauses);
        }
        throw new RuntimeException("can't find gate " + gateName + " for block path" + Arrays.toString(blockPath.toArray()));
    }

    @Override
    public CausePathFinalGraph explainFinal(String gateName, List<String> blockPath, int timestamp) {
        return null;
    }

    private ChangeStayedExplanationItem explainInternalBlock(String gateName, FunctionBlockBase fbToExplain, int timestamp, Map<String, ChangeStayedExplanationItem> processedNodes) {
        if (fbToExplain == null) {
            throw new RuntimeException("Can't find FBlock for the Gate " + gateName);
        }

        ChangedStayedCauseNode rootNode = null;
        ChangedStayedCausePathTree causesTree = new ChangedStayedCausePathTree();

        ChangedStayedCauseNode parentNode;

        ChangeStayedExplanationItem res;
        OutputGate out = fbToExplain.fbInterface().getOutputs().get(gateName);
        if (out != null) {
            rootNode = new ChangedStayedCauseNode(out, fbToExplain.history().getVariableValueForStep(out.getName(), timestamp), timestamp,
                    fbToExplain.history().getVariableValueForStep(out.getName(), timestamp), timestamp, timestamp);
            if (processedNodes.containsKey(this.getStringHash(out, timestamp))) {
                return processedNodes.get(this.getStringHash(out, timestamp));
            }
            res = fbToExplain.explainHistoryStayedChanged(out, timestamp, processedNodes);
            parentNode = rootNode;
        } else {
            InputGate in = fbToExplain.fbInterface().getInputs().get(gateName);
            if (in != null) {
                rootNode = new ChangedStayedCauseNode(in, fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp,
                        fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp, timestamp);
                if (processedNodes.containsKey(this.getStringHash(in, timestamp))) {
                    return processedNodes.get(this.getStringHash(in, timestamp));
                }
                if (in.getIncomingConnection() != null && in.getIncomingConnection().fromGate() instanceof OutputGate) {
                    OutputGate gateToExplain = (OutputGate) in.getIncomingConnection().fromGate();
                    ChangedStayedCauseNode childCause = new ChangedStayedCauseNode(gateToExplain, gateToExplain.getOwner().history().getVariableValueForStep(gateToExplain.getName(), timestamp), timestamp,
                            gateToExplain.getOwner().history().getVariableValueForStep(gateToExplain.getName(), timestamp), timestamp, timestamp);
                    rootNode.addChildNode(childCause);
                    parentNode = childCause;
                    if (processedNodes.containsKey(this.getStringHash(gateToExplain, timestamp))) {
                        res = processedNodes.get(this.getStringHash(gateToExplain, timestamp));
                    } else {
                        res = gateToExplain.getOwner().explainHistoryStayedChanged(gateToExplain, timestamp, processedNodes);
                        processedNodes.put(this.getStringHash(gateToExplain, timestamp), res);
                    }
                } else if (in.getIncomingConnection() != null) {
                    // connected to system input
                    ChangedStayedCausePathTree tree = new ChangedStayedCausePathTree();
                    ChangedStayedCauseNode root = new ChangedStayedCauseNode(in, fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp,
                            fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp, timestamp);
                    tree.addRoot(root);
                    Gate childGate = in.getIncomingConnection().fromGate();
                    ChangedStayedCauseNode child = new ChangedStayedCauseNode(childGate, childGate.getOwner().history().getVariableValueForStep(childGate.getName(), timestamp), timestamp,
                            childGate.getOwner().history().getVariableValueForStep(childGate.getName(), timestamp), timestamp, timestamp);
                    root.addChildNode(child);
                    return new ChangeStayedExplanationItem(tree, new ArrayList<>() {{
                        add(child);
                    }});

                } else {
                    //const
                    if (!this.diagram.fbInterface().getInputs().containsKey(in.getName())) {
                        return new ChangeStayedExplanationItem(new ChangedStayedCausePathTree(Collections.singletonList(new ChangedStayedCauseNode(in, fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp,
                                fbToExplain.history().getVariableValueForStep(in.getName(), timestamp), timestamp, timestamp))), new ArrayList<>());
                    }
                    return new ChangeStayedExplanationItem(new ChangedStayedCausePathTree(Collections.singletonList(new ChangedStayedCauseNode(in, this.diagram.history().getVariableValueForStep(in.getName(), timestamp), timestamp,
                            this.diagram.history().getVariableValueForStep(in.getName(), timestamp), timestamp, timestamp))), new ArrayList<>());
                }
            } else {
                throw new RuntimeException("Can't find the Gate " + gateName + " in Block " + fbToExplain.getName());
            }
        }

        causesTree.addRoot(rootNode);
        parentNode.addChildren(res.getTree().getRoots());
//        processedNodes.putAll(res.getTree().getRoots().stream().collect(Collectors.toMap(ChangedStayedCauseNode::hashCode, r -> r)));
        Collection<ChangedStayedCauseNode> causeNodes = res.getFreshNodes();
        Set<ChangedStayedCauseNode> outputCauseNodes = new HashSet<>();

        ChangeStayedExplanationItem result = new ChangeStayedExplanationItem(causesTree, outputCauseNodes);
        for (ChangedStayedCauseNode causeNode : causeNodes) {
            if (this.diagram.fbInterface().getInputs().get(causeNode.getGate().getName()) != null &&
                    this.diagram.fbInterface().getInputs().get(causeNode.getGate().getName()).equals(causeNode.getGate())
            ) {
                ChangedStayedCauseNode childNode = causeNode;
                outputCauseNodes.add(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                ChangeStayedExplanationItem childItem = this.explainInternalBlock(causeNode.getGate().getName(), causeNode.getGate().getOwner(), causeNode.getChange().getNewStep(), processedNodes);
                if (!processedNodes.containsKey(this.getStringHash(causeNode.getGate(), causeNode.getChange().getNewStep()))) {
                    processedNodes.put(this.getStringHash(causeNode.getGate(), causeNode.getChange().getNewStep()), childItem);
                }

                outputCauseNodes.addAll(childItem.getFreshNodes());
                causeNode.addChildren(childItem.getTree().getRoots());
            }
        }

        return result;
    }

    private String getStringHash(Gate gate, int timestamp) {
        return gate.getFullName() + timestamp;
    }

    @Override
    public ExplanationItem explain(String outputName, List<String> blockPath, int timestamp) {
        return null;
    }

    @Override
    public ChangeExplanationItem explainChange(String outputName, List<String> blockPath, int timestamp) {
        return null;
    }

    @Override
    public ChangeExplanationItem explainChangeHistory(String outputName, List<String> blockPath, int timestamp) {
        return null;
    }

    @Override
    public List<Gate> extractNonObviousConstants(ExplanationItem explanationResult) {
        return null;
    }
}
