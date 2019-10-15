package shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition;

public class ChoiceDefinition {
    private InputDefinition condition;
    private InputDefinition output;

    public ChoiceDefinition(InputDefinition condition, InputDefinition output) {
        this.condition = condition;
        this.output = output;
    }

    public InputDefinition getCondition() {
        return this.condition;
    }

    public InputDefinition getOutput() {
        return this.output;
    }
}
