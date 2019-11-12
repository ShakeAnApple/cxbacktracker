package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.Gate;

public class Cause {
    private Gate gate;
    private ValueHolder value;
    private int timestamp;

    public Cause(Gate gate, ValueHolder value, int timestamp) {
        this.gate = gate;
        this.value = value;
        this.timestamp = timestamp;
    }

    public Gate getGate() {
        return this.gate;
    }

    public ValueHolder getValue() {
        return this.value;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    @Override
    public boolean equals(Object obj) {
        Cause other = (Cause) obj;
        if (other == null){
            return false;
        }
        return other.timestamp == this.timestamp &&
                other.getValue().equals(this.value) &&
                other.getGate().equals(this.gate);
    }

    @Override
    public int hashCode() {
        return (this.value.toString() + this.timestamp + this.gate).hashCode();
    }
}
