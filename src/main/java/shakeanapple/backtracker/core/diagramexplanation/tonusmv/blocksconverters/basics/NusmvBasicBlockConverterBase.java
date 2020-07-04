package shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.basics;

import shakeanapple.backtracker.core.diagramexplanation.model.Connection;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.model.InputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlock;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockConverterBase;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvStringModelBuilder;

public abstract class NusmvBasicBlockConverterBase extends NusmvBlockConverterBase {

    protected FunctionBlockBasic block;

    public NusmvBasicBlockConverterBase(FunctionBlockBasic block) {
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
                        + " := " +
                        (conn.fromGate().getOwner() instanceof FunctionBlockComplex &&
                                !(conn.fromGate().getOwner().equals(parent)) ? conn.fromGate().getOwner().getName() + "." : "")
                        + conn.fromGate().getName() + ";", true);
            }
        }
        StringBuilder sb = new StringBuilder();
        this.block.fbInterface().getInputs().values().stream().filter(in -> in.getIncomingConnection() == null).forEach(in ->{
            parentModel.appendDefineStatement(in.getName() + " := " + in.input().getValue() + ";" + System.lineSeparator(), true);
        });
        return this.convertImpl(sb);
    }

    protected abstract NusmvBlockBasic convertImpl(StringBuilder sb);
}
