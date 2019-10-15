package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

public class Connection<TVal extends ValueHolder> {
    private final DiagramElement from;
    private final OutputVariable<TVal> fromVar;

    private final DiagramElement to;
    private final InputVariable<TVal> toVar;

    private boolean isInverted;

    public Connection(DiagramElement from, OutputVariable<TVal> fromVar, DiagramElement to, InputVariable<TVal> toVar) {
        this.from = from;
        this.fromVar = fromVar;
        this.to = to;
        this.toVar = toVar;
    }

    public Connection(boolean isInverted, DiagramElement from, OutputVariable<TVal> fromVar, DiagramElement to, InputVariable<TVal> toVar) {
        this(from, fromVar, to, toVar);
        this.isInverted = isInverted;
    }

    public DiagramElement from() {
        return this.from;
    }

    public OutputVariable<TVal> fromVar() {
        return this.fromVar;
    }

    public DiagramElement to() {
        return this.to;
    }

    public InputVariable<TVal> toVar() {
        return this.toVar;
    }

    public boolean isInverted() {
        return this.isInverted;
    }
}
