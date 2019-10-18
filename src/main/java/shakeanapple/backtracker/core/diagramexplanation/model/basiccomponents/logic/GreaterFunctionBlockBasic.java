package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BinOpFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

public class GreaterFunctionBlockBasic extends BinOpFunctionBlockBasic {
    public GreaterFunctionBlockBasic(InputVariable left, InputVariable right, OutputVariable res) {
        super("Greater", left, right, res);
    }

    @Override
    public void execute() {
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(
                new BooleanValueHolder(((IntegerValueHolder)super.getLeft().getValue()).getValue() > ((IntegerValueHolder)super.getRight().getValue()).getValue())
        );
    }
}

