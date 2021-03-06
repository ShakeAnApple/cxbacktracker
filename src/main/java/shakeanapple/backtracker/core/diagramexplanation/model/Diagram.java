package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.*;
import java.util.stream.Collectors;

public class Diagram {
    private List<FunctionBlockBase> functionBlocks;

    private Map<String, Gate> inputs;
    private Map<String, Gate> outputs;

    public Diagram(List<FunctionBlockBase> functionBlocks, List<Gate> inputs, List<Gate> outputs) {
        this.functionBlocks = functionBlocks;
//        this.inputs = inputs.stream().collect(Collectors.toMap(InputVariable::getId, in -> in));
//        this.outputs = outputs.stream().collect(Collectors.toMap(OutputVariable::getId, out -> out));

        this.inputs = inputs.stream().collect(Collectors.toMap(Gate::getFullName, in -> in));
//        this.outputs = outputs.stream().collect(Collectors.toMap(Gate::getFullName, in -> in));
    }

    public List<FunctionBlockBase> getFunctionBlocks() {
        return this.functionBlocks;
    }

//    public Map<Long, InputVariable> getInputs() {
//        return this.inputs;
//    }
//
//    public Map<Long, OutputVariable> getOutputs() {
//        return this.outputs;
//    }

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
            if (this.inputs.containsKey(causeNode.getGate().getFullName())) {
                // System.out.println(String.format("InternalD: cause '%s' added to result", causeNode.getGate().getIncomingConnection().fromGate().getName()));
                Gate childGate = causeNode.getGate().getIncomingConnection().fromGate();
                CauseNode childNode = new CauseNode(childGate, childGate.getOwner().history().getVariableValueForStep(childGate.getName(), causeNode.getTimestamp()), causeNode.getTimestamp());
//                CauseNode childNode = new CauseNode(causeNode.getGate(), causeNode.getValue(), causeNode.getTimestamp());
                outputCauseNodes.add(childNode);
                causeNode.addChildNode(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                // System.out.println(String.format("InternalD: cause '%s' will be processed", causeNode.getGate().getName()));
                ExplanationItem childItem = this.explain((OutputGate) causeNode.getGate().getIncomingConnection().fromGate(), causeNode.getTimestamp());
                causeNode.addChildren(childItem.getTree().getRoots());
                outputCauseNodes.addAll(childItem.getFreshNodes());
                // System.out.println(String.format("InternalD: cause '%s' processed", causeNode.getGate().getName()));
            }
        }

        return result;
//        return new ArrayList<>(outputCauseNodes);
    }

    @Override
    public Diagram clone() {
        List<Gate> newInputs = new ArrayList<>();
        List<Gate> newOutputs = new ArrayList<>();

        Map<String, FunctionBlockBase> blockClones = this.functionBlocks.stream().map(FunctionBlockBase::clone).collect(Collectors.toMap(DiagramElement::getName, v -> v));

        for (FunctionBlockBase fb : this.functionBlocks) {
            FunctionBlockBase clonedBlock = blockClones.get(fb.getName());
            Map<String, InputGate> inputGatesFb = fb.fbInterface().getInputs();
            for (InputGate inCloned : clonedBlock.fbInterface().getInputs().values()) {
                Connection inConnection = inputGatesFb.get(inCloned.getName()).getIncomingConnection();
                if (inConnection != null) {
                    FunctionBlockBase fromCloned = blockClones.get(inConnection.from().getName());
                    if (fromCloned != null) {
                        inCloned.makeIncomingConnection(fromCloned.fbInterface().getOutputs().get(inConnection.fromGate().getName()), fromCloned, clonedBlock, inConnection.isInverted());
                    }else{
                        newInputs.add(inCloned);
                    }
                }
            }
            Map<String, OutputGate> outputGatesFb = fb.fbInterface().getOutputs();
            for (OutputGate outCloned : clonedBlock.fbInterface().getOutputs().values()) {
                for (Connection outConnection : outputGatesFb.get(outCloned.getName()).getOutgoingConnections()) {
                    FunctionBlockBase toCloned = blockClones.get(outConnection.to().getName());
                    if (toCloned != null) {
                        outCloned.makeOutgoingConnection(toCloned.fbInterface().getInputs().get(outConnection.toGate().getName()), clonedBlock, toCloned, outConnection.isInverted());
                    } else{
                        newOutputs.add(outCloned);
                    }
                }
            }
        }

        // can go wrong as inputs and outputs are references
        return new Diagram(new ArrayList<>(blockClones.values()),
                newInputs,
                newOutputs);
    }
}
