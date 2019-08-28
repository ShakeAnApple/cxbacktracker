package shakeanapple.backtracker.parser.fblockdiagram;

import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlockInfo;
import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlockVariableInfo;

import java.util.List;
import java.util.stream.Collectors;

public class TextModuleDescription {
    private final String name;
    private List<TextVariable> inputs;
    private List<TextVariable> outputs;
    private List<TextVariable> internals;

    public TextModuleDescription(String name, List<TextVariable> inputs, List<TextVariable> outputs, List<TextVariable> internals) {
        this.name = name;
        this.inputs = inputs;
        this.outputs = outputs;
        this.internals = internals;
    }

    public String getName() {
        return this.name;
    }

    public List<TextVariable> getInputs() {
        return this.inputs;
    }

    public List<TextVariable> getOutputs() {
        return this.outputs;
    }

    public List<TextVariable> getInternals() {
        return this.internals;
    }

    public FunctionBlockInfo translate() {
        List<FunctionBlockVariableInfo> inputs = this.inputs.stream().map(var -> var.translate()).collect(Collectors.toList());
        List<FunctionBlockVariableInfo> outputs = this.outputs.stream().map(var -> var.translate()).collect(Collectors.toList());
        List<FunctionBlockVariableInfo> internals = this.internals.stream()
                .filter(var -> var.getType().getTypeSpec() != VarType.MODULE)
                .map(var -> var.translate()).collect(Collectors.toList());

        return new FunctionBlockInfo(this.name, inputs, outputs, internals);
    }
}
