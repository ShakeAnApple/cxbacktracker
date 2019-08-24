package shakeanapple.backtracker.common.variable;

public class IntegerValueHolder extends AbstractValueHolder<Integer> {
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
    public boolean equals(Object obj) {
        return this.value == ((IntegerValueHolder) obj).value;
    }
}
