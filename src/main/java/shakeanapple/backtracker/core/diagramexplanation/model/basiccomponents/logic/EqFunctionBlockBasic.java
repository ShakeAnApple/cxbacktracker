package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BinOpFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

public class EqFunctionBlockBasic extends BinOpFunctionBlockBasic {

    public EqFunctionBlockBasic(InputVariable left, InputVariable right, OutputVariable res) {
        super(left, right, res);
    }

    @Override
    public void evaluate() {
        super.fbInterface().getOutputs().get(0).assignValue(
                new BooleanValueHolder(((IntegerValueHolder)super.getLeft().getValue()).getValue().equals(((IntegerValueHolder)super.getRight().getValue()).getValue()))
        );
    }
}
