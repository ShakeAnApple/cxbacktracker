package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.counterexample.State;

import java.util.Map;

public interface DiagramInputSource {
    boolean hasNext();

    void moveNext();

    int getCurStateNum();

    void goTo(int stepNum);

    Map<String, ValueHolder> getCurState();
}
