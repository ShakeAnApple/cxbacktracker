package shakeanapple.backtracker.common.variable;

public abstract class ValueHolder<VType> {
    public abstract VType getValue();

    @Override
    public abstract String toString();

    public abstract ValueHolder<VType> invert();

    @Override
    public abstract ValueHolder<VType> clone();
}
