package shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.basics;

import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic.LessFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlock;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockConverter;

public class LessBasicBlockConverter implements NusmvBlockConverter {
    private LessFunctionBlockBasic block;
    public LessBasicBlockConverter(LessFunctionBlockBasic block) {
        this.block = block;
    }

    @Override
    public NusmvBlock convert(boolean isRoot) {
        StringBuilder sb = new StringBuilder();
        this.block.fbInterface().getInputs().values().stream().filter(in -> in.getIncomingConnection() == null).forEach(in ->{
            sb.append(in.getName() + " := " + in.input().getValue() + ";").append(System.lineSeparator());
        });
        sb.append(this.block.getOutputs().get(0).getName() + " := " + this.block.getInputs().get(0).getName() + " < " + this.block.getInputs().get(1).getName() + ";");
        return new NusmvBlockBasic(sb.toString(), false);
    }
}
