package shakeanapple.backtracker.core.diagramexplanation.model;

public class Connection {
    private final DiagramElement from;
    private final Gate fromGate;

    private final DiagramElement to;
    private final Gate toGate;

    private boolean isInverted;

    public Connection(DiagramElement from, Gate fromGate, DiagramElement to, Gate toGate) {
        this.from = from;
        this.fromGate = fromGate;
        this.to = to;
        this.toGate = toGate;
    }

    public Connection(boolean isInverted, DiagramElement from, Gate fromGate, DiagramElement to, Gate toGate) {
        this(from, fromGate, to, toGate);
        this.isInverted = isInverted;
    }

    public DiagramElement from() {
        return this.from;
    }

    public Gate fromGate() {
        return this.fromGate;
    }

    public DiagramElement to() {
        return this.to;
    }

    public Gate toGate() {
        return this.toGate;
    }

    public boolean isInverted() {
        return this.isInverted;
    }
}
