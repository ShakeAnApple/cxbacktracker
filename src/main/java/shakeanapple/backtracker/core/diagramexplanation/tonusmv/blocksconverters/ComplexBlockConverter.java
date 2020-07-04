package shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters;

import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.IntegerDynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlock;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockConverterBase;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvStringModelBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class ComplexBlockConverter extends NusmvBlockConverterBase {

    private FunctionBlockComplex block;

    public ComplexBlockConverter(FunctionBlockComplex block) {
        this.block = block;
    }

    @Override
    public NusmvBlock convertImpl(FunctionBlockBase parent, NusmvStringModelBuilder rootModelBuilder) {
        boolean isRoot = parent == null;
        NusmvStringModelBuilder stringModelBuilder;
        if (isRoot){
            rootModelBuilder = new NusmvStringModelBuilder(this.block.getType(), true);
            stringModelBuilder = rootModelBuilder;
        } else{
            stringModelBuilder = new NusmvStringModelBuilder(this.block.getType(), false);
        }

        List<String> blockSystemInputs = this.block.fbInterface().getOrderedInputs().stream()
                .map(in -> in.getIncomingConnection() != null ?
                        (in.getIncomingConnection().fromGate().getOwner().equals(parent) ? "" : (in.getIncomingConnection().fromGate().getOwner().getName() + ".")) + in.getIncomingConnection().fromGate().getName()
                        : in.input().getValue().toString()).collect(Collectors.toList());

        if (rootModelBuilder.containsModule(this.block.getType())){
            return new NusmvBlockComplex(this.block.getName(), this.block.getType(), blockSystemInputs);
        }

        for (FunctionBlockBase internalBlock : this.block.getInternalDiagram().getFunctionBlocks()) {
            NusmvBlock nusmvBlock = super.getConverterFor(internalBlock).convertImpl(this.block, stringModelBuilder);
            nusmvBlock.writeTo(stringModelBuilder);
        }

        for (OutputGate outGate : this.block.fbInterface().getOutputs().values()) {
            Connection conn = outGate.getIncomingConnection();
            if (conn != null) {
                stringModelBuilder.appendDefineStatement(conn.toGate().getName() + " := " +
                        (conn.fromGate().getOwner() instanceof FunctionBlockComplex ? conn.fromGate().getOwner().getName() + "." : "")
                        + conn.fromGate().getName() + ";", true);
            }
        }

        if (isRoot) {
            for(InputGate inGate: this.block.fbInterface().getOrderedInputs()){
                stringModelBuilder.appendVarStatement(inGate.getName() + ": " + (inGate.input().getValue() instanceof IntegerValueHolder ? "0..100" : "boolean") + ";");
            }
        } else {
            stringModelBuilder.addInputs(this.block.fbInterface().getOrderedInputs().stream().map(DiagramElement::getName).collect(Collectors.toList()));
        }

        return new NusmvBlockComplex(this.block.getName(), this.block.getType(),
                blockSystemInputs,
                stringModelBuilder.get());
    }
}
