package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CausePathTree;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DiagramBackwardExplainer implements DiagramOutputExplainer {

    private FunctionBlockComplex diagram;

    public DiagramBackwardExplainer(FunctionBlockComplex diagram) {
        this.diagram = diagram;
    }

    public ExplanationItem explain(String gateName, String blockName, int timestamp){
        OutputGate outputGate = this.diagram.fbInterface().getOutputs().get(gateName);
        if (outputGate != null){
            ExplanationItem item = this.diagram.explain(outputGate, timestamp);
            return item;
        }
        InputGate inputGate = this.diagram.fbInterface().getInputs().get(gateName);
        if (inputGate != null){
            return new ExplanationItem(new CausePathTree(), new ArrayList<>());
        }

        FunctionBlockBase fbToExplain = this.diagram.getInternalDiagram().getFunctionBlocks().stream()
                .filter(fb -> fb.getName().equals(blockName))
                .findFirst().orElse(null);

        if (fbToExplain == null){
            throw new RuntimeException("Can't find FBlock for the Gate " + gateName);
        }

        OutputGate out = fbToExplain.fbInterface().getOutputs().get(gateName);
        if (out != null){
            ExplanationItem item = fbToExplain.explain(out, timestamp);
            return item;
        }
        InputGate in = fbToExplain.fbInterface().getInputs().get(gateName);
        if (in != null){
            if (in.getIncomingConnection() != null && in.getIncomingConnection().fromGate() instanceof OutputGate) {
                ExplanationItem item = in.getIncomingConnection().fromGate().getOwner().explain((OutputGate) in.getIncomingConnection().fromGate(), timestamp);
                return item;
            } else{
                //TODO distinguish constants
                return new ExplanationItem(new CausePathTree(Collections.singletonList(new CauseNode(in, in.getValue(), timestamp))), new ArrayList<>());
            }
        }

        throw new RuntimeException("Can't find the Gate " + gateName + " in Block " + blockName);
    }

}
