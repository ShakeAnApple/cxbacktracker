package shakeanapple.backtracker.core.diagramexplanation.model.changecausetree;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.Gate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChangeCauseNode {
    private Gate gate;
    private boolean isRoot;

    private Change change;
    private List<ChangeCauseNode> children = new ArrayList<>();

    public ChangeCauseNode(Gate gate, ValueHolder currentValue, int currentStep, ValueHolder changedValue, int changedStep) {
        this.gate = gate;
        this.change = new Change (currentValue, currentStep, changedValue, changedStep);
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

    public Change getChange() {
        return this.change;
    }

    public boolean hasValueChanged(){
        return this.change.getChangedStep() != this.change.getCurrentStep();
    }

    public List<ChangeCauseNode> getChildren() {
        return this.children;
    }

    public void addChildNode(ChangeCauseNode child){
        this.children.add(child);
    }

    @Override
    public boolean equals(Object obj) {
        ChangeCauseNode other = (ChangeCauseNode) obj;
        if (other == null){
            return false;
        }
        return other.change.equals(this.change) &&
                other.getGate().equals(this.gate);
    }

    @Override
    public int hashCode() {
        return (this.change.toString() + this.gate).hashCode();
    }

    public void addChildren(Collection<ChangeCauseNode> outputCauseNodes) {
        this.children.addAll(outputCauseNodes);
    }

    @Override
    public String toString() {
        return "CauseNode{" +
                gate.getName() + " " + gate.getOwner().getName() +
                '}';
    }
}
