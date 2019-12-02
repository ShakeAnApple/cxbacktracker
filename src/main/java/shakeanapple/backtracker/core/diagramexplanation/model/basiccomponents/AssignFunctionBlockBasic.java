package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public void executeImpl() {
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(
                this.input.getValue()
        );
    }

    @Override
    protected List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp) {
        return Collections.singletonList(new CauseNode(super.fbInterface().getInputs().get(this.input.getName()), super.history().getVariableValueForStep(input.getName(), timestamp), timestamp));
    }
}
