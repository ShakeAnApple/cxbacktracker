package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.List;

public class OrFunctionBlockBasic extends FunctionBlockBasic {

    private final OutputVariable result;

    public OrFunctionBlockBasic(List<InputVariable> inputs, OutputVariable res) {
        super(inputs, new ArrayList<>() {{
            add(res);
        }});

        this.result = res;
    }

    @Override
    public void evaluate() {
        boolean res = false;
        for (InputVariable in : super.getInputs()){
            res = res || ((BooleanValueHolder)in.getValue()).getValue();
            if (res)
                break;
        }
        super.fbInterface().getOutputs().get(0).assignValue(
                new BooleanValueHolder(res)
        );
    }
}
