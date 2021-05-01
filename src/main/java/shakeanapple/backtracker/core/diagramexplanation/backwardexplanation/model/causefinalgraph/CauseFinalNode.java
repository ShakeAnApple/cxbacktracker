package shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.Gate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CauseFinalNode {
    private Gate gate;
    private boolean isRoot;

    private ValueHolder value;
    private int step;

    private ValueHolder prevValue;
    // so change step = prevStep + 1
    private int prevStep;

    private CausePathFinalGraph owner;
    private CauseFinalNode parent;

    private List<CauseFinalNode> children = new ArrayList<>();

    public boolean isTimeNode() {
        return this.isTimeNode;
    }

    public void isTimeNode(boolean timeNode) {
        isTimeNode = timeNode;
    }

    private boolean isTimeNode;

    public CauseFinalNode(Gate gate, ValueHolder value, int step, CauseFinalNode parent, CausePathFinalGraph owner) {
        this.gate = gate;
        this.value = value;
        this.step = step;
        this.owner = owner;
        this.parent = parent;

        if (this.parent == null){
            this.isRoot = true;
        } else{
            this.parent.addChildNode(this);
        }

        this.owner.addNode(this);
    }

    public CauseFinalNode(Gate gate, ValueHolder value, int step, CausePathFinalGraph owner, CauseFinalNode parent, ValueHolder prevValue, int prevStep) {
        this(gate, value, step, parent, owner);
        this.prevValue = prevValue;
        this.prevStep = prevStep;
    }

    public boolean isRoot() {
        return this.isRoot;
    }

    public Gate getGate() {
        return this.gate;
    }

    public boolean hasValueChanged() {
        return this.prevValue != null && !this.prevValue.equals(this.value) && this.step != this.prevStep;
    }

    public List<CauseFinalNode> getChildren() {
        return this.children;
    }

    public void addChildNode(CauseFinalNode child) {
        this.children.add(child);
        this.owner.removeLeaf(this);
    }

    public void addChildren(Collection<CauseFinalNode> outputCauseNodes) {
        this.children.addAll(outputCauseNodes);
        this.owner.removeLeaf(this);
    }

    // clear parents and dispose childre, but a child can have several parents as we live in a graph!
    public void clearChildren(){
        this.children.clear();
        this.owner.addLeaf(this);
    }

    @Override
    public boolean equals(Object obj) {
        CauseFinalNode other = (CauseFinalNode) obj;
        if (other == null) {
            return false;
        }
        return other.getGate().equals(this.gate) && other.value.equals(this.value) && other.step == this.step
                && ((other.prevValue != null && this.prevValue != null) ? other.prevValue.equals(this.prevValue) : other.prevValue == null && this.prevValue == null)
                && other.prevStep == this.prevStep && other.isRoot == this.isRoot;
    }


    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return this.gate.getFullName() + (this.isRoot ? "(root)" : "") + ":" + this.step + ":" + this.value;
    }

    public int getStep() {
        return this.step;
    }

    public ValueHolder getValue() {
        return this.value;
    }
}
