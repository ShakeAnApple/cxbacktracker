package shakeanapple.backtracker.core.ltl.evaluation.model;

public abstract class CalculationResult<TRes>{
    private final int forStep;

    public CalculationResult(int forStep) {
        this.forStep = forStep;
    }

    public int forStep(){
        return this.forStep;
    }

    public abstract TRes getValue();

    @Override
    public abstract String toString();
}
