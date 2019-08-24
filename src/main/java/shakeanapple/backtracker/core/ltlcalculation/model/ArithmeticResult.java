package shakeanapple.backtracker.core.ltlcalculation.model;

import shakeanapple.backtracker.common.variable.AbstractValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;

public class ArithmeticResult extends CalculationResult<AbstractValueHolder> {
    private AbstractValueHolder result;

    public ArithmeticResult(AbstractValueHolder result, int forStep) {
        super(forStep);
        this.result = result;
    }

    public ArithmeticResult(int res, int forStep){
        super(forStep);
        this.result = new IntegerValueHolder(res);
    }

    @Override
    public AbstractValueHolder getValue() {
        return this.result;
    }

    @Override
    public String toString() {
        return result.getValue().toString();
    }
}
