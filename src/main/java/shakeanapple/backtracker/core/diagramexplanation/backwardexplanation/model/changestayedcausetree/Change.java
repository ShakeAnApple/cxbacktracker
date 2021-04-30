package shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree;

import shakeanapple.backtracker.common.variable.ValueHolder;

public class Change {
    // current value - the one we encountered during traversing the diagram
    private ValueHolder newValue;
    private int newStep;

    // changed value - last time when the gate value was different
    private ValueHolder prevValue;
    private int prevStep;

    public Change(ValueHolder newValue, int newStep, ValueHolder prevValue, int prevStep) {

        this.newValue = newValue;
        this.newStep = newStep;
        this.prevValue = prevValue;
        this.prevStep = prevStep;
    }

    public ValueHolder getNewValue() {
        return this.newValue;
    }

    public int getNewStep() {
        return this.newStep;
    }

    public ValueHolder getPrevValue() {
        return this.prevValue;
    }

    public int getPrevStep() {
        return this.prevStep;
    }

    @Override
    public boolean equals(Object obj) {
        Change other = (Change) obj;
        if (other == null){
            return false;
        }
        return other.newStep == this.newStep &&
                other.prevStep == this.prevStep &&
                other.newValue.equals(this.newValue) &&
                other.prevValue.equals(this.prevValue);
    }

    @Override
    public String toString() {
        return String.format("(%s,%s) -> (%s,%s)", this.prevValue, this.prevStep, this.newValue, this.newStep);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
