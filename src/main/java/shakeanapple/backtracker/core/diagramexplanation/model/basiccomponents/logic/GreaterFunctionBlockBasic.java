package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.Cause;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BinOpFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.List;
import java.util.stream.Collectors;

public class GreaterFunctionBlockBasic extends BinOpFunctionBlockBasic {
    public GreaterFunctionBlockBasic(InputVariable left, InputVariable right, OutputVariable res) {
        super("Greater", left, right, res);
    }

    @Override
    public void executeImpl() {
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(
                new BooleanValueHolder(((IntegerValueHolder)super.getLeft().getValue()).getValue() > ((IntegerValueHolder)super.getRight().getValue()).getValue())
        );
    }

    @Override
    protected List<Cause> explainImpl(OutputGate output, Integer timestamp) {
        return super.fbInterface().getInputs().values().stream()
                .map(in -> new Cause(in, super.history().getVariableValueForStep(in.getName(), timestamp), timestamp))
                .collect(Collectors.toList());
    }
}

