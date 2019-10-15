package shakeanapple.backtracker.core.diagramexplanation.model.snapshot;

import java.util.List;

public class FBInterfaceSnapshot {
    private final List<String> inputs;
    private final List<String> outputs;

    public FBInterfaceSnapshot(List<String> inputs, List<String> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public List<String> getInputs() {
        return this.inputs;
    }

    public List<String> getOutputs() {
        return this.outputs;
    }
}
