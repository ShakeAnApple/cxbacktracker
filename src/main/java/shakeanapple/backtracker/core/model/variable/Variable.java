package shakeanapple.backtracker.core.model.variable;

public abstract class Variable<TValue extends AbstractValueHolder> {
    private final String name;

    protected Variable(String name) {
        this.name = name;
    }

    public abstract TValue getValue();

    public String getName(){
        return this.name;
    }
}
