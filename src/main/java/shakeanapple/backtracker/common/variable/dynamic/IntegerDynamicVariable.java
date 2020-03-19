package shakeanapple.backtracker.common.variable.dynamic;

import shakeanapple.backtracker.common.variable.IntegerValueHolder;

public class IntegerDynamicVariable extends DynamicVariable<IntegerValueHolder> {

    private IntegerValueHolder value;

    public IntegerDynamicVariable(IntegerValueHolder value, String name) {
        super(name);
        this.value = value;
    }

    @Override
    public IntegerValueHolder getValue() {
        return this.value;
    }

    @Override
    public void setValue(IntegerValueHolder value) {
        this.value = value;
    }

    @Override
    public DynamicVariable<IntegerValueHolder> clone() {
        return new IntegerDynamicVariable(this.value.clone(), this.getName());
    }
}
