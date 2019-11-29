package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;

public interface DiagramSequentialEvaluator {
    DiagramSnapshot moveNext();
    DiagramSnapshot moveTo(int stepNum);
}
