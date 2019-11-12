package shakeanapple.backtracker.core.diagramexplanation.model;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FBInterface implements InputUpdatedListener {
    private Map<String, OutputGate> outputs;
    private Map<String, InputGate> inputs;

    private List<InputGate> orderedInputs;

    private InterfaceUpdatedEvent interfaceUpdatedEvent;

    public FBInterface(List<InputGate> inputs, List<OutputGate> outputs) {
        this.outputs = outputs.stream().collect(Collectors.toMap(OutputGate::getName, o -> o));
        this.inputs = inputs.stream().collect(Collectors.toMap(InputGate::getName, i -> i));

        this.orderedInputs = inputs.stream().sorted(Comparator.comparing(inputGate -> inputGate.input().getOrder())).collect(Collectors.toList());

        this.interfaceUpdatedEvent = new InterfaceUpdatedEvent(this.inputs);

        this.orderedInputs.forEach(in -> in.inputUpdatedEvent().addListener(this));
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

    public Event interfaceUpdatedEvent(){
        return this.interfaceUpdatedEvent;
    }

    @Override
    public void onInputUpdated(Gate gate) {

        this.interfaceUpdatedEvent.tryFire(gate);
    }
}
