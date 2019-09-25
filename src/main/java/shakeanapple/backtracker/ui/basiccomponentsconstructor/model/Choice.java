package shakeanapple.backtracker.ui.basiccomponentsconstructor.model;

public class Choice {
    private InputVariable input;
    private OutputVariable output;

    public Choice(InputVariable input, OutputVariable output) {
        this.input = input;
        this.output = output;
    }

    public InputVariable getInput() {
        return this.input;
    }

    public OutputVariable getOutput() {
        return this.output;
    }

    public shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Choice translate() {
        return new shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Choice(this.input.translate(), this.output.translate());
    }
}
