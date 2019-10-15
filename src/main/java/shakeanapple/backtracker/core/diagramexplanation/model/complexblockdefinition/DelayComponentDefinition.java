package shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition;

import java.util.ArrayList;

public class DelayComponentDefinition extends BasicComponentDefinitionAbstract {

    private InputDefinition input;
    private InputDefinition defValue;

    private OutputDefinition output;

    private int delay;

    public DelayComponentDefinition(ComponentDefinitionType type, long id, InputDefinition input, OutputDefinition output, int delay) {
        super(type, id, new ArrayList<>() {{
            add(input);
        }}, new ArrayList<>() {{
            add(output);
        }});

        this.output = output;
        this.input = input;
        this.delay = delay;
    }

    public DelayComponentDefinition(ComponentDefinitionType type, long id, InputDefinition input, InputDefinition defValue, OutputDefinition output, int delay) {
        this(type, id, input, output, delay);

        this.defValue = defValue;
        super.getInputs().add(this.defValue);
    }

    public InputDefinition getDefValue() {
        return this.defValue;
    }

    public InputDefinition getInput() {
        return this.input;
    }

    public OutputDefinition getOutput() {
        return this.output;
    }

    public int getDelay() {
        return this.delay;
    }

}
