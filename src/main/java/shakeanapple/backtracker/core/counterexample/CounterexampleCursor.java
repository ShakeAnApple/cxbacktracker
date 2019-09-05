package shakeanapple.backtracker.core.counterexample;

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
        this.curStep ++;
        return this.getCurState();
    }

    public State pickNext(){
        return this.counterexample.getPath().get(this.curStep + 1);
    }

    public boolean hasNext() {
        return this.curStep < this.counterexample.length();
    }

    public int getCurStateNum() {
        return this.curStep;
    }

    public CounterexampleCursor branch() {
        return new CounterexampleCursor(this.counterexample, this.curStep);
    }
}
