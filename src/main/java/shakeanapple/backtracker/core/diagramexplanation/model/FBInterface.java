package shakeanapple.backtracker.core.diagramexplanation.model;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FBInterface implements InputUpdatedListener, OutputUpdatedListener {
    private Map<String, OutputGate> outputs;
    private Map<String, InputGate> inputs;

    private List<InputGate> orderedInputs;

    private InterfaceUpdatedEvent inputInterfaceUpdatedEvent;
    private InterfaceUpdatedEvent outputInterfaceUpdatedEvent;

    public FBInterface(List<InputGate> inputs, List<OutputGate> outputs) {
        this.outputs = outputs.stream().collect(Collectors.toMap(OutputGate::getName, o -> o));
        this.inputs = inputs.stream().collect(Collectors.toMap(InputGate::getName, i -> i));

        this.orderedInputs = inputs.stream().sorted(Comparator.comparing(inputGate -> inputGate.input().getOrder())).collect(Collectors.toList());

        this.inputInterfaceUpdatedEvent = new InterfaceUpdatedEvent(this.inputs.values().stream().map(in -> (Gate)in).collect(Collectors.toMap(Gate::getName, g -> g)), true);
        this.outputInterfaceUpdatedEvent = new InterfaceUpdatedEvent(this.outputs.values().stream().map(out -> (Gate)out).collect(Collectors.toMap(Gate::getName, g -> g)), false);

        this.orderedInputs.forEach(in -> in.inputUpdatedEvent().addListener(this));
        this.outputs.values().forEach(out -> out.outputUpdatedEvent().addListener(this));
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

    public Event inputInterfaceUpdatedEvent(){
        return this.inputInterfaceUpdatedEvent;
    }

    @Override
    public void onInputUpdated(Gate gate) {
        this.inputInterfaceUpdatedEvent.tryFire(gate);
    }

    @Override
    public void onOutputUpdated(Gate gate) {
        this.outputInterfaceUpdatedEvent.tryFire(gate);
    }

    public Event outputInterfaceUpdatedEvent() {
        return this.outputInterfaceUpdatedEvent;
    }
}
