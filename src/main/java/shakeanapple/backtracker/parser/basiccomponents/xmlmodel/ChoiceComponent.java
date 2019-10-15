package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.BasicComponentDefinitionAbstract;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.ChoiceComponentDefinition;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.ComponentDefinitionType;

import java.util.List;
import java.util.stream.Collectors;

public class ChoiceComponent extends BasicComponentAbstract {
    @JacksonXmlElementWrapper(localName = "choices")
    @JacksonXmlProperty(localName = "choice")
    private List<Choice> choices;

    private OutputVariable output;

    public ChoiceComponent() {
    }



    public ChoiceComponent(ComponentType type, long id, List<Choice> choices, OutputVariable output) {
        super(type, id);

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

    @Override
    public BasicComponentDefinitionAbstract translate() {
        return new ChoiceComponentDefinition(ComponentDefinitionType.valueOf(this.getType().name()), super.getId(),
                this.choices.stream().map(Choice::translate).collect(Collectors.toList()),
                this.output.translate());
    }
}
