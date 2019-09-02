package shakeanapple.backtracker.core.fblockmapping;

import shakeanapple.backtracker.core.fblockmapping.model.snapshot.DiagramSnapshot;

public interface DiagramSequentialEvaluator {
    DiagramSnapshot moveNext();
}
