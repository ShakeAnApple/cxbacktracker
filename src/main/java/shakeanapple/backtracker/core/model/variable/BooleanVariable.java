package shakeanapple.backtracker.core.model.variable;

public class BooleanVariable extends Variable<BooleanValueHolder> {
    private final BooleanValueHolder value;

    public BooleanVariable(BooleanValueHolder value, String name) {
        super(name);
        this.value = value;
    }

    @Override
    public BooleanValueHolder getValue() {
        return this.value;
    }

}
