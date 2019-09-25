package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.BooleanComponent;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.List;

public class AndComponent extends BooleanComponent {

    private final OutputVariable<BooleanValueHolder> result;

    protected AndComponent(List<InputVariable<BooleanValueHolder>> inputs, OutputVariable<BooleanValueHolder> res) {
        super(inputs, new ArrayList<>() {{
            add(res);
        }});

        this.result = res;
    }

    @Override
    public void execute() {
        boolean res = true;
        for (InputVariable<BooleanValueHolder> in : super.getInputs()){
            res = res && in.getValue().getValue();
            if (!res)
                break;
        }
        this.result.assignValue(
                new BooleanValueHolder(res)
        );
    }
}
