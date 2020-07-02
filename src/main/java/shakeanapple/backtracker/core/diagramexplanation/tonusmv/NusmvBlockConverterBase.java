package shakeanapple.backtracker.core.diagramexplanation.tonusmv;

import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;

public abstract class NusmvBlockConverterBase implements NusmvBlockConverter{
    private ConvertersFabric convertersFabric;

    public NusmvBlockConverterBase() {
        this.convertersFabric = new ConvertersFabric();
    }

    protected NusmvBlockConverter getConverterFor(FunctionBlockBase block){
        return this.convertersFabric.getConverterForBlock(block);
    }
}
