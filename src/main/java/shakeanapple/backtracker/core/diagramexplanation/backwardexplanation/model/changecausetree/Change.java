package shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree;

import shakeanapple.backtracker.common.variable.ValueHolder;

public class Change {
    // current value - the one we encountered during traversing the diagram
    private ValueHolder currentValue;
    private int currentStep;

    // changed value - last time when the gate value was different
    private ValueHolder changedValue;
    private int changedStep;

    public Change(ValueHolder currentValue, int currentStep, ValueHolder changedValue, int changedStep) {

        this.currentValue = currentValue;
        this.currentStep = currentStep;
        this.changedValue = changedValue;
        this.changedStep = changedStep;
    }

    public ValueHolder getCurrentValue() {
        return this.currentValue;
    }

    public int getCurrentStep() {
        return this.currentStep;
    }

    public ValueHolder getChangedValue() {
        return this.changedValue;
    }

    public int getChangedStep() {
        return this.changedStep;
    }

    @Override
    public boolean equals(Object obj) {
        Change other = (Change) obj;
        if (other == null){
            return false;
        }
        return other.currentStep == this.currentStep &&
                other.changedStep == this.changedStep &&
                other.currentValue.equals(this.currentValue) &&
                other.changedValue.equals(this.changedValue);
    }

    @Override
    public String toString() {
        return String.format("(%s,%s) -> (%s,%s)", this.changedValue, this.changedStep, this.currentValue, this.currentStep);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
