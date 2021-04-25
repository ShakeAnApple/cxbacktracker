package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MaxFunctionBlockBasic extends FunctionBlockBasic {
    private OutputVariable output;

    protected MaxFunctionBlockBasic(boolean generateId, List<InputVariable> inputs, OutputVariable output, String pathInSystem) {
        super("max" + (generateId ? BasicBlocksIdGenerator.next("max") : ""), inputs, new ArrayList<>() {{
            add(output);
        }},pathInSystem);

        this.output = output;
    }

    private MaxFunctionBlockBasic(String name, List<InputVariable> inputs, OutputVariable output, String pathInSystem) {
        super(name, inputs, new ArrayList<>() {{
            add(output);
        }},pathInSystem);

        this.output = output;
    }

    @Override
    public void executeImpl() {
        Integer res = super.getInputs().stream().map(in -> (int) in.getValue().getValue()).max(Integer::compareTo).get();
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(
                new IntegerValueHolder(res)
        );
    }

    @Override
    public FunctionBlockBase clone() {
        return new MaxFunctionBlockBasic(this.getName(), this.getInputs().stream().map(InputVariable::clone).collect(Collectors.toList()), this.output.clone(),this.getStringPathInSystem());
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
