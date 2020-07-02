package shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.basics;

import shakeanapple.backtracker.common.variable.Variable;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.CountFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlock;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockConverter;

import java.util.stream.Collectors;

public class CountBasicBlockConverter implements NusmvBlockConverter {
    private CountFunctionBlockBasic block;
    public CountBasicBlockConverter(CountFunctionBlockBasic block) {
        this.block = block;
    }

    @Override
    public NusmvBlock convert(boolean isRoot) {
        StringBuilder sb = new StringBuilder();
        this.block.fbInterface().getInputs().values().stream().filter(in -> in.getIncomingConnection() == null).forEach(in ->{
            sb.append(in.getName() + " := " + in.input().getValue() + ";").append(System.lineSeparator());
        });
        sb.append(this.block.getOutputs().get(0).getName() + " := " +
                "count(" + this.block.getInputs().stream().map(Variable::getName).collect(Collectors.joining(",")) + ")" + ";");
        return new NusmvBlockBasic(sb.toString(), false);
    }
}
