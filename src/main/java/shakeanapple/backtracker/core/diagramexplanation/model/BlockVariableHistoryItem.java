package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.common.variable.ValueHolder;

public class BlockVariableHistoryItem {
    private String varName;
    private ValueHolder valueHolder;
    private int timestamp;

    public BlockVariableHistoryItem(String varName, ValueHolder valueHolder, int timestamp) {
        this.varName = varName;
        this.valueHolder = valueHolder;
        this.timestamp = timestamp;
    }

    public String getVarName() {
        return this.varName;
    }

    public ValueHolder getValueHolder() {
        return this.valueHolder;
    }

    public int getTimestamp() {
        return this.timestamp;
    }
}
