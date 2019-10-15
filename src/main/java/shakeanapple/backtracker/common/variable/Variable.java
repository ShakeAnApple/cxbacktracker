package shakeanapple.backtracker.common.variable;

public abstract class Variable<TValue extends ValueHolder> {
    private final String name;

    protected Variable(String name) {
        this.name = name;
    }

    public abstract TValue getValue();

    public String getName(){
        return this.name;
    }
}
