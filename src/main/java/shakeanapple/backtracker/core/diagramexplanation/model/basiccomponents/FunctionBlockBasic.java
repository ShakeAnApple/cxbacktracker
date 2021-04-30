package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph.CauseFinalNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph.CausePathFinalGraph;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangeStayedExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangedStayedCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangedStayedCausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.model.BlockVariableHistoryItem;
import shakeanapple.backtracker.core.diagramexplanation.model.Gate;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.arithmetic.*;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice.Choice;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice.ChoiceFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic.*;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.CausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeCausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.ComponentDefinitionType;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;
import shakeanapple.backtracker.ui.explainer.control.diagramchangeexplainer.model.Cause;

import java.util.*;
import java.util.stream.Collectors;

public abstract class FunctionBlockBasic extends FunctionBlockBase {
    private final List<InputVariable> inputs;
    private final List<OutputVariable> outputs;

    protected FunctionBlockBasic(String name, List<InputVariable> inputs, List<OutputVariable> outputs, String pathInSystem) {
        super(name, "BASIC", inputs, outputs, pathInSystem);
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public static FunctionBlockBasic instance(ComponentDefinitionType type, List<InputVariable> inputs, List<OutputVariable> outputs, String pathInSystem) {
        switch (type) {
            case AND:
                return new AndFunctionBlockBasic(true, inputs, outputs.get(0), pathInSystem);
            case OR:
                return new OrFunctionBlockBasic(true, inputs, outputs.get(0), pathInSystem);
            case ASSIGN:
                return new AssignFunctionBlockBasic(true, inputs.get(0), outputs.get(0), pathInSystem);
            case MINUS:
                return new MinusFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0), pathInSystem);
            case PLUS:
                return new PlusFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0), pathInSystem);
            case MUL:
                return new MulFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0), pathInSystem);
            case DIV:
                return new DivFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0), pathInSystem);
            case MOD:
                return new ModFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0), pathInSystem);
            case EQ:
                return new EqFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0), pathInSystem);
            case GREATER:
                return new GreaterFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0), pathInSystem);
            case GREATER_EQ:
                return new GreaterEqFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0), pathInSystem);
            case LESS:
                return new LessFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0), pathInSystem);
            case LESS_EQ:
                return new LessEqFunctionBlockBasic(true, inputs.get(0), inputs.get(1), outputs.get(0), pathInSystem);
            case COUNT:
                return new CountFunctionBlockBasic(true, inputs, outputs.get(0), pathInSystem);
            case MAX:
                return new MaxFunctionBlockBasic(true, inputs, outputs.get(0), pathInSystem);
            default:
                throw new RuntimeException("Not implemented block type " + type);
        }
    }

    public static FunctionBlockBasic choiceInstance(List<Choice> choices, OutputVariable output, String pathInSystem) {
        return new ChoiceFunctionBlockBasic(true, choices, output, pathInSystem);
    }

    public static FunctionBlockBasic delayInstance(InputVariable input, InputVariable defValue, OutputVariable output, int delay, String pathInSystem) {
        if (defValue == null) {
            return new DelayFunctionBlockBasic(true, input, output, delay, pathInSystem);
        }
        return new DelayFunctionBlockBasic(true, input, defValue, output, delay, pathInSystem);
    }

    public abstract void executeImpl();

    public List<InputVariable> getInputs() {
        return this.inputs;
    }

    public List<OutputVariable> getOutputs() {
        return this.outputs;
    }

    protected abstract List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp);

    @Override
    public ExplanationItem explainImpl(OutputGate output, Integer timestamp) {
        CauseNode root = new CauseNode(output, output.getValue(), timestamp);
        CausePathTree pathTree = new CausePathTree(root);
        List<CauseNode> causes = this.explainBasicImpl(output, timestamp);
        root.addChildren(causes);
        ExplanationItem res = new ExplanationItem(pathTree, causes);
        return res;
    }

    @Override
    public ChangeExplanationItem explainChangeImpl(OutputGate output, Integer timestamp) {
        ValueHolder val = this.history().getVariableValueForStep(output.getName(), timestamp);
        Map<Integer, BlockVariableHistoryItem> outputHistory = this.history().ofVariable(output.getName());
        for (int i = timestamp - 1; i > 0; i--) {
            final BlockVariableHistoryItem outputHistoryItem = outputHistory.get(i);
            if (!outputHistoryItem.getValueHolder().getValue().equals(val.getValue())) {
                final int changeStep = i;
                ChangeCauseNode root = new ChangeCauseNode(output, this.history().getVariableValueForStep(output.getName(), changeStep + 1), changeStep + 1,
                        this.history().getVariableValueForStep(output.getName(), changeStep), changeStep);
                ChangeCausePathTree pathTree = new ChangeCausePathTree(root);
                List<ChangeCauseNode> causes = this.explainChangeBasicImpl(output, changeStep);
                root.addChildren(causes);
                ChangeExplanationItem res = new ChangeExplanationItem(pathTree, causes);
                return res;
            }
        }
        ChangeCauseNode root = new ChangeCauseNode(output, this.history().getVariableValueForStep(output.getName(), timestamp), timestamp,
                this.history().getVariableValueForStep(output.getName(), timestamp), timestamp);
        ChangeCausePathTree pathTree = new ChangeCausePathTree(root);
        ChangeExplanationItem res = new ChangeExplanationItem(pathTree, new ArrayList<>());
        return res;
    }

    protected abstract List<ChangeCauseNode> explainChangeBasicImpl(OutputGate output, Integer timestamp);

    @Override
    public ChangeExplanationItem explainHistoryChangeImpl(OutputGate output, Integer timestamp) {
        ValueHolder val = this.history().getVariableValueForStep(output.getName(), timestamp);
        Map<Integer, BlockVariableHistoryItem> outputHistory = this.history().ofVariable(output.getName());
        for (int i = timestamp - 1; i > 0; i--) {
            final BlockVariableHistoryItem outputHistoryItem = outputHistory.get(i);
            if (!outputHistoryItem.getValueHolder().getValue().equals(val.getValue())) {
                final int changeStep = i;
                ChangeCauseNode root = new ChangeCauseNode(output, this.history().getVariableValueForStep(output.getName(), changeStep + 1), changeStep + 1,
                        this.history().getVariableValueForStep(output.getName(), changeStep), changeStep);
                ChangeCausePathTree pathTree = new ChangeCausePathTree(root);
                List<ChangeCauseNode> causes = this.explainHistoryChangeBasicImpl(output, changeStep + 1);
                root.addChildren(causes);
                ChangeExplanationItem res = new ChangeExplanationItem(pathTree, causes);
                return res;
            }
        }
        if (timestamp == 1) {
            ChangeCauseNode root = new ChangeCauseNode(output, this.history().getVariableValueForStep(output.getName(), timestamp), timestamp,
                    this.history().getVariableValueForStep(output.getName(), timestamp), timestamp);
            ChangeCausePathTree pathTree = new ChangeCausePathTree(root);
            List<ChangeCauseNode> causes = this.explainHistoryChangeBasicImpl(output, timestamp);
            root.addChildren(causes);
            ChangeExplanationItem res = new ChangeExplanationItem(pathTree, causes);
            return res;
        }
        ChangeCauseNode root = new ChangeCauseNode(output, this.history().getVariableValueForStep(output.getName(), timestamp), timestamp,
                this.history().getVariableValueForStep(output.getName(), timestamp), timestamp);
        ChangeCausePathTree pathTree = new ChangeCausePathTree(root);
        ChangeExplanationItem res = new ChangeExplanationItem(pathTree, new ArrayList<>());
        return res;
    }

    private List<ChangeCauseNode> explainHistoryChangeBasicImpl(OutputGate output, Integer changeStep) {
        List<CauseNode> causes = this.explainBasicImpl(output, changeStep);
        List<ChangeCauseNode> res = new ArrayList<>();
        for (CauseNode cause : causes) {
            ValueHolder val = this.history().getVariableValueForStep(cause.getGate().getName(), cause.getTimestamp());
            Map<Integer, BlockVariableHistoryItem> causeHistory = this.history().ofVariable(cause.getGate().getName());
            boolean changeFound = false;
            for (int i = cause.getTimestamp() - 1; i > 0; i--) {
                final BlockVariableHistoryItem causeHistoryItem = causeHistory.get(i);
                if (!causeHistoryItem.getValueHolder().getValue().equals(val.getValue())) {
                    final int causeChangeStep = i;
                    res.add(new ChangeCauseNode(cause.getGate(), this.history().getVariableValueForStep(cause.getGate().getName(), causeChangeStep + 1), causeChangeStep + 1,
                            this.history().getVariableValueForStep(cause.getGate().getName(), causeChangeStep), causeChangeStep));
                    changeFound = true;
                    break;
                }
            }
            // explaining block output for the first step or first set of delay
//            if (cause.getTimestamp() == 1){
//                res.add(new ChangeCauseNode(cause.getGate(), this.history().getVariableValueForStep(cause.getGate().getName(), 1), 1,
//                        this.history().getVariableValueForStep(cause.getGate().getName(), 1), 1));
//            }

            // the second is obvious but to make logic more explicit
            if (!changeFound || cause.getTimestamp() == 1) {
                res.add(new ChangeCauseNode(cause.getGate(), this.history().getVariableValueForStep(cause.getGate().getName(), cause.getTimestamp()), cause.getTimestamp(),
                        this.history().getVariableValueForStep(cause.getGate().getName(), cause.getTimestamp()), cause.getTimestamp()));
            }
        }
        return res;
    }

    private String getStringHash(Gate gate, int timestamp) {
        return gate.getFullName() + timestamp;
    }

    @Override
    public ChangeStayedExplanationItem explainHistoryStayedChangedImpl(OutputGate output, Integer timestamp, Map<String, ChangeStayedExplanationItem> processedNodes) {
        if (processedNodes.containsKey(this.getStringHash(output, timestamp))){
            return processedNodes.get(this.getStringHash(output, timestamp));
        }

        ValueHolder val = this.history().getVariableValueForStep(output.getName(), timestamp);
        Map<Integer, BlockVariableHistoryItem> outputHistory = this.history().ofVariable(output.getName());
        // Search for change
        for (int i = timestamp - 1; i > 0; i--) {
            final BlockVariableHistoryItem outputHistoryItem = outputHistory.get(i);
            if (!outputHistoryItem.getValueHolder().getValue().equals(val.getValue())) {
                final int changeStep = i;
                ChangedStayedCauseNode root = new ChangedStayedCauseNode(output, this.history().getVariableValueForStep(output.getName(), changeStep + 1), changeStep + 1,
                        this.history().getVariableValueForStep(output.getName(), changeStep), changeStep, timestamp);

                if (processedNodes.containsKey(this.getStringHash(output, changeStep + 1))){
                    return processedNodes.get(this.getStringHash(output, changeStep + 1));
                }

                ChangedStayedCausePathTree pathTree = new ChangedStayedCausePathTree(root);
                List<ChangedStayedCauseNode> causes = this.explainHistoryStayedChangedBasicImpl(output, changeStep + 1);
                root.addChildren(causes);
                ChangeStayedExplanationItem res = new ChangeStayedExplanationItem(pathTree, causes);
                return res;
            }
        }

        // or if it's for the first time step
        if (timestamp == 1) {
            ChangedStayedCauseNode root = new ChangedStayedCauseNode(output, this.history().getVariableValueForStep(output.getName(), timestamp), timestamp,
                    this.history().getVariableValueForStep(output.getName(), timestamp), timestamp, timestamp);
            ChangedStayedCausePathTree pathTree = new ChangedStayedCausePathTree(root);
            List<ChangedStayedCauseNode> causes = this.explainHistoryStayedChangedBasicImpl(output, timestamp);
            root.addChildren(causes);
            ChangeStayedExplanationItem res = new ChangeStayedExplanationItem(pathTree, causes);
            return res;
        }

        // or if there're no changes found
//        ChangedStayedCauseNode root = new ChangedStayedCauseNode(output, this.history().getVariableValueForStep(output.getName(), timestamp), timestamp,
//                this.history().getVariableValueForStep(output.getName(), timestamp), timestamp, timestamp);
        List<ChangedStayedCauseNode> roots = this.explainStayedBasicImpl(output, timestamp, processedNodes);
        ChangedStayedCausePathTree pathTree = new ChangedStayedCausePathTree(roots);
        List<ChangedStayedCauseNode> freshNodes =  roots.stream().flatMap(ch -> ch.getChildren().stream()).collect(Collectors.toList());
        ChangeStayedExplanationItem res = new ChangeStayedExplanationItem(pathTree, freshNodes);
        return res;
    }

    private List<ChangedStayedCauseNode> explainHistoryStayedChangedBasicImpl(OutputGate output, Integer changeStep) {
        List<CauseNode> causes = this.explainBasicImpl(output, changeStep);
        List<ChangedStayedCauseNode> res = new ArrayList<>();
        for (CauseNode cause : causes) {
            ValueHolder val = this.history().getVariableValueForStep(cause.getGate().getName(), cause.getTimestamp());
            Map<Integer, BlockVariableHistoryItem> causeHistory = this.history().ofVariable(cause.getGate().getName());
            boolean changeFound = false;
            for (int i = cause.getTimestamp() - 1; i > 0; i--) {
                final BlockVariableHistoryItem causeHistoryItem = causeHistory.get(i);
                if (!causeHistoryItem.getValueHolder().getValue().equals(val.getValue())) {
                    final int causeChangeStep = i;
                    res.add(new ChangedStayedCauseNode(cause.getGate(), this.history().getVariableValueForStep(cause.getGate().getName(), causeChangeStep + 1), causeChangeStep + 1,
                            this.history().getVariableValueForStep(cause.getGate().getName(), causeChangeStep), causeChangeStep, cause.getTimestamp()));
                    changeFound = true;
                    break;
                }
            }
            // the second is obvious but to make logic more explicit
            if (!changeFound || cause.getTimestamp() == 1) {
                res.add(new ChangedStayedCauseNode(cause.getGate(), this.history().getVariableValueForStep(cause.getGate().getName(), cause.getTimestamp()), cause.getTimestamp(),
                        this.history().getVariableValueForStep(cause.getGate().getName(), cause.getTimestamp()), cause.getTimestamp(), cause.getTimestamp()));
            }
        }
        return res;
    }

    private List<ChangedStayedCauseNode> explainStayedBasicImpl(OutputGate output, Integer timestamp, Map<String, ChangeStayedExplanationItem> processedCauses){
        List<ChangedStayedCauseNode> rootNodes = new ArrayList<>();
        for (int time = timestamp; time > 0; time --){
            ChangedStayedCauseNode root = new ChangedStayedCauseNode(output, this.history().getVariableValueForStep(output.getName(), time), time,
                    this.history().getVariableValueForStep(output.getName(), time), time, time);
            if (!processedCauses.containsKey(this.getStringHash(output, time))) {
                for (CauseNode cause : this.explainBasicImpl(output, time)) {
                    ChangedStayedCauseNode child = new ChangedStayedCauseNode(cause.getGate(), this.history().getVariableValueForStep(cause.getGate().getName(), cause.getTimestamp()), cause.getTimestamp(),
                            this.history().getVariableValueForStep(cause.getGate().getName(), cause.getTimestamp()), cause.getTimestamp(), cause.getTimestamp());
                    root.addChildNode(child);
                }
                rootNodes.add(root);
            } else{
                rootNodes.addAll(processedCauses.get(this.getStringHash(output, time)).getTree().getRoots());
            }
        }
        return rootNodes;
    }

    @Override
    public List<CauseFinalNode> explainFinal(Gate gate, int timestamp, CauseFinalNode parent, CausePathFinalGraph graph){
        ExplanationItem exp = this.explainImpl((OutputGate) gate, timestamp);
        CauseNode oldNode = exp.getTree().getRoots().get(0);
        CauseFinalNode newNode = new CauseFinalNode(oldNode.getGate(), oldNode.getValue(), oldNode.getTimestamp(), parent, graph);

        List<CauseFinalNode> newNodes = new ArrayList<>();
        for (CauseNode node : exp.getFreshNodes()) {
            CauseFinalNode childNode = new CauseFinalNode(node.getGate(), node.getValue(), node.getTimestamp(), newNode, graph);
            if (exp.getFreshNodes().stream().anyMatch(n -> n.equals(node))){
                newNodes.add(childNode);
            }
        }
        return newNodes;
    }

}