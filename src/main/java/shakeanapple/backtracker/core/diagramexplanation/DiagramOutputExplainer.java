package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph.CausePathFinalGraph;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangeStayedExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.Gate;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeExplanationItem;

import java.util.List;

// FIXME I'm ugly
public interface DiagramOutputExplainer {
    ExplanationItem explain(String outputName, List<String> blockPath, int timestamp);

    ChangeExplanationItem explainChange(String outputName, List<String> blockPath, int timestamp);

    ChangeExplanationItem explainChangeHistory(String outputName, List<String> blockPath, int timestamp);

    List<Gate> extractNonObviousConstants(ExplanationItem explanationResult);

    ChangeStayedExplanationItem explainChangedStayedHistory(String gateName, List<String> blockPath, int timestamp);

    CausePathFinalGraph explainFinal(String gateName, List<String> blockPath, int timestamp);
}
