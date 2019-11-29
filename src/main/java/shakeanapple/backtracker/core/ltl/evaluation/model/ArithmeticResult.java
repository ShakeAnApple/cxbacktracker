package shakeanapple.backtracker.core.ltl.evaluation.model;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;

public class ArithmeticResult extends CalculationResult<ValueHolder> {
    private ValueHolder result;

    public ArithmeticResult(ValueHolder result, int forStep) {
        super(forStep);
        this.result = result;
    }

    public ArithmeticResult(int res, int forStep){
        super(forStep);
        this.result = new IntegerValueHolder(res);
    }

    @Override
    public ValueHolder getValue() {
        return this.result;
    }

    @Override
    public String toString() {
        return result.getValue().toString();
    }
}
