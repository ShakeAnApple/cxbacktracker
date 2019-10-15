package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.List;

public class CountFunctionBlockBasic extends FunctionBlockBasic {
    private OutputVariable output;

    protected CountFunctionBlockBasic(List<InputVariable> inputs, OutputVariable output) {
        super("count","basic", inputs, new ArrayList<>(){{add(output);}});

        this.output = output;
    }

    @Override
    public void evaluate() {
        this.output.assignValue(new IntegerValueHolder(
                (int)super.getInputs().stream().filter(in -> ((BooleanValueHolder)in.getValue()).getValue()).count()
        ));
    }
}
