package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class ChoiceComponent extends BasicComponentAbstract {
    @JacksonXmlElementWrapper(localName = "choices")
    @JacksonXmlProperty(localName = "choice")
    private List<Choice> choices;

    private OutputVariable output;

    public ChoiceComponent() {
    }

    public ChoiceComponent(ComponentType type, List<Choice> choices, OutputVariable output) {
        super(type);

        this.choices = choices;
        this.output = output;
    }

    public List<Choice> getChoices() {
        return this.choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public OutputVariable getOutput() {
        return this.output;
    }

    public void setOutput(OutputVariable output) {
        this.output = output;
    }
}
