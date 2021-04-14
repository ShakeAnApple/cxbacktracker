package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.BlockVariableHistoryItem;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.*;
import java.util.stream.Collectors;

public class AssignFunctionBlockBasic extends FunctionBlockBasic {

    private final InputVariable input;
    private final OutputVariable output;


    protected AssignFunctionBlockBasic(boolean generateId, InputVariable input, OutputVariable output, String pathInSystem) {
        super("Assign" + (generateId ? BasicBlocksIdGenerator.next("Assign") : ""), new ArrayList<>() {{
            add(input);
        }}, new ArrayList<>() {{
            add(output);
        }}, pathInSystem);

        this.input = input;
        this.output = output;
    }

    private AssignFunctionBlockBasic(String name, InputVariable input, OutputVariable output, String pathInSystem) {
        super(name, new ArrayList<>() {{
            add(input);
        }}, new ArrayList<>() {{
            add(output);
        }}, pathInSystem);

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
        return new AssignFunctionBlockBasic(this.getName(), this.input.clone(), this.output.clone(), this.getStringPathInSystem());
    }

    @Override
    protected List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp) {
        return Collections.singletonList(new CauseNode(super.fbInterface().getInputs().get(this.input.getName()), super.history().getVariableValueForStep(input.getName(), timestamp), timestamp));
    }

    @Override
    protected List<ChangeCauseNode> explainChangeBasicImpl(OutputGate output, Integer changeStep) {
        return Collections.singletonList(new ChangeCauseNode(super.fbInterface().getInputs().get(this.input.getName()),
                super.history().getVariableValueForStep(this.input.getName(), changeStep + 1), changeStep + 1,
                super.history().getVariableValueForStep(this.input.getName(), changeStep), changeStep));
    }

}
