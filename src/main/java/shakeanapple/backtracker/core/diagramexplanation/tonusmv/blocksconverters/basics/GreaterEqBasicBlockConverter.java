package shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.basics;

import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic.GreaterEqFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.*;

public class GreaterEqBasicBlockConverter extends NusmvBasicBlockConverterBase {
    private GreaterEqFunctionBlockBasic block;
    public GreaterEqBasicBlockConverter(GreaterEqFunctionBlockBasic block) {
        super(block);
        this.block = block;
    }

    @Override
    public NusmvBlockBasic convertImpl(StringBuilder sb) {
        sb.append(this.block.getOutputs().get(0).getName() + " := " + this.block.getInputs().get(0).getName() + " >= " + this.block.getInputs().get(1).getName() + ";");
        return new NusmvBlockBasic(sb.toString(), false);
    }
}
