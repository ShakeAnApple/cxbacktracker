package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice;

import shakeanapple.backtracker.common.variable.AbstractValueHolder;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.Component;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChoiceComponent<TOut extends AbstractValueHolder>  extends Component<BooleanValueHolder, TOut> {

    private final List<Choice<TOut>> choices;
    private final OutputVariable<TOut> output;

    public ChoiceComponent(List<Choice<TOut>> choices, OutputVariable<TOut> output) {
        super(
                choices.stream().map(c -> c.getCondition()).collect(Collectors.toList()),
                new ArrayList<>() {{add(output);}}
                );
        this.choices = choices;
        this.output = output;
    }

    @Override
    public void execute() {
        for (Choice<TOut> choice : this.choices){
            if (choice.getCondition().getValue().getValue()){
                this.output.assignValue(choice.getOutput().getValue());
                break;
            }
        }
    }
}
