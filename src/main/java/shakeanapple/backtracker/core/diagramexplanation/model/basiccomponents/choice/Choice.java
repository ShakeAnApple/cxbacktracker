package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;

public class Choice<TVal extends ValueHolder> {
    private final InputVariable<BooleanValueHolder> condition;
    private final InputVariable<TVal> output;

    public Choice(InputVariable<BooleanValueHolder> condition, InputVariable<TVal> output) {
        this.condition = condition;
        this.output = output;
    }

    public InputVariable<BooleanValueHolder> getCondition() {
        return this.condition;
    }

    public InputVariable<TVal> getOutput() {
        return this.output;
    }
}
