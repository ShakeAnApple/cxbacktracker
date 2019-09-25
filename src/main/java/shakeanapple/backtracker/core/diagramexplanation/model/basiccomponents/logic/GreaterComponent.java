package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BinOpComponent;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

public class GreaterComponent extends BinOpComponent<IntegerValueHolder, BooleanValueHolder> {
    protected GreaterComponent(InputVariable<IntegerValueHolder> left, InputVariable<IntegerValueHolder> right, OutputVariable<BooleanValueHolder> res) {
        super(left, right, res);
    }

    @Override
    public void execute() {
        super.getOutput().assignValue(
                new BooleanValueHolder(super.getLeft().getValue().getValue() > super.getRight().getValue().getValue())
        );
    }
}

