package shakeanapple.backtracker.parser.fblockdiagram.model;

import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlock;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.FBVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParsingModule {
    private final String name;
    private boolean isRoot;
    private final ParsingModuleType info;

    private List<ParsingInput> inputs;
    private List<ParsingOutput> outputs;
    private List<ParsingModuleVariable> internals;

    private Map<String, ParsingOutput> outputsMap;
    private Map<String, ParsingInput> inputsMap;


    public ParsingModule(String name, ParsingModuleType info) {
        this.name = name;
        this.info = info;
        this.isRoot = false;

        this.inputs = info.getInputs().stream().map(ParsingInput::new).collect(Collectors.toList());
        this.outputs = info.getOutputs().stream().map(ParsingOutput::new).collect(Collectors.toList());
        this.internals = info.getInternals().stream().map(ParsingModuleVariable::new).collect(Collectors.toList());

        this.updateOutputsMap();
        this.updateInputsMap();
    }

    public ParsingModule(String name, boolean isRoot, ParsingModuleType info) {
        this.name = name;
        this.info = info;
        this.isRoot = isRoot;

        this.inputs = info.getInputs().stream().map(ParsingInput::new).collect(Collectors.toList());
        this.outputs = info.getOutputs().stream().map(ParsingOutput::new).collect(Collectors.toList());
        this.internals = info.getInternals().stream().map(ParsingModuleVariable::new).collect(Collectors.toList());

        this.updateOutputsMap();
        this.updateInputsMap();
    }

    public String getName() {
        return name;
    }

    public List<ParsingInput> getInputs() {
        return inputs;
    }

    public ParsingModuleType getTypeInfo() {
        return info;
    }

    private void updateOutputsMap() {
        this.outputsMap = this.outputs.stream().collect(Collectors.toMap(out -> out.getInfo().getName(), out -> out));
    }

    private void updateInputsMap() {
        this.inputsMap = this.inputs.stream().collect(Collectors.toMap(in -> in.getInfo().getName(), in -> in));
    }

    public void addOutput(ParsingOutput var){
        this.outputs.add(var);
        this.updateOutputsMap();
    }

    public void addInput(ParsingInput var){
        this.inputs.add(var);
        this.updateInputsMap();
    }

    public Map<String, ParsingOutput> getOutputsMap() {
        return this.outputsMap;
    }

    public FunctionBlock translate() {

        Map<String, InputVariable> inputs = new HashMap<>();
        Map<String, OutputVariable> outputs = new HashMap<>();
        Map<String, FBVariable> internals = new HashMap<>();

        for (ParsingInput input : this.inputs) {
            inputs.put(input.getInfo().getName(), (InputVariable) input.translate());
        }

        for (ParsingOutput output : this.outputs) {
            outputs.put(output.getInfo().getName(), (OutputVariable) output.translate());
        }

        for (ParsingModuleVariable internal : this.internals) {
            internals.put(internal.getInfo().getName(), internal.translate());
        }

        return new FunctionBlock(this.name, this.info.getTypeName(), this.isRoot, inputs, outputs, internals);
    }

    public Map<String, ParsingInput> getInputsMap() {
        return this.inputsMap;
    }
}
