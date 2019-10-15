package shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition;

public class ConnectionDefinition {
    private long fromVarId;
    private long toVarId;

    private boolean isInverted;

    public ConnectionDefinition(long fromVarId, long toVarId) {
        this.fromVarId = fromVarId;
        this.toVarId = toVarId;
    }

    public ConnectionDefinition(long fromVarId, long toVarId, String isInverted) {
        this(fromVarId, toVarId);
        this.isInverted = isInverted != null && isInverted.toLowerCase().equals("true");
    }

    public long getFromVarId() {
        return this.fromVarId;
    }

    public long getToVarId() {
        return this.toVarId;
    }

    public boolean isInverted() {
        return this.isInverted;
    }
}
