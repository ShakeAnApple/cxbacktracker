package shakeanapple.backtracker.parser.fblockdiagram.xmlbasiccomponents;

import shakeanapple.backtracker.core.diagramexplanation.model.Gate;

public class ConnectionDescription {
    private Gate toGate;
    private boolean isInverted;

    public ConnectionDescription(Gate toGate, boolean isInverted) {
        this.toGate = toGate;
        this.isInverted = isInverted;
    }

    public Gate getToGate() {
        return this.toGate;
    }

    public boolean isInverted() {
        return this.isInverted;
    }
}
