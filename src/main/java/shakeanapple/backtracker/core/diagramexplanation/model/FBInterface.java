package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.model.InputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FBInterface {
    private Map<String, OutputGate> outputs;
    private Map<String, InputGate> inputs;

    private List<InputGate> orderedInputs;

    public FBInterface(List<InputGate> inputs, List<OutputGate> outputs) {
        this.outputs = outputs.stream().collect(Collectors.toMap(OutputGate::getName, o -> o));
        this.inputs = inputs.stream().collect(Collectors.toMap(InputGate::getName, i -> i));

        this.orderedInputs = inputs;
    }

    public List<InputGate> getOrderedInputs(){
        return this.orderedInputs;
    }

    public Map<String, OutputGate> getOutputs() {
        return this.outputs;
    }

    public Map<String, InputGate> getInputs() {
        return this.inputs;
    }

}
