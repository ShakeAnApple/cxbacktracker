package shakeanapple.backtracker.core.fblockmapping.model;

import shakeanapple.backtracker.core.fblockmapping.model.variable.*;

import java.util.Map;

public class FunctionBlock {
    private final String name;
    private String type;

    private Map<String, InputVariable> inputs;
    private Map<String, OutputVariable> outputs;
    private Map<String, FBVariable> internals;

    public FunctionBlock(String name, String type,
                         Map<String, InputVariable> inputs, Map<String, OutputVariable> outputs, Map<String, FBVariable> internals) {
        this.name = name;
        this.type = type;
        this.inputs = inputs;
        this.outputs = outputs;
        this.internals = internals;
    }

    public String getName() {
        return this.name;
    }

    public String getType(){
        return this.type;
    }

    public Map<String, InputVariable> getInputs() {
        return this.inputs;
    }

    public Map<String, OutputVariable> getOutputs() {
        return this.outputs;
    }

    public Map<String, FBVariable> getInternals() {
        return this.internals;
    }
}
