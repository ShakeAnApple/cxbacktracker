package shakeanapple.backtracker.core.fblockmapping.model;

import java.util.List;

public class FunctionBlockInfo {
    private String type;

    private List<FunctionBlockVariableInfo> inputs;
    private List<FunctionBlockVariableInfo> outputs;
    private List<FunctionBlockVariableInfo> internals;

    public FunctionBlockInfo(String type, List<FunctionBlockVariableInfo> inputs, List<FunctionBlockVariableInfo> outputs, List<FunctionBlockVariableInfo> internals) {
        this.type = type;
        this.inputs = inputs;
        this.outputs = outputs;
        this.internals = internals;
    }

    public String getType() {
        return this.type;
    }

    public List<FunctionBlockVariableInfo> getInputs() {
        return this.inputs;
    }

    public List<FunctionBlockVariableInfo> getOutputs() {
        return this.outputs;
    }

    public List<FunctionBlockVariableInfo> getInternals() {
        return this.internals;
    }
}
