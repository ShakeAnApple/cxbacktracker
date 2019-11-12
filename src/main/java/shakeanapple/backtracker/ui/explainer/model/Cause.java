package shakeanapple.backtracker.ui.explainer.model;

import shakeanapple.backtracker.common.variable.ValueHolder;

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
}
