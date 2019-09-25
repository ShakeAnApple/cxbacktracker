package shakeanapple.backtracker.ui.basiccomponentsconstructor.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChoiceComponent extends BasicComponentAbstract {
    private List<Choice> choices;

    private OutputVariable output;

    public ChoiceComponent(ComponentType type, List<Choice> choices, OutputVariable output) {
        super(type);

        this.choices = choices;
        this.output = output;
    }

    public List<Choice> getChoices() {
        return this.choices;
    }

    public OutputVariable getOutput() {
        return this.output;
    }

    @Override
    public shakeanapple.backtracker.parser.basiccomponents.xmlmodel.BasicComponentAbstract translate() {
        List<shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Choice> choicesXml = this.choices.stream().map(Choice::translate).collect(Collectors.toList());

        return new shakeanapple.backtracker.parser.basiccomponents.xmlmodel.ChoiceComponent(
                shakeanapple.backtracker.parser.basiccomponents.xmlmodel.ComponentType.valueOf(super.getType().name()), choicesXml, this.output.translate());
    }
}
