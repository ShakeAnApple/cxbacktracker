package shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.Gate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChangedStayedCauseNode {
    private Gate gate;
    private boolean isRoot;
    private Change change;
    private int criticalStepNumber;



    private List<ChangedStayedCauseNode> children = new ArrayList<>();


    public ChangedStayedCauseNode(Gate gate, ValueHolder currentValue, int currentStep, ValueHolder changedValue, int changedStep, int criticalStepNumber) {
        this.gate = gate;
        this.change = new Change(currentValue, currentStep, changedValue, changedStep);
        this.criticalStepNumber = criticalStepNumber;
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

    public boolean isChangeCause(){
        return this.change.getPrevStep() != this.change.getNewStep();
    }

    public List<ChangedStayedCauseNode> getChildren() {
        return this.children;
    }

    public void addChildNode(ChangedStayedCauseNode child){
        this.children.add(child);
    }

    public int getCriticalStepNumber() {
        return this.criticalStepNumber;
    }

    @Override
    public boolean equals(Object obj) {
        ChangedStayedCauseNode other = (ChangedStayedCauseNode) obj;
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

    public void addChildren(Collection<ChangedStayedCauseNode> outputCauseNodes) {
        this.children.addAll(outputCauseNodes);
    }

    @Override
    public String toString() {
        return "CauseNode{" +
                gate.getName() + " " + gate.getOwner().getName() +
                '}';
    }
}
