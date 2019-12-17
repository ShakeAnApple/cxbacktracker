package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.*;
import java.util.stream.Collectors;

public class Diagram {
    private List<FunctionBlockBase> functionBlocks;

    private Map<Long, InputVariable> inputs;
    private Map<Long, OutputVariable> outputs;

    public Diagram(List<FunctionBlockBase> functionBlocks, List<InputVariable> inputs, List<OutputVariable> outputs) {
        this.functionBlocks = functionBlocks;
        this.inputs = inputs.stream().collect(Collectors.toMap(InputVariable::getId, in -> in));
        this.outputs = outputs.stream().collect(Collectors.toMap(OutputVariable::getId, out -> out));
        ;
    }

    public List<FunctionBlockBase> getFunctionBlocks() {
        return this.functionBlocks;
    }

    public Map<Long, InputVariable> getInputs() {
        return this.inputs;
    }

    public Map<Long, OutputVariable> getOutputs() {
        return this.outputs;
    }

    public void execute() {
//        for (FunctionBlockBase fb: this.functionBlocks){
//            if (fb.fbInterface().getInputs().values().stream().noneMatch(i -> i.getIncomingConnection() != null)){
//                System.out.println(fb.getName() + ": no connected inputs, executed");
//                fb.execute();
//            }
//        }
    }


    // TODO lazy recursion
    public ExplanationItem explain(OutputGate gateToExplain, Integer timestamp) {
        ExplanationItem item = gateToExplain.getOwner().explain(gateToExplain, timestamp);
        Collection<CauseNode> causeNodes = item.getFreshNodes();
        Set<CauseNode> outputCauseNodes = new HashSet<>();

        ExplanationItem result = new ExplanationItem(item.getTree(), outputCauseNodes);
        for (CauseNode causeNode : causeNodes) {
            if (this.inputs.containsKey(causeNode.getGate().output().getId())) {
                System.out.println(String.format("InternalD: cause '%s' added to result", causeNode.getGate().getIncomingConnection().fromGate().getName()));
                Gate childGate = causeNode.getGate().getIncomingConnection().fromGate();
                CauseNode childNode = new CauseNode(childGate, childGate.getOwner().history().getVariableValueForStep(childGate.getName(), causeNode.getTimestamp()), causeNode.getTimestamp());
//                CauseNode childNode = new CauseNode(causeNode.getGate(), causeNode.getValue(), causeNode.getTimestamp());
                outputCauseNodes.add(childNode);
                causeNode.addChildNode(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                System.out.println(String.format("InternalD: cause '%s' will be processed", causeNode.getGate().getName()));
                ExplanationItem childItem = this.explain((OutputGate) causeNode.getGate().getIncomingConnection().fromGate(), causeNode.getTimestamp());
                causeNode.addChildren(childItem.getTree().getRoots());
                outputCauseNodes.addAll(childItem.getFreshNodes());
                System.out.println(String.format("InternalD: cause '%s' processed", causeNode.getGate().getName()));
            }
        }

        return result;
//        return new ArrayList<>(outputCauseNodes);
    }
}
