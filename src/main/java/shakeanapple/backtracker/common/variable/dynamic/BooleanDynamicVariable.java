package shakeanapple.backtracker.common.variable.dynamic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;

public class BooleanDynamicVariable extends DynamicVariable<BooleanValueHolder> {

    private BooleanValueHolder value;

    public BooleanDynamicVariable(BooleanValueHolder value, String name) {
        super(name);
        this.value = value;
    }

    @Override
    public BooleanValueHolder getValue() {
        return this.value;
    }


    @Override
    public void setValue(BooleanValueHolder value) {
        this.value = value;
    }

    @Override
    public DynamicVariable<BooleanValueHolder> clone() {
        return new BooleanDynamicVariable(this.value.clone(), this.getName());
    }
}
