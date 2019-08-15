package shakeanapple.backtracker.core.calculation;

import shakeanapple.backtracker.core.model.counterexample.Counterexample;
import shakeanapple.backtracker.core.model.counterexample.State;

public class CalculationCursor {
    private final Counterexample counterexample;
    private int curStep;

    public CalculationCursor(Counterexample counterexample) {
        this.counterexample = counterexample;
    }

    public State getCurState(){
        return this.counterexample.getPath().get(this.curStep);
    }

    public State moveNext(){
        this.curStep ++;
        return this.getCurState();
    }

    public State pickNext(){
        return this.counterexample.getPath().get(this.curStep + 1);
    }
}
