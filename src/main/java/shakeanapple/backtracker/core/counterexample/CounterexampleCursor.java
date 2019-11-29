package shakeanapple.backtracker.core.counterexample;

import shakeanapple.backtracker.core.diagramexplanation.Clocks;

public class CounterexampleCursor {
    private final Counterexample counterexample;
    private int curStep;

    public CounterexampleCursor(Counterexample counterexample) {
        this.counterexample = counterexample;
        this.curStep = -1;
    }

    private CounterexampleCursor(Counterexample counterexample, int curStep){
        this(counterexample);
        this.curStep = curStep;
    }

    public State getCurState(){
        return this.counterexample.getPath().get(this.curStep);
    }

    public State moveNext(){
        if (this.counterexample.getPath().get(this.curStep + 1) == null){
            this.curStep = this.counterexample.getLoopStart();
        } else{
            this.curStep ++;
        }
        return this.getCurState();
    }

    public boolean hasNext() {
        return this.counterexample.getLoopStart() > 0 || this.curStep < this.counterexample.length();
    }

    public int getCurStateNum() {
        return this.curStep;
    }

    public CounterexampleCursor branch() {
        return new CounterexampleCursor(this.counterexample, this.curStep);
    }

    public CounterexampleCursor branchNext() {
        CounterexampleCursor branch = new CounterexampleCursor(this.counterexample, this.curStep);
        branch.moveNext();
        return branch;
    }

    public boolean goTo(int stepNum) {
        if (this.counterexample.length() < stepNum){
            return false;
        }
        this.curStep = stepNum;
        return true;
    }

    public boolean isEndOfPath() {
        return this.counterexample.getLoopStart() == -1 && this.curStep == this.counterexample.length() - 1 ;
    }
}
