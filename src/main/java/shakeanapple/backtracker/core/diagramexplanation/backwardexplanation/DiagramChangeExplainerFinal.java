package shakeanapple.backtracker.core.diagramexplanation.backwardexplanation;

import shakeanapple.backtracker.core.diagramexplanation.DiagramOutputExplainer;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph.CauseFinalNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph.CausePathFinalGraph;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeCausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangeStayedExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.*;

import java.util.*;

public class DiagramChangeExplainerFinal implements DiagramOutputExplainer {

    private FunctionBlockComplex diagram;

    public DiagramChangeExplainerFinal(FunctionBlockComplex diagram) {
        this.diagram = diagram;
    }

    @Override
    public CausePathFinalGraph explainFinal(String gateName, List<String> blockPath, int timestamp) {
        CausePathFinalGraph resultGraph = new CausePathFinalGraph();

        FunctionBlockBase fbToExplain = this.diagram;
        for (int i = 0; i < blockPath.size(); i++) {
            String nextName = blockPath.get(i);
            fbToExplain = ((FunctionBlockComplex)fbToExplain).getInternalDiagram().getFunctionBlocks().stream()
                    .filter(fb -> fb.getName().equals(nextName))
                    .findFirst().orElse(null);
            if (fbToExplain == null) break;
        }

        if (fbToExplain == null) {
            OutputGate outputGate = this.diagram.fbInterface().getOutputs().get(gateName);
            if (outputGate != null) {
                this.diagram.explainFinal(outputGate, timestamp, null, resultGraph);
                return resultGraph;
            }
            InputGate inputGate = this.diagram.fbInterface().getInputs().get(gateName);
            if (inputGate != null) {
                CauseFinalNode root = new CauseFinalNode(inputGate, this.diagram.history().getVariableValueForStep(inputGate.getName(), timestamp), timestamp, null, resultGraph);
                return resultGraph;
            }
        } else {
            Gate gateToExplain = fbToExplain.fbInterface().getOutputs().get(gateName);
            if (gateToExplain == null) {
                gateToExplain = fbToExplain.fbInterface().getInputs().get(gateName);
            }
            if (gateToExplain == null){
                throw new RuntimeException("Can't find the Gate " + gateName + " in Block " + fbToExplain.getName());
            }
            this.diagram.explainFinal(gateToExplain, timestamp, null, resultGraph);
            return resultGraph;
        }
        throw new RuntimeException("can't find gate " + gateName + " for block path" + Arrays.toString(blockPath.toArray()));
    }


    @Override
    public ExplanationItem explain(String outputName, List<String> blockPath, int timestamp) {
        return null;
    }

    @Override
    public ChangeExplanationItem explainChange(String outputName, List<String> blockPath, int timestamp) {
        return null;
    }

    @Override
    public List<Gate> extractNonObviousConstants(ExplanationItem explanationResult) {
        return null;
    }

    @Override
    public ChangeStayedExplanationItem explainChangedStayedHistory(String gateName, List<String> blockPath, int timestamp) {
        return null;
    }

    @Override
    public ChangeExplanationItem explainChangeHistory(String gateName, List<String> blockPath, int timestamp) {
        return null;
    }
}
