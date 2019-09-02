package shakeanapple.backtracker.core.fblockmapping.model.snapshot;

import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlock;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionBlockSnapshot {
    private final String name;
    private final String type;

    private final List<String> inputs;
    private final List<String> outputs;
    private final List<String> internals;

    public FunctionBlockSnapshot(String name, String type, List<String> inputs, List<String> outputs, List<String> internals) {
        this.name = name;
        this.type = type;
        this.inputs = inputs;
        this.outputs = outputs;
        this.internals = internals;
    }

    public static FunctionBlockSnapshot fromFunctionBlock(FunctionBlock functionBlock) {
        return new FunctionBlockSnapshot(functionBlock.getName(), functionBlock.getType(),
                functionBlock.getInputs().values().stream().map(v -> v.getName()).collect(Collectors.toList()),
                functionBlock.getOutputs().values().stream().map(v -> v.getName()).collect(Collectors.toList()),
                functionBlock.getInternals().values().stream().map(v -> v.getName()).collect(Collectors.toList()));
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }
}
