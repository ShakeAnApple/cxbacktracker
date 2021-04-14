package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.core.diagramexplanation.model.Gate;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.changecausetree.Change;
import shakeanapple.backtracker.core.diagramexplanation.model.changecausetree.ChangeExplanationItem;

import java.util.Collection;
import java.util.List;

// FIXME I'm ugly
public interface DiagramOutputExplainer {
    ExplanationItem explain(String outputName, List<String> blockPath, int timestamp);

    ChangeExplanationItem explainChange(String outputName, List<String> blockPath, int timestamp);

    List<Gate> extractNonObviousConstants(ExplanationItem explanationResult);
}
