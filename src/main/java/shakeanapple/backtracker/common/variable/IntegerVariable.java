package shakeanapple.backtracker.common.variable;

public class IntegerVariable extends Variable<IntegerValueHolder> {
    private final IntegerValueHolder value;

    public IntegerVariable(IntegerValueHolder value, String name) {
        super(name);
        this.value = value;
    }

    @Override
    public IntegerValueHolder getValue() {
        return this.value;
    }

}
