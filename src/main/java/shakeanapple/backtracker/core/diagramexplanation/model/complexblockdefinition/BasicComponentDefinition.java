package shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition;

import java.util.List;

public class BasicComponentDefinition extends BasicComponentDefinitionAbstract {
    private List<InputDefinition> inputs;

    private List<OutputDefinition> outputs;

    public BasicComponentDefinition(ComponentDefinitionType type, long id, List<InputDefinition> inputs, List<OutputDefinition> outputs) {
        super(type, id, inputs, outputs);
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public List<InputDefinition> getInputs() {
        return this.inputs;
    }

    public List<OutputDefinition> getOutputs() {
        return this.outputs;
    }
}
