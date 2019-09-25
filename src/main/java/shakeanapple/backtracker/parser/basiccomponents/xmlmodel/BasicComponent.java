package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class BasicComponent extends BasicComponentAbstract{
    @JacksonXmlElementWrapper(localName = "inputs")
    @JacksonXmlProperty(localName = "input")
    private List<InputVariable> inputs;

    @JacksonXmlElementWrapper(localName = "outputs")
    @JacksonXmlProperty(localName = "output")
    private List<OutputVariable> outputs;

    public BasicComponent() {
    }

    public BasicComponent(ComponentType type, List<InputVariable> inputs, List<OutputVariable> outputs) {
        super(type);
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
}
