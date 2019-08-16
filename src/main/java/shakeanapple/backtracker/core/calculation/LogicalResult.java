package shakeanapple.backtracker.core.calculation;

public class LogicalResult extends CalculationResult<LogicalResultKind> {
    private LogicalResultKind result;

    public LogicalResult(LogicalResultKind result) {
        this.result = result;
    }

    @Override
    public LogicalResultKind getValue() {
        return null;
    }
}
