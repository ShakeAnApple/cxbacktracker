package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.CounterexampleCursor;
import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.FunctionBlockSnapshot;

import java.util.HashMap;
import java.util.Map;

public class DiagramCounterexampleExecutor implements DiagramExecutor {

    private final FunctionBlockComplex diagram;
    private final CounterexampleCursor cursor;

    private HashMap<Integer, DiagramSnapshot> stepsEvaluated = new HashMap<>();
    private int maxEvaluatedStepNum = -1;

    public DiagramCounterexampleExecutor(FunctionBlockComplex diagram, Counterexample counterexample) {
        this.diagram = diagram;

        this.cursor = new CounterexampleCursor(counterexample);
    }

    @Override
    public DiagramSnapshot moveNext() {
        if (this.cursor.hasNext()) {
            this.cursor.moveNext();
            if (!this.stepsEvaluated.containsKey(this.cursor.getCurStateNum())) {
                this.diagram.tickSystemTime();
                this.evaluateDiagram();
                DiagramSnapshot snapshot = DiagramSnapshot.fromDiagram(this.diagram);
                this.stepsEvaluated.put(this.cursor.getCurStateNum(), snapshot);
                this.maxEvaluatedStepNum++;
                return snapshot;
            } else{
                return this.stepsEvaluated.get(this.cursor.getCurStateNum());
            }
        }
        return DiagramSnapshot.fromDiagram(this.diagram);
    }

    @Override
    public DiagramSnapshot moveTo(int stepNum) {
        if (this.stepsEvaluated.containsKey(stepNum)) {
            this.cursor.goTo(stepNum);
            return this.stepsEvaluated.get(stepNum);
        } else {
            this.cursor.goTo(this.maxEvaluatedStepNum);
            DiagramSnapshot lastSnapshot = null;
            while (this.cursor.getCurStateNum() < stepNum) {
                lastSnapshot = this.moveNext();
            }
            return lastSnapshot;
        }
    }

    @Override
    public boolean hasSnapshotFor(int stepNum) {
        if (this.stepsEvaluated.containsKey(stepNum)){
            return true;
        }
        return this.cursor.hasNext();
    }

    @Override
    public Map<String,ValueHolder> extractInputSnapshotFor(int stepNum, String blockName) {
        DiagramSnapshot diagramSnapshot = this.stepsEvaluated.get(stepNum);
        FunctionBlockSnapshot snapshot =  diagramSnapshot.getBlocks().stream().filter(block -> block.getName().equals(blockName)).findFirst().orElse(null);
        return snapshot.getFbInterface().getInputsValues();
    }

    private void evaluateDiagram() {
        for (InputGate inGate : this.diagram.fbInterface().getInputs().values()) {
            inGate.populateInput(this.cursor.getCurState().getVarByName(inGate.getName()).getValue());
        }

        for (DiagramElement de : this.diagram.getInternalDiagram().getFunctionBlocks()) {
            FunctionBlockBase fb = (FunctionBlockBase) de;
            // System.out.println("Block: " + fb.getName() + " ouput records: " + fb.history().outputRecordsCount());
            for (OutputGate outGate : fb.fbInterface().getOutputs().values()) {
                String varName = fb.getName() + "." + outGate.getName();
                ValueHolder cxValue = this.cursor.getCurState().getVarByName(varName).getValue();
                ValueHolder calculatedValue = outGate.getValue();
                if (!cxValue.equals(calculatedValue)) {
                    System.out.println(String.format("%s: %s has value %s in cx and %s after evaluation", this.cursor.getCurStateNum(), varName, cxValue, calculatedValue));
                }
            }
        }

        this.diagram.history().record(this.diagram.fbInterface(), this.diagram.getSystemTime());
    }
}
