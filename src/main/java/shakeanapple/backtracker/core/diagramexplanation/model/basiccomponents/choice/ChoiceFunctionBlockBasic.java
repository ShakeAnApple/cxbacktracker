package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChoiceFunctionBlockBasic  extends FunctionBlockBasic {

    private final List<Choice> choices;
    private final OutputVariable output;

    public ChoiceFunctionBlockBasic(List<Choice> choices, OutputVariable output) {
        super("Choice",
                inferInputs(choices),
                new ArrayList<>() {{add(output);}}
                );
        this.choices = choices;
        this.output = output;
    }

    private static List<InputVariable> inferInputs(List<Choice> choices){
        List<InputVariable> inputs = choices.stream().map(ch -> ch.getCondition()).collect(Collectors.toList());
        inputs.addAll(choices.stream().map(ch -> ch.getOutput()).collect(Collectors.toList()));
        return inputs;
    }

    @Override
    public void execute() {
        for (Choice choice : this.choices){
            if (((BooleanValueHolder)choice.getCondition().getValue()).getValue()){
                super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(choice.getOutput().getValue());
                break;
            }
        }
    }
}
