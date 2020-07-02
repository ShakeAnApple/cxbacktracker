package shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.basics;

import shakeanapple.backtracker.core.diagramexplanation.model.InputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.AssignFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlock;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockConverter;

import java.util.ArrayList;

public class AssignBasicBlockConverter implements NusmvBlockConverter {
    private AssignFunctionBlockBasic block;

    public AssignBasicBlockConverter(AssignFunctionBlockBasic block) {
        this.block = block;
    }

    @Override
    public NusmvBlock convert(boolean isRoot) {
        StringBuilder sb = new StringBuilder();
        InputGate input = new ArrayList<>(this.block.fbInterface().getInputs().values()).get(0);
        if (input.getIncomingConnection() == null){
            sb.append(input.getName() + " := " + input.input().getValue()).append(System.lineSeparator());
        }

        sb.append(this.block.getOutputs().get(0).getName() + " := " + input.getName() + ";").append(System.lineSeparator());
        return new NusmvBlockBasic(sb.toString(), false);
    }
}
