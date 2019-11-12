package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.Variable;

import java.util.HashMap;
import java.util.Map;

public class BlockVariableHistory {
    private String name;
    private Map<Integer, BlockVariableHistoryItem> valuesByStep = new HashMap<>();

    public BlockVariableHistory(String name) {
        this.name = name;
    }

    public void record(Variable var, Integer timestamp){
        this.valuesByStep.put(timestamp, new BlockVariableHistoryItem(var.getName(), var.getValue(), timestamp));
    }

    public String getName() {
        return this.name;
    }

    public ValueHolder getValueForStep(int timestamp){
        return this.valuesByStep.get(timestamp).getValueHolder();
    }
}
