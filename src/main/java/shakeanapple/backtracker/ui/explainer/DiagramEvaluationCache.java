package shakeanapple.backtracker.ui.explainer;

import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagramEvaluationCache {
    private Map<Integer, DiagramSnapshot> history = new HashMap<>();

    public void add(DiagramSnapshot snapshot, int timestamp){
        this.history.put(timestamp, snapshot);
    }

    public DiagramSnapshot getByStep(int timestamp){
        return this.history.get(timestamp);
    }
}
