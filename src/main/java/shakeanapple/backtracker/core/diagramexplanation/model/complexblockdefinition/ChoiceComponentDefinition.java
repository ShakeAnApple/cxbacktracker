package shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChoiceComponentDefinition extends BasicComponentDefinitionAbstract {
    private List<ChoiceDefinition> choices;
    private OutputDefinition output;

    public ChoiceComponentDefinition(ComponentDefinitionType type, long id, List<ChoiceDefinition> choices, OutputDefinition output) {
        super(type, id, inferInputs(choices) , new ArrayList<>(){{add(output);}});

        this.choices = choices;
        this.output = output;
    }

    private static List<InputDefinition> inferInputs(List<ChoiceDefinition> choices){
        List<InputDefinition> inputs = choices.stream().map(ch -> ch.getCondition()).collect(Collectors.toList());
        inputs.addAll(choices.stream().map(ch -> ch.getOutput()).collect(Collectors.toList()));
        return inputs;
    }

    public List<ChoiceDefinition> getChoices() {
        return this.choices;
    }

    public OutputDefinition getOutput() {
        return this.output;
    }
}
