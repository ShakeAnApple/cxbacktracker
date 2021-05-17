package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BasicBlocksIdGenerator;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.*;
import java.util.stream.Collectors;

public class ChoiceFunctionBlockBasic extends FunctionBlockBasic {

    private final List<Choice> choices;
    private final OutputVariable output;

    private final Map<Integer, Choice> executedChoices = new HashMap<>();

    public ChoiceFunctionBlockBasic(boolean generateId, List<Choice> choices, OutputVariable output, String pathInSystem) {
        super("Choice" + (generateId ? BasicBlocksIdGenerator.next("Choice") : ""),
                inferInputs(choices),
                new ArrayList<>() {{
                    add(output);
                }}, pathInSystem
        );
        this.choices = choices;
        this.output = output;
    }

    private ChoiceFunctionBlockBasic(String name, List<Choice> choices, OutputVariable output, String pathInSystem) {
        super(name,
                inferInputs(choices),
                new ArrayList<>() {{
                    add(output);
                }}, pathInSystem
        );
        this.choices = choices;
        this.output = output;
    }

    private static List<InputVariable> inferInputs(List<Choice> choices) {
        List<InputVariable> inputs = choices.stream().map(Choice::getCondition).collect(Collectors.toList());
        inputs.addAll(choices.stream().map(Choice::getOutput).collect(Collectors.toList()));
        return inputs;
    }

    public List<Choice> getChoices() {
        return this.choices;
    }

    @Override
    public void executeImpl() {
        for (Choice choice : this.choices) {
            if (((BooleanValueHolder) choice.getCondition().getValue()).getValue()) {
                this.executedChoices.put(super.getSystemTime(), choice.clone());
//                System.out.println("Executed choices:");
//                this.executedChoices.entrySet().stream().forEach((kv) -> {
//                    System.out.println("Step: " + kv.getKey() + " Choice: " + kv.getValue().toString());
//                });
                super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(choice.getOutput().getValue());
                break;
            }
        }
    }

    @Override
    public FunctionBlockBase clone() {
        return new ChoiceFunctionBlockBasic(this.getName(), this.choices.stream().map(Choice::clone).collect(Collectors.toList()), this.output.clone(), this.getStringPathInSystem());
    }

    @Override
    protected List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp) {
//        System.out.println("Executed choices:");
//        this.executedChoices.entrySet().stream().forEach((kv) -> {
//            System.out.println("Step: " + kv.getKey() + " Choice: " + kv.getValue().toString());
//        });
//        return newExplain(output, timestamp);

        Choice executedChoice = this.executedChoices.get(timestamp);
        List<CauseNode> causeNodes = this.choices.stream()
                .takeWhile(ch -> ch.getOrder() <= executedChoice.getOrder())
                .map(ch -> new CauseNode(super.fbInterface().getInputs().get(ch.getCondition().getName()),
                        super.history().getVariableValueForStep(ch.getCondition().getName(), timestamp),
                        timestamp)).collect(Collectors.toList());
        causeNodes.add(new CauseNode(super.fbInterface().getInputs().get(executedChoice.getOutput().getName()), super.history().getVariableValueForStep(executedChoice.getOutput().getName(), timestamp), timestamp));
        return causeNodes;
    }

    private List<CauseNode> newExplain(OutputGate output, Integer timestamp) {
        Choice executedChoice = this.executedChoices.get(timestamp);
        List<Choice> failedChoices = this.choices.stream()
                .takeWhile(ch -> ch.getOrder() < executedChoice.getOrder()).collect(Collectors.toList());
        ValueHolder resultOutputValue = super.history().getVariableValueForStep(executedChoice.getOutput().getName(), timestamp);
        List<Choice> possibleCauses = new ArrayList<>();
        for (Choice choice : failedChoices) {
            ValueHolder choiceOutputValue = super.history().getVariableValueForStep(choice.getOutput().getName(), timestamp);
            if (!choiceOutputValue.equals(resultOutputValue)) {
                possibleCauses.add(choice);
            } else {
                System.out.println("eliminated " + choice);
            }
        }
        possibleCauses.add(this.choices.stream().filter(ch -> ch.getOrder() == executedChoice.getOrder()).findFirst().get());
        List<CauseNode> causeNodes = possibleCauses.stream()
                .map(ch -> new CauseNode(super.fbInterface().getInputs().get(ch.getCondition().getName()),
                        super.history().getVariableValueForStep(ch.getCondition().getName(), timestamp),
                        timestamp)).collect(Collectors.toList());
        causeNodes.add(new CauseNode(super.fbInterface().getInputs().get(executedChoice.getOutput().getName()), super.history().getVariableValueForStep(executedChoice.getOutput().getName(), timestamp), timestamp));
        return causeNodes;
    }

    @Override
    protected List<ChangeCauseNode> explainChangeBasicImpl(OutputGate output, Integer changeStep) {
        Choice changeExecutedChoice = this.executedChoices.get(changeStep);
        Choice nextExecutedChoice = this.executedChoices.get(changeStep + 1);
        if (changeExecutedChoice.getOrder() == nextExecutedChoice.getOrder()) {
            return Collections.singletonList(new ChangeCauseNode(this.fbInterface().getInputs().get(changeExecutedChoice.getOutput().getName()),
                    this.history().getVariableValueForStep(changeExecutedChoice.getOutput().getName(), changeStep + 1), changeStep + 1,
                    this.history().getVariableValueForStep(changeExecutedChoice.getOutput().getName(), changeStep), changeStep));
        }

        List<ChangeCauseNode> result = new ArrayList<>();
        if (nextExecutedChoice.getOrder() > changeExecutedChoice.getOrder()) {
            result.addAll(
                    this.choices.stream().filter(choice -> choice.getOrder() <= nextExecutedChoice.getOrder())
                            .map(choice -> {
                                ValueHolder changeCondition = history().getVariableValueForStep(choice.getCondition().getName(), changeStep);
                                ValueHolder nextCondition = history().getVariableValueForStep(choice.getCondition().getName(), changeStep + 1);
                                if (!changeCondition.getValue().equals(nextCondition.getValue())) {
                                    return new ChangeCauseNode(fbInterface().getInputs().get(choice.getCondition().getName()),
                                            nextCondition, changeStep + 1, changeCondition, changeStep);
                                }
                                return null;
                            }).filter(Objects::nonNull).collect(Collectors.toList())
            );
        } else {
            result.add(new ChangeCauseNode(fbInterface().getInputs().get(nextExecutedChoice.getCondition().getName()),
                    history().getVariableValueForStep(nextExecutedChoice.getCondition().getName(), changeStep + 1),
                    changeStep + 1, history().getVariableValueForStep(nextExecutedChoice.getCondition().getName(), changeStep), changeStep));
        }
        ValueHolder changeChoiceOutput = this.history().getVariableValueForStep(nextExecutedChoice.getOutput().getName(), changeStep);
        ValueHolder changeChoiceNextOutput = this.history().getVariableValueForStep(nextExecutedChoice.getOutput().getName(), changeStep + 1);
        if (!changeChoiceOutput.getValue().equals(changeChoiceNextOutput.getValue())) {
            result.add(new ChangeCauseNode(this.fbInterface().getInputs().get(nextExecutedChoice.getOutput().getName()), changeChoiceNextOutput, changeStep + 1, changeChoiceOutput, changeStep));
        }
        return result;

    }
}
