package shakeanapple.backtracker.common.variable.dynamic;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.Variable;

public abstract class DynamicVariable<TVal extends ValueHolder> extends Variable<TVal> {

    protected DynamicVariable(String name) {
        super(name);
    }

    public static DynamicVariable of(String name, ValueHolder value) {
        if (value instanceof BooleanValueHolder){
            return new BooleanDynamicVariable((BooleanValueHolder) value,name);
        } else if (value instanceof IntegerValueHolder){
            return new IntegerDynamicVariable((IntegerValueHolder) value, name);
        }
        return null;
    }

    @Override
    public abstract TVal getValue();

    public abstract void setValue(TVal val);
}
