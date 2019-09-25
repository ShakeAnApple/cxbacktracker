package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice;

import shakeanapple.backtracker.common.variable.AbstractValueHolder;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

public class Choice<TVal extends AbstractValueHolder> {
    private final InputVariable<BooleanValueHolder> condition;
    private final OutputVariable<TVal> output;

    public Choice(InputVariable<BooleanValueHolder> condition, OutputVariable<TVal> output) {
        this.condition = condition;
        this.output = output;
    }

    public InputVariable<BooleanValueHolder> getCondition() {
        return this.condition;
    }

    public OutputVariable<TVal> getOutput() {
        return this.output;
    }
}
