package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.FunctionBlockSnapshot;

import java.util.Map;

public interface DiagramExecutor {
    DiagramSnapshot moveNext();
    DiagramSnapshot moveTo(int stepNum);
    boolean hasSnapshotFor(int stepNum);
    Map<String, ValueHolder> extractInputSnapshotFor(int stepNum, String blockName);
}
