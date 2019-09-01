package shakeanapple.backtracker.common.variable.dynamic;

import shakeanapple.backtracker.common.variable.AbstractValueHolder;
import shakeanapple.backtracker.common.variable.Variable;

public abstract class DynamicVariable<TVal extends AbstractValueHolder> extends Variable<TVal> {

    protected DynamicVariable(String name) {
        super(name);
    }

    @Override
    public abstract TVal getValue();

    public abstract void setValue(TVal val);
}
