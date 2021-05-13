package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph.CauseFinalNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph.CausePathFinalGraph;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangeStayedExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangedStayedCauseNode;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Diagram {
    private List<FunctionBlockBase> functionBlocks;

    private Map<String, Gate> inputs;
    private Map<String, Gate> outputs;
    private FunctionBlockComplex owner;

    public Diagram(List<FunctionBlockBase> functionBlocks, List<Gate> inputs, List<Gate> outputs) {
        this.functionBlocks = functionBlocks;
        this.inputs = inputs.stream().collect(Collectors.toMap(Gate::getFullName, in -> in));
    }

    public List<FunctionBlockBase> getFunctionBlocks() {
        return this.functionBlocks;
    }

    public void setOwner(FunctionBlockComplex owner) {
        this.owner = owner;
    }

    public FunctionBlockComplex getOwner() {
        return owner;
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
            if (this.inputs.containsKey(causeNode.getGate().getFullName())) {
                Gate childGate = causeNode.getGate().getIncomingConnection().fromGate();
                CauseNode childNode = new CauseNode(childGate, childGate.getOwner().history().getVariableValueForStep(childGate.getName(), causeNode.getTimestamp()), causeNode.getTimestamp());
                outputCauseNodes.add(childNode);
                causeNode.addChildNode(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                ExplanationItem childItem = this.explain((OutputGate) causeNode.getGate().getIncomingConnection().fromGate(), causeNode.getTimestamp());
                causeNode.addChildren(childItem.getTree().getRoots());
                outputCauseNodes.addAll(childItem.getFreshNodes());
            }
        }

        return result;
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

    // TODO what about changed causes for connections? for now, I count start and end as single variable
    public ChangeExplanationItem explainChange(OutputGate gateToExplain, Integer timestamp) {
        ChangeExplanationItem item = gateToExplain.getOwner().explainChange(gateToExplain, timestamp);

        Collection<ChangeCauseNode> causeNodes = item.getFreshNodes();
        Set<ChangeCauseNode> outputCauseNodes = new HashSet<>();

        ChangeExplanationItem result = new ChangeExplanationItem(item.getTree(), outputCauseNodes);
        for (ChangeCauseNode causeNode : causeNodes) {
            if (this.inputs.containsKey(causeNode.getGate().getFullName())) {
                Gate childGate = causeNode.getGate().getIncomingConnection().fromGate();
                ChangeCauseNode childNode = new ChangeCauseNode(childGate, childGate.getOwner().history().getVariableValueForStep(childGate.getName(), causeNode.getChange().getCurrentStep()), causeNode.getChange().getCurrentStep(),
                        childGate.getOwner().history().getVariableValueForStep(childGate.getName(), causeNode.getChange().getCurrentStep()), causeNode.getChange().getCurrentStep());
                outputCauseNodes.add(childNode);
                causeNode.addChildNode(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                ChangeExplanationItem childItem = this.explainChange((OutputGate) causeNode.getGate().getIncomingConnection().fromGate(), causeNode.getChange().getCurrentStep());
                causeNode.addChildren(childItem.getTree().getRoots());
                outputCauseNodes.addAll(childItem.getFreshNodes());
            }
        }

        return result;
    }

    public ChangeExplanationItem explainHistoryChange(OutputGate gateToExplain, Integer timestamp) {
        ChangeExplanationItem item = gateToExplain.getOwner().explainHistoryChange(gateToExplain, timestamp);

        Collection<ChangeCauseNode> causeNodes = item.getFreshNodes();
        Set<ChangeCauseNode> outputCauseNodes = new HashSet<>();

        ChangeExplanationItem result = new ChangeExplanationItem(item.getTree(), outputCauseNodes);
        for (ChangeCauseNode causeNode : causeNodes) {
            if (this.inputs.containsKey(causeNode.getGate().getFullName())) {
                Gate childGate = causeNode.getGate().getIncomingConnection().fromGate();
                ChangeCauseNode childNode = new ChangeCauseNode(childGate, childGate.getOwner().history().getVariableValueForStep(childGate.getName(), causeNode.getChange().getCurrentStep()), causeNode.getChange().getCurrentStep(),
                        childGate.getOwner().history().getVariableValueForStep(childGate.getName(), causeNode.getChange().getCurrentStep()), causeNode.getChange().getCurrentStep());
                outputCauseNodes.add(childNode);
                causeNode.addChildNode(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                ChangeExplanationItem childItem = this.explainHistoryChange((OutputGate) causeNode.getGate().getIncomingConnection().fromGate(), causeNode.getChange().getCurrentStep());
                causeNode.addChildren(childItem.getTree().getRoots());
                outputCauseNodes.addAll(childItem.getFreshNodes());
            }
        }

        return result;
    }

    private String getStringHash(Gate gate, int timestamp) {
        return gate.getFullName() + timestamp;
    }

    public ChangeStayedExplanationItem explainHistoryStayedChanged(OutputGate gateToExplain, Integer timestamp, Map<String, ChangeStayedExplanationItem> processedNodes) {
//        System.out.println("Entered gate: " + timestamp + ": " + gateToExplain.getFullName() + " " + gateToExplain.getOwner().getName());
        if (processedNodes.containsKey(this.getStringHash(gateToExplain, timestamp))){
            return processedNodes.get(this.getStringHash(gateToExplain, timestamp));
        }

        ChangeStayedExplanationItem item = gateToExplain.getOwner().explainHistoryStayedChanged(gateToExplain, timestamp, processedNodes);
        if (!processedNodes.containsKey(this.getStringHash(gateToExplain, timestamp))){
            processedNodes.put(this.getStringHash(gateToExplain, timestamp), item);
        }

        Collection<ChangedStayedCauseNode> causeNodes = item.getFreshNodes();
        List<ChangedStayedCauseNode> outputCauseNodes = new ArrayList<>();

        ChangeStayedExplanationItem result = new ChangeStayedExplanationItem(item.getTree(), outputCauseNodes);
        for (ChangedStayedCauseNode causeNode : causeNodes) {
            if (this.inputs.containsKey(causeNode.getGate().getFullName())) {
                Gate childGate = causeNode.getGate().getIncomingConnection().fromGate();
                ChangedStayedCauseNode childNode = new ChangedStayedCauseNode(childGate, childGate.getOwner().history().getVariableValueForStep(childGate.getName(), causeNode.getChange().getNewStep()), causeNode.getChange().getNewStep(),
                        childGate.getOwner().history().getVariableValueForStep(childGate.getName(), causeNode.getChange().getNewStep()), causeNode.getChange().getNewStep(), causeNode.getChange().getNewStep());
                outputCauseNodes.add(childNode);
                causeNode.addChildNode(childNode);
            } else if (causeNode.getGate().getIncomingConnection() != null) {
                ChangeStayedExplanationItem childItem = this.explainHistoryStayedChanged((OutputGate) causeNode.getGate().getIncomingConnection().fromGate(), causeNode.getChange().getNewStep(), processedNodes);
                if (!processedNodes.containsKey(this.getStringHash(causeNode.getGate().getIncomingConnection().fromGate(), causeNode.getChange().getNewStep()))){
                    processedNodes.put(this.getStringHash(causeNode.getGate().getIncomingConnection().fromGate(), causeNode.getChange().getNewStep()), childItem);
                }
                causeNode.addChildren(childItem.getTree().getRoots());
                outputCauseNodes.addAll(childItem.getFreshNodes());
            }
        }
        item.getFreshNodes().clear();
//        System.out.println("Left gate: " + timestamp + ": " + gateToExplain.getFullName() + " " + gateToExplain.getOwner().getName());

        return result;
    }

    public List<CauseFinalNode> explainFinal(Gate gate, int timestamp, CauseFinalNode parent, CausePathFinalGraph graph) {
        List<CauseFinalNode> resultNodes = new ArrayList<>();
        CauseFinalNode existingNode = graph.causeNodeFor(gate, timestamp, parent == null);
        if (existingNode != null){
            parent.addChildNode(existingNode);
            return new ArrayList<>();
        }

        //FIXME in-out connections should preserve information about changes

        if (gate.getIncomingConnection() == null && gate instanceof OutputGate){
            // output of internal block
            resultNodes = gate.getOwner().explainFinal(gate, timestamp, parent, graph);
        } else {
            BlockVariableHistoryItem lastChange = gate.getOwner().history().lastChangeForVarBeforeStep(gate.getName(), timestamp);
            CauseFinalNode rootNode = new CauseFinalNode(gate, gate.getOwner().history().getVariableValueForStep(gate.getName(), timestamp), timestamp, graph, parent,
                    lastChange.getValueHolder(), lastChange.getTimestamp());
            // we might enter outer interface with no incoming connections
            if (gate instanceof InputGate && gate.getIncomingConnection() == null){
                resultNodes.add(rootNode);
            } else{
                // can be an input gate as well (connected to interface, e.g.) or an output gate
                Gate gateToExplain = gate.getIncomingConnection().fromGate();
                existingNode = graph.causeNodeFor(gateToExplain, timestamp, parent == null);
                if (existingNode != null) {
                    rootNode.addChildNode(existingNode);
                } else {
                    resultNodes = gateToExplain.getOwner().explainFinal(gateToExplain, timestamp, rootNode, graph);
                }
            }
        }

        List<CauseFinalNode> newNodes = new ArrayList<>();
        for (CauseFinalNode causeNode : resultNodes) {
            if (this.getOwner().fbInterface().getInputs().containsKey(causeNode.getGate().getName())){
                newNodes.add(causeNode);
            } else if (causeNode.getGate().getIncomingConnection() != null){
                existingNode = graph.causeNodeFor(causeNode.getGate().getIncomingConnection().fromGate(), causeNode.getStep(), parent == null);
                if (existingNode != null){
                    causeNode.addChildNode(existingNode);
                } else {
                    List<CauseFinalNode> nodes = this.explainFinal(causeNode.getGate().getIncomingConnection().fromGate(), causeNode.getStep(), causeNode, graph);
                    newNodes.addAll(nodes);
                }
            }
        }
        return newNodes;
    }


}
