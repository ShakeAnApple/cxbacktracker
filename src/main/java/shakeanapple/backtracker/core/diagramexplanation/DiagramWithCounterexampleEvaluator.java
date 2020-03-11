package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.Variable;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.CounterexampleCursor;
import shakeanapple.backtracker.core.counterexample.State;
import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;

import java.util.Map;

@Deprecated
public class DiagramWithCounterexampleEvaluator implements DiagramExecutor {

    private final FunctionBlockComplex diagram;
    private final CounterexampleCursor cursor;

//    public DiagramWithCounterexampleEvaluator(String diagramPath, String blockDefsPath, Counterexample counterexample) {
//        this.diagram = FunctionBlockComplex.parse(diagramPath, blockDefsPath);
//
//        this.cursor = new CounterexampleCursor(counterexample);
//    }

    public DiagramWithCounterexampleEvaluator(FunctionBlockComplex diagram, Counterexample counterexample) {
        this.diagram = diagram;

        this.cursor = new CounterexampleCursor(counterexample);
    }

    @Override
    public DiagramSnapshot moveNext() {
        if (this.cursor.hasNext()) {
            this.cursor.moveNext();

            this.evaluateDiagram();
        }

        return DiagramSnapshot.fromDiagram(this.diagram);
    }

    @Override
    public DiagramSnapshot moveTo(int stepNum) {
        return null;
    }

    @Override
    public boolean hasSnapshotFor(int stepNum) {
        return false;
    }

    @Override
    public Map<String, ValueHolder> extractInputsSnapshotFor(int stepNum, String blockName) {
        return null;
    }

    private void evaluateDiagram() {
        State curState = this.cursor.getCurState();
        this.diagram.fbInterface().getInputs().values().forEach(in -> {
            in.populateInput(curState.getVarByName(in.getName()).getValue());
        });
        for (DiagramElement fblock: this.diagram.getInternalDiagram().getFunctionBlocks()){
            for (OutputGate outputGate : ((FunctionBlockComplex)fblock).fbInterface().getOutputs().values()) {
                String cxVarName = fblock.getName() + "." + outputGate.getName();
                Variable var = curState.getVarByName(cxVarName);
                if (var != null) {
                    outputGate.assignValue(var.getValue());
                }
            }
        }
    }
}
