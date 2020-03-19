package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
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


    protected AssignFunctionBlockBasic(boolean generateId, InputVariable input, OutputVariable output) {
        super("Assign"+ (generateId ? BasicBlocksIdGenerator.next("Assign") : ""), new ArrayList<>() {{
            add(input);
        }}, new ArrayList<>() {{
            add(output);
        }});

        this.input = input;
        this.output = output;
    }

    private AssignFunctionBlockBasic(String name, InputVariable input, OutputVariable output) {
        super(name, new ArrayList<>() {{
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
    public FunctionBlockBase clone() {
        return new AssignFunctionBlockBasic(this.getName(), this.input.clone(), this.output.clone());
    }

    @Override
    protected List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp) {
        return Collections.singletonList(new CauseNode(super.fbInterface().getInputs().get(this.input.getName()), super.history().getVariableValueForStep(input.getName(), timestamp), timestamp));
    }
}
