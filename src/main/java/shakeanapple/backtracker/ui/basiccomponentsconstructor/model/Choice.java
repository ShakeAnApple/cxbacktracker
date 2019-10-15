package shakeanapple.backtracker.ui.basiccomponentsconstructor.model;

public class Choice {
    private InputVariable condition;
    private InputVariable output;

    public Choice(InputVariable condition, InputVariable output) {
        this.condition = condition;
        this.output = output;
    }

    public InputVariable getCondition() {
        return this.condition;
    }

    public InputVariable getOutput() {
        return this.output;
    }

    public shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Choice translate() {
        return new shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Choice(this.condition.translate(), this.output.translate());
    }
}
