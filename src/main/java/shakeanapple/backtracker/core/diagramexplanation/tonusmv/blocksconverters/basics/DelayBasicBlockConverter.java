package shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.basics;

import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.DelayFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlock;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockConverter;

public class DelayBasicBlockConverter implements NusmvBlockConverter {
    private DelayFunctionBlockBasic block;
    public DelayBasicBlockConverter(DelayFunctionBlockBasic block) {
        this.block = block;
    }

    @Override
    public NusmvBlock convert(boolean isRoot) {
        StringBuilder sb = new StringBuilder();
        this.block.fbInterface().getInputs().values().stream().filter(in -> in.getIncomingConnection() == null).forEach(in ->{
            sb.append(in.getName() + " := " + in.input().getValue() + ";").append(System.lineSeparator());
        });
        sb.append("next(" + this.block.getOutputs().get(0).getName() + ") = " + this.block.getInput().getName() + ";")
                .append(System.lineSeparator())
                .append("init(" + this.block.getOutputs().get(0).getName() + ") = " + this.block.getInputs().get(1).getName() + ";");
        return new NusmvBlockBasic(sb.toString(), true);
    }
}
