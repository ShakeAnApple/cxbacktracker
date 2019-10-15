package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.BasicComponentDefinitionAbstract;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.ComponentDefinitionType;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.DelayComponentDefinition;

public class DelayComponent extends BasicComponentAbstract {

    private InputVariable input;
    private InputVariable defValue;

    private OutputVariable output;


    @JacksonXmlProperty(isAttribute = true)
    private int delay;

    public DelayComponent() {
    }

    public DelayComponent(long id, InputVariable input, OutputVariable output, int delay) {
        super(ComponentType.DELAY, id);

        this.input = input;
        this.output = output;
        this.delay = delay;
    }

    public DelayComponent(long id, InputVariable input, InputVariable defValue, OutputVariable output, int delay) {
        this(id, input, output, delay );

        this.defValue = defValue;
    }

    public InputVariable getInput() {
        return this.input;
    }

    public void setInput(InputVariable input) {
        this.input = input;
    }

    public OutputVariable getOutput() {
        return this.output;
    }

    public void setOutput(OutputVariable output) {
        this.output = output;
    }

    public int getDelay() {
        return this.delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public InputVariable getDefValue() {
        return this.defValue;
    }

    public void setDefValue(InputVariable defValue) {
        this.defValue = defValue;
    }

    @Override
    public BasicComponentDefinitionAbstract translate() {
        if (this.defValue != null){
            return new DelayComponentDefinition(ComponentDefinitionType.valueOf(super.getType().name()), super.getId(), this.input.translate(), this.defValue.translate(), this.output.translate(), this.delay);
        }
        return new DelayComponentDefinition(ComponentDefinitionType.valueOf(super.getType().name()), super.getId(), this.input.translate(), this.output.translate(), this.delay);
    }
}
