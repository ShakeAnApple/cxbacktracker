package shakeanapple.backtracker.core.fblockmapping.model;

import shakeanapple.backtracker.common.variable.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FunctionBlock {
    private final String name;
    private final String type;
    private final int delay;

    private final Map<String, Variable> inputs;
    private final Map<String, Variable> outputs;

    public FunctionBlock(String name, String type, int delay, List<Variable> inputs, List<Variable> outputs) {
        this.name = name;
        this.type = type;
        this.delay = delay;
        this.inputs = inputs.stream().collect(Collectors.toMap(Variable::getName, v -> v));
        this.outputs = outputs.stream().collect(Collectors.toMap(Variable::getName, v -> v));
    }

    public boolean isImmediate(){
        return this.delay == 0;
    }

    public List<Variable> getInputs(){
        return new ArrayList<>(this.inputs.values());
    }

    public List<Variable> getOutputs(){
        return new ArrayList<>(this.outputs.values());
    }
}
