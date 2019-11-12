package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.core.diagramexplanation.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiagramBackwardExplainer implements DiagramOutputExplainer {

    private FunctionBlockComplex diagram;

    public DiagramBackwardExplainer(FunctionBlockComplex diagram) {
        this.diagram = diagram;
    }

    public List<Cause> explain(String gateName, String blockName, int timestamp){
        OutputGate outputGate = this.diagram.fbInterface().getOutputs().get(gateName);
        if (outputGate != null){
            return this.diagram.explain(outputGate, timestamp);
        }
        InputGate inputGate = this.diagram.fbInterface().getInputs().get(gateName);
        if (inputGate != null){
            return new ArrayList<>();
        }

        FunctionBlockBase fbToExplain = this.diagram.getInternalDiagram().getFunctionBlocks().stream()
                .filter(fb -> fb.getName().equals(blockName))
                .findFirst().orElse(null);

        if (fbToExplain == null){
            throw new RuntimeException("Can't find FBlock for the Gate " + gateName);
        }

        OutputGate out = fbToExplain.fbInterface().getOutputs().get(gateName);
        if (out != null){
            return fbToExplain.explain(out, timestamp);
        }
        InputGate in = fbToExplain.fbInterface().getInputs().get(gateName);
        if (in != null){
            if (in.getIncomingConnection() != null && in.getIncomingConnection().fromGate() instanceof OutputGate) {
                return in.getIncomingConnection().fromGate().getOwner().explain((OutputGate) in.getIncomingConnection().fromGate(), timestamp);
            } else{
                //TODO distinguish constants
                return Collections.singletonList(new Cause((InputGate) in, in.getValue(), timestamp));
            }
        }

        throw new RuntimeException("Can't find the Gate " + gateName + " in Block " + blockName);
    }

}
