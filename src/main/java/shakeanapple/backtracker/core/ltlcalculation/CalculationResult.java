package shakeanapple.backtracker.core.ltlcalculation;

public abstract class CalculationResult<TRes>{
    public abstract TRes getValue();

    @Override
    public abstract String toString();
}
