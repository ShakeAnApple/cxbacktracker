package shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.basics;

import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice.Choice;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice.ChoiceFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.*;

public class ChoiceBasicBlockConverter extends NusmvBasicBlockConverterBase {
    private ChoiceFunctionBlockBasic block;

    public ChoiceBasicBlockConverter(ChoiceFunctionBlockBasic block) {
        super(block);
        this.block = block;
    }

    @Override
    public NusmvBlockBasic convertImpl(StringBuilder sb) {
        sb.append(this.block.getOutputs().get(0).getName() + " := ")
                .append(System.lineSeparator())
                .append("case").append(System.lineSeparator());
        for (Choice choice: this.block.getChoices()) {
            sb.append(choice.getCondition().getName() + ": " + choice.getOutput().getName() + ";").append(System.lineSeparator());
        }
        sb.append(System.lineSeparator()).append("esac;");

        return new NusmvBlockBasic(sb.toString(), false);
    }
}
