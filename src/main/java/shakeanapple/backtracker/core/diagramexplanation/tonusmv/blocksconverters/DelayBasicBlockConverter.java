package shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters;

import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.DelayFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.*;

import java.util.ArrayList;

public class DelayBasicBlockConverter extends NusmvBlockConverterBase {

    protected DelayFunctionBlockBasic block;

    public DelayBasicBlockConverter(DelayFunctionBlockBasic block) {
        this.block = block;
    }

    @Override
    public NusmvBlock convertImpl(FunctionBlockBase parent, NusmvStringModelBuilder parentModel) {
        for (InputGate inGate : this.block.fbInterface().getOrderedInputs()) {
            Connection conn = inGate.getIncomingConnection();
            if (conn != null) {
                parentModel.appendDefineStatement((conn.toGate().getOwner() instanceof FunctionBlockComplex
                        ? conn.toGate().getOwner().getName() + "." : "")
                        + conn.toGate().getName()
                        + " := " + (conn.isInverted() ? "!" : "") +
                        (conn.fromGate().getOwner() instanceof FunctionBlockComplex &&
                                !(conn.fromGate().getOwner().equals(parent)) ? conn.fromGate().getOwner().getName() + "." : "")
                        + conn.fromGate().getName() + ";", true);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("next(" + this.block.getOutputs().get(0).getName() + ") := " + this.block.getInput().getName() + ";")
                .append(System.lineSeparator())
                .append("init(" + this.block.getOutputs().get(0).getName() + ") := " + this.block.getInputs().get(1).getName() + ";");

        OutputVariable output = this.block.getOutputs().get(0);
//        String var = output.getName() + ": " + (output.getValue() instanceof IntegerValueHolder ? "-2147483647..2147483647" : "boolean") + ";";
        String var = output.getName() + ": " + (output.getValue() instanceof IntegerValueHolder ? "-100..100" : "boolean") + ";";
        parentModel.appendVarStatement(var);

        this.block.fbInterface().getInputs().values().stream().filter(in -> in.getIncomingConnection() == null).forEach(in ->{
            parentModel.appendDefineStatement(in.getName() + " := " + in.input().getValue() + ";" + System.lineSeparator(), true);
        });
        return new NusmvBlockBasic(sb.toString(), true);
    }
}
