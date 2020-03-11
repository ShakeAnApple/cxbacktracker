package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.CounterexampleCursor;

import java.util.Map;
import java.util.stream.Collectors;

public class CounterexampleInputSource implements DiagramInputSource {

    private final CounterexampleCursor cursor;

    public CounterexampleInputSource(Counterexample cx) {
        this.cursor = new CounterexampleCursor(cx);
    }

    @Override
    public boolean hasNext() {
        return this.cursor.hasNext();
    }

    @Override
    public void moveNext() {
        this.cursor.moveNext();
    }

    @Override
    public int getCurStateNum() {
        return this.cursor.getCurStateNum();
    }

    @Override
    public void goTo(int stepNum) {
        this.cursor.goTo(stepNum);
    }

    @Override
    public Map<String, ValueHolder> getCurState() {
        return this.cursor.getCurState().getVarsByNames().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, (entry) -> entry.getValue().getValue()));
    }
}
