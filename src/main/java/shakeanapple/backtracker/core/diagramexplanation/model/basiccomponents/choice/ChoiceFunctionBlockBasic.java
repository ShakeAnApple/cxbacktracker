package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.Cause;
import shakeanapple.backtracker.core.diagramexplanation.Clocks;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChoiceFunctionBlockBasic extends FunctionBlockBasic {

    private final List<Choice> choices;
    private final OutputVariable output;

    private final Map<Integer, Choice> executedChoices = new HashMap<>();

    public ChoiceFunctionBlockBasic(List<Choice> choices, OutputVariable output) {
        super("Choice",
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
                this.executedChoices.put(Clocks.instance().currentTime(), choice.clone());
                System.out.println("Executed choices:");
                this.executedChoices.entrySet().stream().forEach((kv) -> {
                    System.out.println("Step: " + kv.getKey() + " Choice: " + kv.getValue().toString());
                });
                super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(choice.getOutput().getValue());
                break;
            }
        }
    }

    @Override
    protected List<Cause> explainImpl(OutputGate output, Integer timestamp) {
        System.out.println("Executed choices:");
        this.executedChoices.entrySet().stream().forEach((kv) -> {
            System.out.println("Step: " + kv.getKey() + " Choice: " + kv.getValue().toString());
        });
        Choice executedChoice = this.executedChoices.get(timestamp);
        List<Cause> causes = this.choices.stream()
                .takeWhile(ch -> ch.getOrder() <= executedChoice.getOrder())
                .map(ch -> new Cause(super.fbInterface().getInputs().get(ch.getCondition().getName()),
                        super.history().getVariableValueForStep(ch.getCondition().getName(), timestamp),
                        timestamp)).collect(Collectors.toList());
        causes.add(new Cause(super.fbInterface().getInputs().get(executedChoice.getOutput().getName()), super.history().getVariableValueForStep(executedChoice.getOutput().getName(), timestamp), timestamp));
        return causes;
    }
}
