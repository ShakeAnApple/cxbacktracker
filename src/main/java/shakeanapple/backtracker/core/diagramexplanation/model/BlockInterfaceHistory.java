package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockInterfaceHistory {

    private Map<String, BlockVariableHistory> inputVariablesHistory = new HashMap<>();
    private Map<String, BlockVariableHistory> outputVariablesHistory = new HashMap<>();

    public BlockInterfaceHistory(FBInterface fbInterface) {
        for (InputGate inGate : fbInterface.getInputs().values()) {
            this.inputVariablesHistory.put(inGate.getName(), new BlockVariableHistory(inGate.getName()));
        }

        for (OutputGate outGate : fbInterface.getOutputs().values()) {
            this.outputVariablesHistory.put(outGate.getName(), new BlockVariableHistory(outGate.getName()));
        }
    }

    public void record(FBInterface fbInterface, Integer timestamp) {
        for (InputGate inGate : fbInterface.getInputs().values()) {
            this.recordInput(inGate.input(), timestamp);
        }

        for (OutputGate outGate : fbInterface.getOutputs().values()) {
            this.recordOutput(outGate.output(), timestamp);
        }
    }

    public BlockVariableHistoryItem lastChangeForVarBeforeStep(String name, int currentStep) {
        ValueHolder curVal;
        BlockVariableHistory history;
        if (this.inputVariablesHistory.containsKey(name)) {
            history = this.inputVariablesHistory.get(name);
            curVal = history.getValueForStep(currentStep);
        } else {
            history = this.outputVariablesHistory.get(name);
            curVal = history.getValueForStep(currentStep);
        }

        ValueHolder prevVal;
        for (int timestamp = currentStep - 1; timestamp > 0; timestamp--) {
            prevVal = history.getValueForStep(timestamp);
            if (!prevVal.equals(curVal)){
                return history.getItem(timestamp);
            }
        }
        return history.getItem(currentStep);
    }

    public int outputRecordsCount() {
        return this.outputVariablesHistory.values().stream().findFirst().get().varRecordsCount();
    }

    private void recordInput(InputVariable var, Integer timestamp) {
        this.inputVariablesHistory.get(var.getName()).record(var, timestamp);
    }

    private void recordOutput(OutputVariable var, Integer timestamp) {
        this.outputVariablesHistory.get(var.getName()).record(var, timestamp);
    }

    public ValueHolder getVariableValueForStep(String varName, int timestamp) {
        if (this.inputVariablesHistory.containsKey(varName)) {
            return this.inputVariablesHistory.get(varName).getValueForStep(timestamp);
        }
        return this.outputVariablesHistory.get(varName).getValueForStep(timestamp);
    }

    public Map<Integer, BlockVariableHistoryItem> ofVariable(String varName) {
        if (this.inputVariablesHistory.containsKey(varName)) {
            return this.inputVariablesHistory.get(varName).valuesByStep();
        }
        return this.outputVariablesHistory.get(varName).valuesByStep();
    }
}
