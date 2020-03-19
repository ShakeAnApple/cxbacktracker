package shakeanapple.backtracker.common.variable;

public class IntegerValueHolder extends ValueHolder<Integer> {
    private int value;

    public IntegerValueHolder(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public IntegerValueHolder invert() {
        return new IntegerValueHolder(value);
    }

    @Override
    public boolean equals(Object obj) {
        return this.value == ((IntegerValueHolder) obj).value;
    }

    @Override
    public IntegerValueHolder clone(){
        return new IntegerValueHolder(this.value);
    }
}
