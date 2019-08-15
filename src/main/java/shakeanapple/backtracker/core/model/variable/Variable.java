package shakeanapple.backtracker.core.model.variable;

public interface Variable<TValue extends AbstractValueHolder> {
    TValue getValue();
    VariableInfo getInfo();
}
