package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.core.diagramexplanation.model.Gate;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;

import java.util.Collection;
import java.util.List;

public interface DiagramOutputExplainer {
    ExplanationItem explain(String outputName, List<String> blockPath, int timestamp);

    List<Gate> extractNonObviousConstants(ExplanationItem explanationResult);
}
