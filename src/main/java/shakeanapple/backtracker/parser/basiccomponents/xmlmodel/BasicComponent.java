package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.BasicComponentDefinition;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.BasicComponentDefinitionAbstract;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.ComponentDefinitionType;

import java.util.List;
import java.util.stream.Collectors;

public class BasicComponent extends BasicComponentAbstract{
    @JacksonXmlElementWrapper(localName = "inputs")
    @JacksonXmlProperty(localName = "input")
    private List<InputVariable> inputs;

    @JacksonXmlElementWrapper(localName = "outputs")
    @JacksonXmlProperty(localName = "output")
    private List<OutputVariable> outputs;

    public BasicComponent() {
    }

    public BasicComponent(ComponentType type, long id, List<InputVariable> inputs, List<OutputVariable> outputs) {
        super(type, id);
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public List<InputVariable> getInputs() {
        return this.inputs;
    }

    public void setInputs(List<InputVariable> inputs) {
        this.inputs = inputs;
    }

    public List<OutputVariable> getOutputs() {
        return this.outputs;
    }

    public void setOutputs(List<OutputVariable> outputs) {
        this.outputs = outputs;
    }

    @Override
    public BasicComponentDefinitionAbstract translate() {
        return new BasicComponentDefinition(ComponentDefinitionType.valueOf(this.getType().name()), super.getId(),
                this.inputs.stream().map(InputVariable::translate).collect(Collectors.toList()),
                this.outputs.stream().map(OutputVariable::translate).collect(Collectors.toList()));
    }
}
