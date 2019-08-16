package shakeanapple.backtracker.core.calculation;

import shakeanapple.backtracker.core.model.counterexample.Counterexample;
import shakeanapple.backtracker.core.model.counterexample.State;

public class CounterexampleCursor {
    private final Counterexample counterexample;
    private int curStep;

    public CounterexampleCursor(Counterexample counterexample) {
        this.counterexample = counterexample;
    }

    private CounterexampleCursor(Counterexample counterexample, int curStep){
        this(counterexample);
        this.curStep = curStep;
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

    public boolean isEndOfPath() {
        return this.curStep == this.counterexample.length();
    }

    public int getCurStateNum() {
        return this.curStep;
    }

    public CounterexampleCursor branch() {
        return new CounterexampleCursor(this.counterexample, this.curStep);
    }
}
