package shakeanapple.backtracker.core.diagramexplanation.model.variable;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;

public class FBVariable<TVal extends ValueHolder> extends DynamicVariable<TVal> {
    private final DynamicVariable<TVal> variable;
    private boolean isUnset;

    public FBVariable(DynamicVariable variable) {
        super(variable.getName());
        this.variable = variable;
    }

    @Override
    public TVal getValue() {
        return this.variable.getValue();
    }

    @Override
    public void setValue(TVal val) {
        this.variable.setValue(val);
    }

    public boolean isUnset() {
        return this.isUnset;
    }

    public void unset(boolean unset) {
        this.isUnset = unset;
    }
}