package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;

import java.util.Collection;
import java.util.List;

public interface DiagramOutputExplainer {
    ExplanationItem explain(String outputName, String blockName, int timestamp);

    ExplanationItem explain(String varName, String blockName, int timestamp, boolean inContext);
}
