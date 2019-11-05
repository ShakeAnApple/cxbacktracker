package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.common.variable.ValueHolder;

public class Cause {
    private String varName;
    private ValueHolder value;
    private Integer timestamp;

    public Cause(String varName, ValueHolder value, Integer timestamp) {
        this.varName = varName;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getVarName() {
        return this.varName;
    }

    public ValueHolder getValue() {
        return this.value;
    }

    public Integer getTimestamp() {
        return this.timestamp;
    }
}
