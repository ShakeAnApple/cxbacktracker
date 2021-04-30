package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CountFunctionBlockBasic extends FunctionBlockBasic {
    private OutputVariable output;

    protected CountFunctionBlockBasic(boolean generateId, List<InputVariable> inputs, OutputVariable output, String pathInSystem) {
        super("count" + (generateId ? BasicBlocksIdGenerator.next("count") : ""), inputs, new ArrayList<>() {{
            add(output);
        }},pathInSystem);

        this.output = output;
    }

    private CountFunctionBlockBasic(String name, List<InputVariable> inputs, OutputVariable output, String pathInSystem) {
        super(name, inputs, new ArrayList<>() {{
            add(output);
        }},pathInSystem);

        this.output = output;
    }

    @Override
    public void executeImpl() {
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(new IntegerValueHolder(
                (int) super.getInputs().stream().filter(in -> ((BooleanValueHolder) in.getValue()).getValue()).count()
        ));
    }

    @Override
    public FunctionBlockBase clone() {
        return new CountFunctionBlockBasic(this.getName(), this.getInputs().stream().map(InputVariable::clone).collect(Collectors.toList()), this.output.clone(),this.getStringPathInSystem());
    }

    @Override
    protected List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp) {
        return super.fbInterface().getInputs().values().stream()
                .map(in -> new CauseNode(in, super.history().getVariableValueForStep(in.getName(), timestamp), timestamp))
                .collect(Collectors.toList());
    }

    @Override
    protected List<ChangeCauseNode> explainChangeBasicImpl(OutputGate output, Integer changeStep) {
        return this.fbInterface().getInputs().keySet().stream().map(name -> {
            ValueHolder changeValue = this.history().getVariableValueForStep(name, changeStep);
            ValueHolder nextValue = this.history().getVariableValueForStep(name, changeStep + 1);
            if (!changeValue.equals(nextValue)) {
                return new ChangeCauseNode(this.fbInterface().getInputs().get(name), nextValue, changeStep + 1, changeValue, changeStep);
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
