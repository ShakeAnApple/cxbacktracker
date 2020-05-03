package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BasicBlocksIdGenerator;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.Clocks;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.*;
import java.util.stream.Collectors;

public class ChoiceFunctionBlockBasic extends FunctionBlockBasic {

    private final List<Choice> choices;
    private final OutputVariable output;

    private final Map<Integer, Choice> executedChoices = new HashMap<>();

    public ChoiceFunctionBlockBasic(boolean generateId,List<Choice> choices, OutputVariable output) {
        super("Choice"+ (generateId ? BasicBlocksIdGenerator.next("Choice") : ""),
                inferInputs(choices),
                new ArrayList<>() {{
                    add(output);
                }}
        );
        this.choices = choices;
        this.output = output;
    }

    private ChoiceFunctionBlockBasic(String name,List<Choice> choices, OutputVariable output) {
        super(name,
                inferInputs(choices),
                new ArrayList<>() {{
                    add(output);
                }}
        );
        this.choices = choices;
        this.output = output;
    }

    private static List<InputVariable> inferInputs(List<Choice> choices) {
        List<InputVariable> inputs = choices.stream().map(Choice::getCondition).collect(Collectors.toList());
        inputs.addAll(choices.stream().map(Choice::getOutput).collect(Collectors.toList()));
        return inputs;
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
        return new ChoiceFunctionBlockBasic(this.getName(), this.choices.stream().map(Choice::clone).collect(Collectors.toList()), this.output.clone());
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

    private List<CauseNode> newExplain(OutputGate output, Integer timestamp){
        Choice executedChoice = this.executedChoices.get(timestamp);
        List<Choice> failedChoices = this.choices.stream()
                .takeWhile(ch -> ch.getOrder() < executedChoice.getOrder()).collect(Collectors.toList());
        ValueHolder resultOutputValue = super.history().getVariableValueForStep(executedChoice.getOutput().getName(), timestamp);
        List<Choice> possibleCauses = new ArrayList<>();
        for (Choice choice: failedChoices){
            ValueHolder choiceOutputValue = super.history().getVariableValueForStep(choice.getOutput().getName(), timestamp);
            if (!choiceOutputValue.equals(resultOutputValue)){
                possibleCauses.add(choice);
            } else{
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
}
