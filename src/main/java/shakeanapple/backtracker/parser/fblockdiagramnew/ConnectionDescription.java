package shakeanapple.backtracker.parser.fblockdiagramnew;

import shakeanapple.backtracker.core.diagramexplanation.model.Gate;

public class ConnectionDescription {
    private String interfaceVarName;
    private Gate fromGate;
    private Gate toGate;
    private boolean isInverted;

    public ConnectionDescription(String interfaceVarName, Gate fromGate, Gate toGate, boolean isInverted) {
        this.toGate = toGate;
        this.fromGate = fromGate;
        this.interfaceVarName = interfaceVarName;
        this.isInverted = isInverted;
    }

    public Gate getToGate() {
        return this.toGate;
    }

    public boolean isInverted() {
        return this.isInverted;
    }

    public String getInterfaceVarName() {
        return this.interfaceVarName;
    }

    public Gate getFromGate() {
        return this.fromGate;
    }
}
