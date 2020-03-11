package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.common.variable.ValueHolder;

import java.util.Map;

public class SnapshotInputSource implements DiagramInputSource {
    private final DiagramExecutor parentExecutor;
    private final FunctionBlockInputsHistory inputsHistory = new FunctionBlockInputsHistory();
    private final String blockName;

    private int currentStep = -1;
    private int maxExecutedStep = -1;

    public SnapshotInputSource(DiagramExecutor parentExecutor, String blockName) {
        this.parentExecutor = parentExecutor;
        this.blockName = blockName;
    }

    @Override
    public boolean hasNext() {
        if (this.maxExecutedStep > this.currentStep) {
            return true;
        } else{
            return this.parentExecutor.hasSnapshotFor(this.currentStep + 1);
        }
    }

    @Override
    public void moveNext() {
        if (this.maxExecutedStep <= this.currentStep) {
            this.parentExecutor.moveTo(this.currentStep + 1);
            this.inputsHistory.record(this.currentStep + 1,
                    this.parentExecutor.extractInputsSnapshotFor(this.currentStep + 1, this.blockName));
        }
        this.currentStep++;
        this.maxExecutedStep = Math.max(this.currentStep, this.maxExecutedStep);
    }

    @Override
    public int getCurStateNum() {
        return this.currentStep;
    }

    @Override
    public void goTo(int stepNum) {
        if (this.maxExecutedStep < stepNum) {
            while (this.currentStep < stepNum){
                this.moveNext();
            }
        }
        this.currentStep = stepNum;
        this.maxExecutedStep = Math.max(this.currentStep, this.maxExecutedStep);
    }

    @Override
    public Map<String, ValueHolder> getCurState() {
        return this.inputsHistory.getVarsValuesByStep(this.currentStep);
    }
}
