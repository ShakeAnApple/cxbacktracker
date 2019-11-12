package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.CounterexampleCursor;
import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;

public class DiagramCounterexampleExecutor implements DiagramSequentialEvaluator {

    private final FunctionBlockComplex diagram;
    private final CounterexampleCursor cursor;

    public DiagramCounterexampleExecutor(String diagramPath, String blockDefsPath, Counterexample counterexample) {
        this.diagram = FunctionBlockComplex.parse(diagramPath, blockDefsPath);

        this.cursor = new CounterexampleCursor(counterexample);
    }

    public DiagramCounterexampleExecutor(FunctionBlockComplex diagram, Counterexample counterexample) {
        this.diagram = diagram;

        this.cursor = new CounterexampleCursor(counterexample);
    }

    @Override
    public DiagramSnapshot moveNext() {
        if (this.cursor.hasNext()) {
            this.cursor.moveNext();
            Clocks.instance().tick();
            this.evaluateDiagram();
        }
        return DiagramSnapshot.fromDiagram(this.diagram);
    }

    private void evaluateDiagram(){
        for(InputGate inGate: this.diagram.fbInterface().getInputs().values()){
            inGate.populateInput(this.cursor.getCurState().getVarByName(inGate.getName()).getValue());
        }

        for (DiagramElement de: this.diagram.getInternalDiagram().getFunctionBlocks()){
            FunctionBlockBase fb = (FunctionBlockBase) de;
            for (OutputGate outGate: fb.fbInterface().getOutputs().values()){
                String varName = fb.getName() + "." + outGate.getName();
                ValueHolder cxValue = this.cursor.getCurState().getVarByName(varName).getValue();
                ValueHolder calculatedValue = outGate.getValue();
                if (!cxValue.equals(calculatedValue)){
                    System.out.println(String.format("%s has value %s in cx and %s after evaluation", varName, cxValue, calculatedValue));
                }
            }
        }
    }
}
