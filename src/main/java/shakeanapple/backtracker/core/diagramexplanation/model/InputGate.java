package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.DiagramElement;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.Gate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;

public class InputGate extends Gate {
    private OutputVariable output;

    private InputVariable input;

    public InputGate(InputVariable input) {
        super(input.getName(), "INPUT_GATE");
        this.input = input;

        this.output = new OutputVariable(DynamicVariable.of(input.getName(), input.getValue()));
    }

    public OutputVariable output() {
        return this.output;
    }

    public InputVariable input() {
        return this.input;
    }

    public void populateInput(ValueHolder value) {
        this.input = new InputVariable(DynamicVariable.of(this.output.getName(), value), this.input.getOrder());
    }

    @Override
    public void evaluate() {
        this.output.assignValue(
                this.input.getValue()
        );
    }
}
