package shakeanapple.backtracker.core.diagramexplanation.model.snapshot;

import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlock;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionBlockSnapshot {
    private final String name;
    private final String type;

    private final List<String> inputs;
    private final List<String> outputs;
    private final List<String> internals;

    private final boolean isRoot;

    public FunctionBlockSnapshot(boolean isRoot, String name, String type, List<String> inputs, List<String> outputs, List<String> internals) {
        this.name = name;
        this.type = type;
        this.inputs = inputs;
        this.outputs = outputs;
        this.internals = internals;

        this.isRoot = isRoot;
    }

    public static FunctionBlockSnapshot fromFunctionBlock(FunctionBlock functionBlock) {
        return new FunctionBlockSnapshot(functionBlock.isRoot(), functionBlock.getName(), functionBlock.getType(),
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

    public boolean isRoot() {
        return this.isRoot;
    }

    public List<String> getInputs() {
        return this.inputs;
    }

    public List<String> getOutputs() {
        return this.outputs;
    }
}
