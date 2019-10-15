package shakeanapple.backtracker.ui.basiccomponentsconstructor.model;

import java.util.ArrayList;

public class DelayComponent extends BasicComponent {

    private InputVariable input;
    private InputVariable defValue;

    private OutputVariable output;
    private int delay;

    public DelayComponent(long id, InputVariable input, OutputVariable output, int delay) {
        super(ComponentType.DELAY, id, new ArrayList<>() {{add(input);}}, new ArrayList<>(){{add(output);}});

        this.input = input;
        this.output = output;
        this.delay = delay;
    }

    public DelayComponent(long id, InputVariable input, InputVariable defValue, OutputVariable output, int delay) {
        this(id, input, output, delay);

        this.defValue = defValue;
        super.getInputs().add(this.defValue);
    }

    @Override
    public shakeanapple.backtracker.parser.basiccomponents.xmlmodel.BasicComponentAbstract translate() {
        if (this.defValue != null){
            return new shakeanapple.backtracker.parser.basiccomponents.xmlmodel.DelayComponent(super.getId(), this.input.translate(), this.defValue.translate(), this.output.translate(), this.delay);
        }
        return new shakeanapple.backtracker.parser.basiccomponents.xmlmodel.DelayComponent(super.getId(), this.input.translate(), this.output.translate(), this.delay);
    }
}
