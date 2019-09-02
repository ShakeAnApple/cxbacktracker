package shakeanapple.backtracker.common.variable;

public class BooleanValueHolder extends AbstractValueHolder<Boolean> {
    private boolean _value;

    public BooleanValueHolder(boolean value) {
        _value = value;
    }

    @Override
    public Boolean getValue() {
        return _value;
    }

    @Override
    public String toString() {
        return ((Integer) (_value ? 1 : 0)).toString();
    }

    @Override
    public void invert() {
        _value = !_value;
    }

    @Override
    public boolean equals(Object obj) {
        return _value == ((BooleanValueHolder) obj)._value;
    }
}
