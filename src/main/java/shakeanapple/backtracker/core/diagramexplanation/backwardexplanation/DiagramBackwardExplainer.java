package shakeanapple.backtracker.core.diagramexplanation.backwardexplanation;

import shakeanapple.backtracker.core.diagramexplanation.DiagramOutputExplainer;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph.CausePathFinalGraph;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangeStayedExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.CausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlock;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockConverter;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvStringModel;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.ComplexBlockConverter;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DiagramBackwardExplainer implements DiagramOutputExplainer {

    private FunctionBlockComplex diagram;

    public DiagramBackwardExplainer(FunctionBlockComplex diagram) {
        this.diagram = diagram;
    }

    public ExplanationItem explain(String gateName, List<String> blockPath, int timestamp) {
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
                ExplanationItem item = this.diagram.explain(outputGate, timestamp);
                return item;
            }
            InputGate inputGate = this.diagram.fbInterface().getInputs().get(gateName);
            if (inputGate != null) {
                return new ExplanationItem(new CausePathTree(Collections.singletonList(new CauseNode(inputGate, this.diagram.history().getVariableValueForStep(inputGate.getName(), timestamp), timestamp))), new ArrayList<>());
            }
        } else {
            return this.explainInternalBlock(gateName, fbToExplain, timestamp);
        }
        throw new RuntimeException("can't find gate " + gateName + " for block path" + Arrays.toString(blockPath.toArray()));
    }

    @Override
    public ChangeExplanationItem explainChange(String outputName, List<String> blockPath, int timestamp) {
        return null;
    }

    @Override
    public ChangeExplanationItem explainChangeHistory(String outputName, List<String> blockPath, int timestamp) {
        return null;
    }

    private ExplanationItem explainInternalBlock(String gateName, FunctionBlockBase fbToExplain, int timestamp) {
//        FunctionBlockBase fbToExplain = this.diagram.getInternalDiagram().getFunctionBlocks().stream()
//                .filter(fb -> fb.getName().equals(blockName))
//                .findFirst().orElse(null);

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
                throw new RuntimeException("Can't find the Gate " + gateName + " in Block " + fbToExplain.getName());
            }
        }

        causesTree.addRoot(rootNode);
        parentNode.addChildren(res.getTree().getRoots());
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
                ExplanationItem childItem = this.explainInternalBlock(causeNode.getGate().getName(), causeNode.getGate().getOwner(), causeNode.getTimestamp());
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
        CauseNode root = explanationResult.getTree().getRoots().get(0);
        Map<String, CauseGateEvaluation> suspiciousGates = this.extractSuspiciousGates(root);
        List<String> propertiesToCheck = suspiciousGates.entrySet().stream()
                .filter(entry -> entry.getValue().getEvaluations().size() == 1)
                .map(entry -> "LTLSPEC G " + entry.getKey() + " = " + entry.getValue().getEvaluations().iterator().next() + ";" + System.lineSeparator())
                .collect(Collectors.toList());

        if (propertiesToCheck.size() == 0) {
            return new ArrayList<>();
        }

        NusmvBlockConverter converter = new ComplexBlockConverter(this.diagram);
        NusmvBlock nusmvBlock = converter.convert(true);
        NusmvStringModel model = nusmvBlock.getStringModel();
        model.addSpecifications(propertiesToCheck);

        try {
            NusmvRunner nusmvRunner = new NusmvRunner(model);
            nusmvRunner.run( true, "-dcx", "-bmc");

            List<String> output = Arrays.stream(new String(nusmvRunner.getInputStream().readAllBytes()).split(System.lineSeparator()))
                    .filter(line -> line.contains("specification") && line.contains("is true"))
                    .map(line -> line
                            .substring(0, line.indexOf(" ="))
                            .replace("-- specification  G", "")
                            .replace("is true", "")
                            .trim()
                            )
                    .collect(Collectors.toList());

            List<Gate> res = new ArrayList<>();
            for(String gateName: output){
                System.out.println(gateName);
                res.add(suspiciousGates.get(gateName).getGate());
            }
            return res;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public ChangeStayedExplanationItem explainChangedStayedHistory(String gateName, List<String> blockPath, int timestamp) {
        return null;
    }

    @Override
    public CausePathFinalGraph explainFinal(String gateName, List<String> blockPath, int timestamp) {
        return null;
    }

    private Map<String, CauseGateEvaluation> extractSuspiciousGates(CauseNode parent) {
        Map<String, CauseGateEvaluation> gatesValues = new HashMap<>();
        if (parent.getGate().getIncomingConnection() != null) {
            gatesValues.put(parent.getGate().getFullNameInContext(this.diagram.getName()), new CauseGateEvaluation(parent.getGate(), parent.getValue().toString()));
        }
        for (CauseNode child : parent.getChildren()) {
            Map<String, CauseGateEvaluation> childValues = this.extractSuspiciousGates(child);
            for (String key : childValues.keySet()) {
                if (!gatesValues.containsKey(key)) {
                    gatesValues.put(key, new CauseGateEvaluation(child.getGate()));
                }
                gatesValues.get(key).addEvaluations(childValues.get(key).getEvaluations());
            }
        }
        return gatesValues;
    }

    private Map<String, HashSet<String>> extractNonConstantGatesNames(CauseNode parent) {
        Map<String, HashSet<String>> gatesValues = new HashMap<>();
        if (parent.getGate().getIncomingConnection() != null && parent.getGate().getIncomingConnection().fromGate().getIncomingConnection() != null) {
            gatesValues.put(parent.getGate().getFullName(), new HashSet<>() {{
                add(parent.getValue().toString());
            }});
        }
        for (CauseNode child : parent.getChildren()) {
            Map<String, HashSet<String>> childValues = this.extractNonConstantGatesNames(child);
            for (String key : childValues.keySet()) {
                if (!gatesValues.containsKey(key)) {
                    gatesValues.put(key, new HashSet<>());
                }
                gatesValues.get(key).addAll(childValues.get(key));
            }
        }
        return gatesValues;
    }
}
