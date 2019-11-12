package shakeanapple.backtracker.core.diagramexplanation;

import java.util.List;

public interface DiagramOutputExplainer {
    List<Cause> explain(String outputName, String blockName, int timestamp);
}
