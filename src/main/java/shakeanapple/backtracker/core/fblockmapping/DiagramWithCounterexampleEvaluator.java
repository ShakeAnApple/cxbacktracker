package shakeanapple.backtracker.core.fblockmapping;

import shakeanapple.backtracker.common.variable.Variable;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.CounterexampleCursor;
import shakeanapple.backtracker.core.counterexample.State;
import shakeanapple.backtracker.core.fblockmapping.model.Diagram;
import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlock;
import shakeanapple.backtracker.core.fblockmapping.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.fblockmapping.model.variable.OutputVariable;

public class DiagramWithCounterexampleEvaluator implements DiagramSequentialEvaluator {

    private Diagram diagram;
    private final CounterexampleCursor cursor;

    public DiagramWithCounterexampleEvaluator(String diagramPath, Counterexample counterexample) {
        this.diagram = Diagram.load(diagramPath);

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

    private void evaluateDiagram() {
        State curState = this.cursor.getCurState();
        for (FunctionBlock fblock: this.diagram.getFblocks()){
            for (OutputVariable output : fblock.getOutputs().values()) {
                String cxVarName = this.composeName(fblock.getName(), output.getName());
                Variable var = curState.getVarByName(cxVarName);
                if (var != null) {
                    output.assignValue(var.getValue());
                }
            }
        }
    }

    private String composeName(String fblockName, String varName) {
        if (fblockName == "main"){
            return varName;
        }
        return fblockName + "." + varName;
    }
}
