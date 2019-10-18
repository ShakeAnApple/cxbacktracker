package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;

public class AssignFunctionBlockBasic extends FunctionBlockBasic {

    private final InputVariable input;
    private final OutputVariable output;


    protected AssignFunctionBlockBasic(InputVariable input, OutputVariable output) {
        super("Assign", new ArrayList<>() {{
            add(input);
        }}, new ArrayList<>() {{
            add(output);
        }});

        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() {
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(
                this.input.getValue()
        );
    }
}
