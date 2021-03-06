package shakeanapple.backtracker.core.diagramexplanation.model.snapshot;

import shakeanapple.backtracker.common.variable.ValueHolder;

public class ConnectionSnapshot<TValue extends ValueHolder> {
    private String id;

    private final FunctionBlockSnapshot from;
    private final String fromVarName;

    private final FunctionBlockSnapshot to;
    private final String toVarName;

    private final boolean isInverted;

    private final TValue value;

    public ConnectionSnapshot(FunctionBlockSnapshot from, String fromVarName, FunctionBlockSnapshot to, String toVarName, boolean isInverted, TValue value) {
        this.from = from;
        this.fromVarName = fromVarName;
        this.to = to;
        this.toVarName = toVarName;
        this.isInverted = isInverted;
        this.value = value;

        this.id = (this.from != null ? this.from.getName() : "") + this.fromVarName + (this.to != null ? this.to.getName() : "") + this.toVarName;
    }

    public FunctionBlockSnapshot from() {
        return this.from;
    }

    public String fromVarName() {
        return fromVarName;
    }

    public FunctionBlockSnapshot to() {
        return to;
    }

    public String toVarName() {
        return toVarName;
    }

    public boolean isInverted() {
        return isInverted;
    }

    public TValue getValue() {
        return value;
    }

    public String getId(){
        return this.id;
    }
}
