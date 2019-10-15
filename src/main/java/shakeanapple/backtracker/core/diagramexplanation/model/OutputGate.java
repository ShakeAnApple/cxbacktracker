package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.Gate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;

public class OutputGate extends Gate {
    private InputVariable input;

    private OutputVariable output;

    public OutputGate(OutputVariable output) {
        super(output.getName(), "OUTPUT_GATE");

        this.output = output;

        this.input = new InputVariable(DynamicVariable.of(output.getName(), output.getValue()), Integer.MIN_VALUE);
    }

    public InputVariable input() {
        return this.input;
    }

    public OutputVariable output() {
        return this.output;
    }

    @Override
    public void evaluate() {
        this.output.assignValue(
                this.input.getValue()
        );
    }
}
