package shakeanapple.backtracker.core.diagramexplanation.model.variable;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;

public abstract class FBVariable<TVal extends ValueHolder> extends DynamicVariable<TVal> {
    private final DynamicVariable<TVal> variable;
    private boolean isUnset;

    private long id;

    public FBVariable(DynamicVariable<TVal> variable, long id) {
        super(variable.getName());
        this.variable = variable;
        this.id = id;
    }

    public long getId() {
        return this.id;
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
