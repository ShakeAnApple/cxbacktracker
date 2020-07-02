package shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters;

import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlock;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlockConverterBase;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvStringModelBuilder;

import java.util.stream.Collectors;

public class ComplexBlockConverter extends NusmvBlockConverterBase {

    private FunctionBlockComplex block;

    public ComplexBlockConverter(FunctionBlockComplex block) {
        this.block = block;
    }

    @Override
    public NusmvBlock convert(boolean isRoot) {
        NusmvStringModelBuilder stringModelBuilder = new NusmvStringModelBuilder(this.block.getType(), this.block.isRoot());
        for (FunctionBlockBase internalBlock: block.getInternalDiagram().getFunctionBlocks()){
            NusmvBlock nusmvBlock = super.getConverterFor(internalBlock).convert(false);
            nusmvBlock.writeTo(stringModelBuilder);
        }
        return new NusmvBlockComplex(this.block.getName(), this.block.getType(),
                this.block.fbInterface().getOrderedInputs().stream().map(in -> in.getIncomingConnection().fromGate().getName()).collect(Collectors.toList()),
                stringModelBuilder.get());
    }
}
