package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.HashMap;
import java.util.Map;

public class BlockInterfaceHistory {

    private Map<String, BlockVariableHistory> inputVariablesHistory = new HashMap<>();
    private Map<String, BlockVariableHistory> outputVariablesHistory = new HashMap<>();

    public BlockInterfaceHistory(FBInterface fbInterface) {
        for (InputGate inGate: fbInterface.getInputs().values()){
            this.inputVariablesHistory.put(inGate.getName(), new BlockVariableHistory(inGate.getName()));
        }

        for (OutputGate outGate: fbInterface.getOutputs().values()){
            this.outputVariablesHistory.put(outGate.getName(), new BlockVariableHistory(outGate.getName()));
        }
    }

    public void record(FBInterface fbInterface,Integer timestamp){
        for (InputGate inGate: fbInterface.getInputs().values()){
            this.recordInput(inGate.input(), timestamp);
        }

        for (OutputGate outGate: fbInterface.getOutputs().values()){
            this.recordOutput(outGate.output(), timestamp);
        }
    }

    private void recordInput(InputVariable var, Integer timestamp){
        this.inputVariablesHistory.get(var.getName()).record(var, timestamp);
    }

    private void recordOutput(OutputVariable var, Integer timestamp){
        this.outputVariablesHistory.get(var.getName()).record(var, timestamp);
    }

    public ValueHolder getVariableValueForStep(String varName, int timestamp){
        if (this.inputVariablesHistory.containsKey(varName)){
            return this.inputVariablesHistory.get(varName).getValueForStep(timestamp);
        }
        return this.outputVariablesHistory.get(varName).getValueForStep(timestamp);
    }
}
