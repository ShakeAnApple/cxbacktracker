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

    public InputGate(InputVariable input, FunctionBlockBase owner) {
        super(input.getName(), "INPUT_GATE", owner);
        this.input = input;

        this.output = OutputVariable.createSharedWithInput(this.input);
    }

    public OutputVariable output() {
        return this.output;
    }

    public InputVariable input() {
        return this.input;
    }


}
