package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.arithmetic;

import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BinOpFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

public class PlusFunctionBlockBasic extends BinOpFunctionBlockBasic {
    public PlusFunctionBlockBasic(InputVariable<IntegerValueHolder> left, InputVariable<IntegerValueHolder> right, OutputVariable<IntegerValueHolder> output) {
        super(left, right, output);
    }

    @Override
    public void evaluate() {
        super.fbInterface().getOutputs().get(0).assignValue(
                new IntegerValueHolder(((IntegerValueHolder)super.getLeft().getValue()).getValue() + ((IntegerValueHolder)super.getRight().getValue()).getValue())
        );
    }
}
