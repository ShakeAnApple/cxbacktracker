package shakeanapple.backtracker.ui.explainer.control.diagramstayedchangedexplanation;

import shakeanapple.backtracker.common.variable.ValueHolder;

import java.util.Objects;

public class Cause {
    private int timestamp;
    private String varName;
    private String blockName;
    private ValueHolder value;

    public Cause(int timestamp, String varName, String blockName, ValueHolder value) {
        this.timestamp = timestamp;
        this.varName = varName;
        this.value = value;
        this.blockName = blockName;
    }

    public String getBlockName() {
        return this.blockName;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public String getVarName() {
        return this.varName;
    }

    public ValueHolder getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cause cause = (Cause) o;
        return this.timestamp == cause.timestamp &&
                this.varName.equals(cause.varName) &&
                this.blockName.equals(cause.blockName) &&
                this.value.equals(cause.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.timestamp, this.varName, this.blockName, this.value);
    }
}
