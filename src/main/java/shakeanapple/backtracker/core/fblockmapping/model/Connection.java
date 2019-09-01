package shakeanapple.backtracker.core.fblockmapping.model;

import shakeanapple.backtracker.common.variable.AbstractValueHolder;
import shakeanapple.backtracker.core.fblockmapping.model.variable.InputVariable;
import shakeanapple.backtracker.core.fblockmapping.model.variable.OutputVariable;

public class Connection<TVal extends AbstractValueHolder> {
    private final FunctionBlock from;
    private final OutputVariable<TVal> fromVar;

    private final FunctionBlock to;
    private final InputVariable<TVal> toVar;

    private boolean isInverted;

    public Connection(FunctionBlock from, OutputVariable<TVal> fromVar, FunctionBlock to, InputVariable<TVal> toVar) {
        this.from = from;
        this.fromVar = fromVar;
        this.to = to;
        this.toVar = toVar;
    }

    public Connection(boolean isInverted, FunctionBlock from, OutputVariable<TVal> fromVar, FunctionBlock to, InputVariable<TVal> toVar) {
        this(from, fromVar, to, toVar);
        this.isInverted = isInverted;
    }

    public FunctionBlock from() {
        return this.from;
    }

    public OutputVariable<TVal> fromVar() {
        return this.fromVar;
    }

    public FunctionBlock to() {
        return this.to;
    }

    public InputVariable<TVal> toVar() {
        return this.toVar;
    }

    public boolean isInverted() {
        return this.isInverted;
    }
}
