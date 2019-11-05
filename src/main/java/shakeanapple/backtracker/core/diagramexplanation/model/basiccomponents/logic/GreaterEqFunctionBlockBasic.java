package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.Cause;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BinOpFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.List;

public class GreaterEqFunctionBlockBasic extends BinOpFunctionBlockBasic {
    public GreaterEqFunctionBlockBasic(InputVariable left, InputVariable right, OutputVariable res) {
        super("GreaterEq", left, right, res);
    }

    @Override
    public void executeImpl() {
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(
                new BooleanValueHolder(((IntegerValueHolder)super.getLeft().getValue()).getValue() >= ((IntegerValueHolder)super.getRight().getValue()).getValue())
        );
    }

    @Override
    protected List<Cause> explainImpl(OutputGate output, Integer timestamp) {
        return null;
    }
}

