package shakeanapple.backtracker.core.variable;

public interface Variable<TValue extends AbstractValueHolder> {
    TValue getValue();
    VariableInfo getInfo();
}
