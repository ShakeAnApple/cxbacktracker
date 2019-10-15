package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.ChoiceDefinition;

public class Choice {
    private InputVariable condition;
    private InputVariable output;

    public Choice() {
    }

    public Choice(InputVariable condition, InputVariable output) {
        this.condition = condition;
        this.output = output;
    }

    public InputVariable getCondition() {
        return this.condition;
    }

    public void setCondition(InputVariable condition) {
        this.condition = condition;
    }

    public InputVariable getOutput() {
        return this.output;
    }

    public void setOutput(InputVariable output) {
        this.output = output;
    }

    public ChoiceDefinition translate() {
        return new ChoiceDefinition(this.condition.translate(), this.output.translate());
    }
}
