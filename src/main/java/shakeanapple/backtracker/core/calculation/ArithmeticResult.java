package shakeanapple.backtracker.core.calculation;

import shakeanapple.backtracker.core.model.variable.AbstractValueHolder;
import shakeanapple.backtracker.core.model.variable.IntegerValueHolder;

public class ArithmeticResult extends CalculationResult<AbstractValueHolder> {
    private AbstractValueHolder result;

    public ArithmeticResult(AbstractValueHolder result) {
        this.result = result;
    }

    public ArithmeticResult(int res){
        this.result = new IntegerValueHolder(res);
    }

    @Override
    public AbstractValueHolder getValue() {
        return this.result;
    }
}
