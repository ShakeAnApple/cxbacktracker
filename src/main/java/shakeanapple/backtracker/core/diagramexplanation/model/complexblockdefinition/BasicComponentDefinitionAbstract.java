package shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition;

import java.util.List;

public abstract class BasicComponentDefinitionAbstract {
    private long id;
    private ComponentDefinitionType type;

    private List<InputDefinition> inputs;
    private List<OutputDefinition> outputs;

    public BasicComponentDefinitionAbstract(ComponentDefinitionType type, long id, List<InputDefinition> inputs, List<OutputDefinition> outputs) {
        this.type = type;
        this.id = id;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public ComponentDefinitionType getType() {
        return this.type;
    }

    public long getId(){
        return this.id;
    }

    public List<InputDefinition> getInputs() {
        return this.inputs;
    }

    public List<OutputDefinition> getOutputs() {
        return this.outputs;
    }
}
