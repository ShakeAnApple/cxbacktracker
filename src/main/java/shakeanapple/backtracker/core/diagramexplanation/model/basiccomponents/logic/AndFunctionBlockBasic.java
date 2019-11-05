package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.Cause;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.List;

public class AndFunctionBlockBasic extends FunctionBlockBasic {

    private final OutputVariable result;

    public AndFunctionBlockBasic(List<InputVariable> inputs, OutputVariable res) {
        super("And", inputs, new ArrayList<OutputVariable>() {{
            add(res);
        }});

        this.result = res;
    }

    @Override
    public void executeImpl() {
        boolean res = true;
        for (InputVariable in : super.getInputs()){
            res = res && ((BooleanValueHolder)in.getValue()).getValue();
            if (!res)
                break;
        }
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(
                new BooleanValueHolder(res)
        );
    }

    @Override
    protected List<Cause> explainImpl(OutputGate output, Integer timestamp) {
        return null;
    }
}
