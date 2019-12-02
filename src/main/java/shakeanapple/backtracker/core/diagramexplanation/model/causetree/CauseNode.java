package shakeanapple.backtracker.core.diagramexplanation.model.causetree;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.Gate;

import java.util.*;

public class CauseNode {
    private Gate gate;
    private boolean isRoot;

    private ValueHolder value;
    private int timestamp;
    private List<CauseNode> children = new ArrayList<>();

    public CauseNode(Gate gate, ValueHolder value, int timestamp) {
        this.gate = gate;
        this.value = value;
        this.timestamp = timestamp;
    }

    public void isRoot(boolean isRoot){
        this.isRoot = isRoot;
    }

    public boolean isRoot() {
        return this.isRoot;
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

    public List<CauseNode> getChildren() {
        return this.children;
    }

    public void addChildNode(CauseNode child){
        this.children.add(child);
    }

    @Override
    public boolean equals(Object obj) {
        CauseNode other = (CauseNode) obj;
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

    public void addChildren(Collection<CauseNode> outputCauseNodes) {
        this.children.addAll(outputCauseNodes);
    }

    @Override
    public String toString() {
        return "CauseNode{" +
                gate.getName() + " " + gate.getOwner().getName() +
                '}';
    }
}
