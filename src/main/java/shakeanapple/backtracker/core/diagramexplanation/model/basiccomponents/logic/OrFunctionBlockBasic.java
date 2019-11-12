package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.Cause;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.InputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrFunctionBlockBasic extends FunctionBlockBasic {

    private final OutputVariable result;

    public OrFunctionBlockBasic(List<InputVariable> inputs, OutputVariable res) {
        super("Or", inputs, new ArrayList<>() {{
            add(res);
        }});

        this.result = res;
    }

    @Override
    public void executeImpl() {
        boolean res = false;
        for (InputVariable in : super.getInputs()){
            res = res || ((BooleanValueHolder)in.getValue()).getValue();
            if (res)
                break;
        }
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(
                new BooleanValueHolder(res)
        );
    }

    @Override
    protected List<Cause> explainImpl(OutputGate output, Integer timestamp) {
        BooleanValueHolder val = (BooleanValueHolder) output.getValue();
        if (val.getValue()){
            return this.explainTrue(timestamp);
        } else{
            return this.explainFalse(timestamp);
        }
    }

    private List<Cause> explainTrue(int timestamp){
        List<Cause> causes = new ArrayList<>();
        for (InputGate in : super.fbInterface().getInputs().values()){
            BooleanValueHolder val = (BooleanValueHolder) super.history().getVariableValueForStep(in.getName(), timestamp);
            if (val.getValue()){
                causes.add(new Cause(in, val, timestamp));
            }
        }
        return causes;
    }

    private List<Cause> explainFalse(int timestamp){
        return super.fbInterface().getInputs().values().stream()
                .map(in -> new Cause(in, super.history().getVariableValueForStep(in.getName(), timestamp), timestamp))
                .collect(Collectors.toList());
    }
}
