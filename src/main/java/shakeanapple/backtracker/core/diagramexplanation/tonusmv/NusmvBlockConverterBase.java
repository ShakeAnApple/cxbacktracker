package shakeanapple.backtracker.core.diagramexplanation.tonusmv;

import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;

public abstract class NusmvBlockConverterBase implements NusmvBlockConverter{
    private ConvertersFabric convertersFabric;

    public NusmvBlockConverterBase() {
        this.convertersFabric = new ConvertersFabric();
    }

    protected NusmvBlockConverterBase getConverterFor(FunctionBlockBase block){
        return this.convertersFabric.getConverterForBlock(block);
    }

    public NusmvBlock convert(boolean isRoot){
        return this.convertImpl(null, null);
    }

    public abstract NusmvBlock convertImpl(FunctionBlockBase parent, NusmvStringModelBuilder parentModel);
}
