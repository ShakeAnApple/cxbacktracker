package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.Cause;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CountFunctionBlockBasic extends FunctionBlockBasic {
    private OutputVariable output;

    protected CountFunctionBlockBasic(List<InputVariable> inputs, OutputVariable output) {
        super("count", inputs, new ArrayList<>(){{add(output);}});

        this.output = output;
    }

    @Override
    public void executeImpl() {
        super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(new IntegerValueHolder(
                (int)super.getInputs().stream().filter(in -> ((BooleanValueHolder)in.getValue()).getValue()).count()
        ));
    }

    @Override
    protected List<Cause> explainImpl(OutputGate output, Integer timestamp) {
        return super.fbInterface().getInputs().values().stream()
                .map(in -> new Cause(in, super.history().getVariableValueForStep(in.getName(), timestamp), timestamp))
                .collect(Collectors.toList());
    }
}
