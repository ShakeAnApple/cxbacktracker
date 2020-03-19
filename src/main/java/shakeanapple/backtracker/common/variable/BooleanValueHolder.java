package shakeanapple.backtracker.common.variable;

public class BooleanValueHolder extends ValueHolder<Boolean> {
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
        return ( (_value ? "TRUE" : "FALSE"));
    }

    @Override
    public BooleanValueHolder invert() {
        return new BooleanValueHolder(!_value);
    }

    @Override
    public boolean equals(Object obj) {
        return _value == ((BooleanValueHolder) obj)._value;
    }

    @Override
    public BooleanValueHolder clone(){
        return new BooleanValueHolder(this.getValue());
    }
}
