package shakeanapple.backtracker.parser.fblockdiagramold.model;

import java.util.List;

public class ParsingModuleType {

    private final String name;

    private List<ParsingVariableInfo> inputs;
    private List<ParsingVariableInfo> outputs;
    private List<ParsingVariableInfo> internals;

    public ParsingModuleType(String name, List<ParsingVariableInfo> inputs, List<ParsingVariableInfo> outputs, List<ParsingVariableInfo> internals) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.internals = internals;
        this.name = name;
    }


    public String getTypeName() {
        return name;
    }

    public List<ParsingVariableInfo> getInputs() {
        return inputs;
    }

    public List<ParsingVariableInfo> getOutputs() {
        return outputs;
    }

    public List<ParsingVariableInfo> getInternals() {
        return internals;
    }


}
