package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.List;

public abstract class BooleanComponent extends Component<BooleanValueHolder, BooleanValueHolder> {
    protected BooleanComponent(List<InputVariable<BooleanValueHolder>> inputs, List<OutputVariable<BooleanValueHolder>> outputs) {
        super(inputs, outputs);
    }

    @Override
    public abstract void execute();
}
