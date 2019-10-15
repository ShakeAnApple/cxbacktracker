package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;

//TODO introduce time into system?
public class DelayFunctionBlockBasic extends FunctionBlockBasic {
    private final InputVariable input;
    private final InputVariable defValue;

    private final OutputVariable output;

    private final int delay;

    private int ticksPassed;

    public DelayFunctionBlockBasic(InputVariable input, InputVariable defValue, OutputVariable output, int delay) {
        super(new ArrayList<>() {{
            add(input);
        }}, new ArrayList<>() {{
            add(output);
        }});

        this.input = input;
        this.output = output;
        this.delay = delay;
        this.defValue = defValue;
        this.ticksPassed = 0;
    }

    // TODO ask Igor
    @Override
    public void evaluate() {
        if (this.ticksPassed < this.delay){
            ValueHolder defValHolder = this.defValue != null ? this.defValue.getValue() : this.output.getDefaultValue();
            this.output.assignValue(defValHolder);
            this.ticksPassed ++;
        }
        // TODO save prev value of input and assign it, NOT current value of input
        if (this.ticksPassed == this.delay){
            this.output.assignValue(input.getValue());
            this.ticksPassed = 0;
        }
    }
}
