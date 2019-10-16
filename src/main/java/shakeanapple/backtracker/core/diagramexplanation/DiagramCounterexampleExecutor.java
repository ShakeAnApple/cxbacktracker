package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.CounterexampleCursor;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.model.InputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;

public class DiagramCounterexampleExecutor implements DiagramSequentialEvaluator {

    private final FunctionBlockComplex diagram;
    private final CounterexampleCursor cursor;

    public DiagramCounterexampleExecutor(String diagramPath, String blockDefsPath, Counterexample counterexample) {
        this.diagram = FunctionBlockComplex.parse(diagramPath, blockDefsPath);

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

    private void evaluateDiagram(){
        for(InputGate inGate: this.diagram.fbInterface().getInputs().values()){
            inGate.populateInput(this.cursor.getCurState().getVarByName(inGate.getName()).getValue());
        }
    }
}
