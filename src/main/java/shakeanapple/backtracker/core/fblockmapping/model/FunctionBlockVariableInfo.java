package shakeanapple.backtracker.core.fblockmapping.model;

public class FunctionBlockVariableInfo {
    private final String name;
    private final boolean isInverted;

    private final VariableType type;

    public FunctionBlockVariableInfo(String name, boolean isInverted, VariableType type) {
        this.name = name;
        this.isInverted = isInverted;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public boolean isInverted() {
        return this.isInverted;
    }

    public VariableType type() {
        return this.type;
    }
}
