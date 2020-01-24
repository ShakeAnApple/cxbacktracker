package shakeanapple.backtracker.core.diagramexplanation.model.snapshot;

import shakeanapple.backtracker.common.variable.ValueHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FBInterfaceSnapshot {
    private final List<String> inputs;
    private final List<String> outputs;

    private final Map<String, ValueHolder> inputsValues;
    private final Map<String, ValueHolder> outputsValues;

    public FBInterfaceSnapshot(List<String> inputs, List<String> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;

        this.inputsValues = new HashMap<>();
        this.outputsValues = new HashMap<>();
    }

    public FBInterfaceSnapshot(Map<String, ValueHolder> inputs, Map<String, ValueHolder> outputs) {
        this.inputs = new ArrayList<>(inputs.keySet());
        this.outputs = new ArrayList<>(outputs.keySet());

        this.inputsValues = inputs;
        this.outputsValues = outputs;
    }

    public Map<String, ValueHolder> getInputsValues() {
        return this.inputsValues;
    }

    public Map<String, ValueHolder> getOutputsValues() {
        return this.outputsValues;
    }

    public List<String> getInputs() {
        return this.inputs;
    }

    public List<String> getOutputs() {
        return this.outputs;
    }
}
