package shakeanapple.backtracker.core.ltlcalculation.model;

public class LogicalResult extends CalculationResult<LogicalResultKind> {
    private LogicalResultKind result;

    public LogicalResult(LogicalResultKind result) {
        this.result = result;
    }

    @Override
    public LogicalResultKind getValue() {
        return this.result;
    }

    @Override
    public String toString() {
        return result.name();
    }
}
