package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.FunctionBlockSnapshot;

import java.util.HashMap;
import java.util.Map;

public class SubDiagramCounterexampleExecutorNew implements DiagramExecutor {
    private final FunctionBlockComplex diagram;
    private final DiagramInputSource inputSource;

    private HashMap<Integer, DiagramSnapshot> stepsEvaluated = new HashMap<>();
    private int maxEvaluatedStepNum = -1;

    public SubDiagramCounterexampleExecutorNew(FunctionBlockComplex diagram, DiagramInputSource inputSource) {
        this.diagram = diagram;

        this.inputSource = inputSource;
    }

    @Override
    public DiagramSnapshot moveNext() {
        if (this.inputSource.hasNext()) {
            this.inputSource.moveNext();
            if (!this.stepsEvaluated.containsKey(this.inputSource.getCurStateNum())) {
                DiagramSnapshot snapshot = DiagramSnapshot.fromDiagram(this.diagram);
                this.stepsEvaluated.put(this.inputSource.getCurStateNum(), snapshot);
                this.diagram.history().record(this.diagram.fbInterface(), Clocks.instance().currentTime());
                this.maxEvaluatedStepNum++;
                return snapshot;
            } else{
                return this.stepsEvaluated.get(this.inputSource.getCurStateNum());
            }
        }
        return DiagramSnapshot.fromDiagram(this.diagram);
    }

    @Override
    public DiagramSnapshot moveTo(int stepNum) {
        if (this.stepsEvaluated.containsKey(stepNum)) {
            this.inputSource.goTo(stepNum);
            return this.stepsEvaluated.get(stepNum);
        } else {
            this.inputSource.goTo(this.maxEvaluatedStepNum);
            DiagramSnapshot lastSnapshot = null;
            while (this.inputSource.getCurStateNum() < stepNum) {
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
        return this.inputSource.hasNext();
    }

    @Override
    public Map<String, ValueHolder> extractInputsSnapshotFor(int stepNum, String blockName) {
        DiagramSnapshot diagramSnapshot = this.stepsEvaluated.get(stepNum);
        FunctionBlockSnapshot blockSnapshot = diagramSnapshot.getBlocks().stream().filter(block -> block.getName().equals(blockName)).findFirst().orElse(null);
        return blockSnapshot.getFbInterface().getInputsValues();
    }

    private void evaluateDiagram() {
        for (InputGate inGate : this.diagram.fbInterface().getInputs().values()) {
            inGate.populateInput(this.inputSource.getCurState().get(inGate.getName()));
        }

        for (DiagramElement de : this.diagram.getInternalDiagram().getFunctionBlocks()) {
            FunctionBlockBase fb = (FunctionBlockBase) de;
            // System.out.println("Block: " + fb.getName() + " ouput records: " + fb.history().outputRecordsCount());
            for (OutputGate outGate : fb.fbInterface().getOutputs().values()) {
                String varName = fb.getName() + "." + outGate.getName();
                ValueHolder cxValue = this.inputSource.getCurState().get(varName);
                ValueHolder calculatedValue = outGate.getValue();
                if (!cxValue.equals(calculatedValue)) {
                    //      System.out.println(String.format("%s has value %s in cx and %s after evaluation", varName, cxValue, calculatedValue));
                }
            }
        }

        this.diagram.history().record(this.diagram.fbInterface(), Clocks.instance().currentTime());
    }
}
