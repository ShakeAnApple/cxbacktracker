package shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.basics;

import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.InputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.AssignFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.*;

import java.util.ArrayList;

public class AssignBasicBlockConverter extends NusmvBasicBlockConverterBase {
    private AssignFunctionBlockBasic block;

    public AssignBasicBlockConverter(AssignFunctionBlockBasic block) {
        super(block);
        this.block = block;
    }

    @Override
    public NusmvBlockBasic convertImpl(StringBuilder sb) {
        sb.append(this.block.getOutputs().get(0).getName() + " := " + this.block.getInputs().get(0).getName() + ";").append(System.lineSeparator());
        return new NusmvBlockBasic(sb.toString(), false);
    }
}
