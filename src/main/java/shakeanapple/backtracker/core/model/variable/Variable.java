package shakeanapple.backtracker.core.model.variable;

public interface Variable<TValue extends AbstractValueHolder> {
    public TValue getValue();
    public VariableInfo getInfo();
}
