package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice;

import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.BooleanDynamicVariable;
import shakeanapple.backtracker.common.variable.dynamic.IntegerDynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;
import shakeanapple.backtracker.ui.explainer.model.graph.cell.InputVarCell;

public class Choice<TVal extends ValueHolder> {
    private final InputVariable<BooleanValueHolder> condition;
    private final InputVariable<TVal> output;
    private int order;

    public Choice(InputVariable<BooleanValueHolder> condition, InputVariable<TVal> output, int order) {
        this.condition = condition;
        this.output = output;
        this.order = order;
    }

    public InputVariable<BooleanValueHolder> getCondition() {
        return this.condition;
    }

    public InputVariable<TVal> getOutput() {
        return this.output;
    }

    public int getOrder() {
        return this.order;
    }

    public Choice clone() {
        InputVariable<BooleanValueHolder> conditionClone = new InputVariable<>(condition.getId(), new BooleanDynamicVariable(condition.getValue(), condition.getName()), condition.getOrder());
        InputVariable outputClone = output.getValue() instanceof IntegerValueHolder ? new InputVariable<>(output.getId(), new IntegerDynamicVariable((IntegerValueHolder) output.getValue(), output.getName()), output.getOrder()) :
                new InputVariable<>(output.getId(), new BooleanDynamicVariable((BooleanValueHolder) output.getValue(), output.getName()), output.getOrder());
        return new Choice(conditionClone, outputClone, order);
    }

    @Override
    public String toString() {
        return "condition=" + condition.getOrder() + " " + condition.getName() +
                ", output=" + output.getOrder() + " " + output.getName() +
                ", order=" + order;
    }
}
