package shakeanapple.backtracker.ui.basiccomponentsconstructor.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BasicComponent extends BasicComponentAbstract {
    private List<InputVariable> inputs;

    private List<OutputVariable> outputs;

    public BasicComponent(ComponentType type, long id, List<InputVariable> inputs, List<OutputVariable> outputs) {
        super(type, id);
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public List<InputVariable> getInputs() {
        return this.inputs;
    }

    public List<OutputVariable> getOutputs() {
        return this.outputs;
    }

    @Override
    public shakeanapple.backtracker.parser.basiccomponents.xmlmodel.BasicComponentAbstract translate() {
        List<shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable> inputsXml = this.inputs.stream().map(InputVariable::translate).collect(Collectors.toList());
        List<shakeanapple.backtracker.parser.basiccomponents.xmlmodel.OutputVariable> outputsXml = this.outputs.stream().map(OutputVariable::translate).collect(Collectors.toList());

        return new shakeanapple.backtracker.parser.basiccomponents.xmlmodel.BasicComponent(shakeanapple.backtracker.parser.basiccomponents.xmlmodel.ComponentType.valueOf(super.getType().name()), this.getId(), inputsXml, outputsXml);
    }
}
