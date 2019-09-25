package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.AbstractValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;

public class DelayComponent<TVal extends AbstractValueHolder> extends Component<TVal, TVal> {
    private final InputVariable<TVal> input;

    private final OutputVariable<TVal> output;

    private final int delay;
    private final TVal defVal;

    private int ticksPassed;

    protected DelayComponent(InputVariable<TVal> input, OutputVariable<TVal> output, int delay, TVal defVal) {
        super(new ArrayList<>() {{
            add(input);
        }}, new ArrayList<>() {{
            add(output);
        }});

        this.input = input;
        this.output = output;
        this.delay = delay;
        this.ticksPassed = 0;
        this.defVal = defVal;
    }

    // TODO ask Igor
    @Override
    public void execute() {
        if (this.ticksPassed < this.delay){
            this.output.assignValue(this.defVal);
            this.ticksPassed ++;
        }
        if (this.ticksPassed == this.delay){
            this.output.assignValue(input.getValue());
            this.ticksPassed = 0;
        }
    }
}
