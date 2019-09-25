package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.AbstractValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;

public class AssignComponent<TVal extends AbstractValueHolder> extends Component<TVal, TVal> {

    private final InputVariable<TVal> input;
    private final OutputVariable<TVal> output;


    protected AssignComponent(InputVariable<TVal> input, OutputVariable<TVal> output) {
        super(new ArrayList<>() {{
            add(input);
        }}, new ArrayList<>() {{
            add(output);
        }});

        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() {
        this.output.assignValue(
                this.input.getValue()
        );
    }
}
