package shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.basics;

import shakeanapple.backtracker.common.variable.Variable;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.CountFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.*;

import java.util.stream.Collectors;

public class CountBasicBlockConverter extends NusmvBasicBlockConverterBase {
    private CountFunctionBlockBasic block;
    public CountBasicBlockConverter(CountFunctionBlockBasic block) {
        super(block);
        this.block = block;
    }

    @Override
    public NusmvBlockBasic convertImpl(StringBuilder sb) {
        sb.append(this.block.getOutputs().get(0).getName() + " := " +
                "count(" + this.block.getInputs().stream().map(Variable::getName).collect(Collectors.joining(",")) + ")" + ";");
        return new NusmvBlockBasic(sb.toString(), false);
    }
}
