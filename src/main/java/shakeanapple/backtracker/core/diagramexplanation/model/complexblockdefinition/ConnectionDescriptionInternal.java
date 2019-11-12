package shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition;

import shakeanapple.backtracker.core.diagramexplanation.model.Gate;

class ConnectionDescriptionInternal {
    private Gate toGate;
    private boolean isInverted;

    public ConnectionDescriptionInternal(Gate toGate, boolean isInverted) {
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
