package shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.basics;

import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice.Choice;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice.ChoiceFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlock;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockConverter;

public class ChoiceBasicBlockConverter implements NusmvBlockConverter {
    private ChoiceFunctionBlockBasic block;

    public ChoiceBasicBlockConverter(ChoiceFunctionBlockBasic block) {
        this.block = block;
    }

    @Override
    public NusmvBlock convert(boolean isRoot) {
        StringBuilder sb = new StringBuilder();
        this.block.fbInterface().getInputs().values().stream().filter(in -> in.getIncomingConnection() == null).forEach(in ->{
            sb.append(in.getName() + " := " + in.input().getValue() + ";").append(System.lineSeparator());
        });
        sb.append(this.block.getOutputs().get(0).getName() + " := ")
                .append(System.lineSeparator())
                .append("case");
        for (Choice choice: this.block.getChoices()) {
            sb.append(choice.getCondition().getName() + ": " + choice.getOutput().getName() + ";");
        }
        sb.append(System.lineSeparator()).append("esac;");

        return new NusmvBlockBasic(sb.toString(), false);
    }
}
