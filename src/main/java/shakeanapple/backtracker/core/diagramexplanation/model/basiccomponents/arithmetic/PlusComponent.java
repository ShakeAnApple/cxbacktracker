package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.arithmetic;

import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BinOpComponent;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

public class PlusComponent extends BinOpComponent<IntegerValueHolder, IntegerValueHolder> {
    protected PlusComponent(InputVariable<IntegerValueHolder> left, InputVariable<IntegerValueHolder> right, OutputVariable<IntegerValueHolder> output) {
        super(left, right, output);
    }

    @Override
    public void execute() {
        super.getOutput().assignValue(
                new IntegerValueHolder(super.getLeft().getValue().getValue() + super.getRight().getValue().getValue())
        );
    }
}
