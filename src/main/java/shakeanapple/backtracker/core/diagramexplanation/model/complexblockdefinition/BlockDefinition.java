package shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition;


import shakeanapple.backtracker.core.diagramexplanation.model.FBInterface;
import shakeanapple.backtracker.core.diagramexplanation.model.InputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.choice.Choice;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.*;
import java.util.stream.Collectors;

public class BlockDefinition {
    private List<InputDefinition> inputs;

    private List<OutputDefinition> outputs;

    private List<BasicComponentDefinitionAbstract> components;

    private List<ConnectionDefinition> connections;

    private String typeName;

    public BlockDefinition(String typeName, List<InputDefinition> inputs, List<OutputDefinition> outputs, List<BasicComponentDefinitionAbstract> components, List<ConnectionDefinition> connections) {
        this.inputs = inputs;
        this.outputs = outputs;
        this.components = components;
        this.connections = connections;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public List<InputDefinition> getInputs() {
        return this.inputs;
    }

    public List<OutputDefinition> getOutputs() {
        return this.outputs;
    }

    public List<BasicComponentDefinitionAbstract> getComponents() {
        return this.components;
    }

    public List<ConnectionDefinition> getConnections() {
        return this.connections;
    }

    public FunctionBlockComplex instance(String fbName) {
        Map<Long, InputVariable> inInterfaceVars = this.inputs.stream().collect(Collectors.toMap(InputDefinition::getId, InputDefinition::translate));
        Map<Long, OutputVariable> outInterfaceVars = this.outputs.stream().collect(Collectors.toMap(OutputDefinition::getId, OutputDefinition::translate));

        Map<Long, List<Long>> blockInputIds = new HashMap<>();
        Map<Long, List<Long>> blockOutputIds = new HashMap<>();

        Map<Long, InputVariable> compInputs = new HashMap<>();
        Map<Long, OutputVariable> compOutputs = new HashMap<>();

        Map<Long, DiagramElement> blocks = new HashMap<>();

        for (BasicComponentDefinitionAbstract def : this.components) {
            blockInputIds.put(def.getId(), def.getInputs().stream().map(InputDefinition::getId).collect(Collectors.toList()));
            blockOutputIds.put(def.getId(), def.getOutputs().stream().map(OutputDefinition::getId).collect(Collectors.toList()));

            FunctionBlockBasic block;
            if (def instanceof ChoiceComponentDefinition) {
                ChoiceComponentDefinition choiceDef = (ChoiceComponentDefinition) def;
                List<Choice> choices = new ArrayList<>();
                for (ChoiceDefinition chDef : choiceDef.getChoices()) {
                    InputVariable condition = chDef.getCondition().translate();
                    compInputs.put(chDef.getCondition().getId(), condition);

                    InputVariable output = chDef.getOutput().translate();
                    compInputs.put(chDef.getOutput().getId(), output);

                    choices.add(new Choice(condition, output));
                }
                OutputVariable output = choiceDef.getOutput().translate();
                compOutputs.put(choiceDef.getOutput().getId(), output);

                block = FunctionBlockBasic.choiceInstance(choices, output);

            } else if (def instanceof DelayComponentDefinition) {
                DelayComponentDefinition delayDef = (DelayComponentDefinition) def;
                InputVariable input = delayDef.getInput().translate();
                compInputs.put(delayDef.getInput().getId(), input);

                InputVariable defValue = delayDef.getDefValue() != null ? delayDef.getDefValue().translate() : null;
                if (defValue != null){
                    compInputs.put(delayDef.getDefValue().getId(), defValue);
                }

                OutputVariable output = delayDef.getOutput().translate();
                compOutputs.put(delayDef.getOutput().getId(), output);


                block = FunctionBlockBasic.delayInstance(input, defValue, output, delayDef.getDelay());

            } else {
                BasicComponentDefinition basicDef = (BasicComponentDefinition) def;
                Map<Long, InputVariable> inputs = basicDef.getInputs().stream().collect(Collectors.toMap(InputDefinition::getId, InputDefinition::translate));
                Map<Long, OutputVariable> outputs = basicDef.getOutputs().stream().collect(Collectors.toMap(OutputDefinition::getId, OutputDefinition::translate));

                compInputs.putAll(inputs);
                compOutputs.putAll(outputs);

                block = FunctionBlockBasic.instance(basicDef.getType(), inputs.values().stream().sorted(Comparator.comparing(InputVariable::getOrder)).collect(Collectors.toList()), new ArrayList<>(outputs.values()));
            }
            blocks.put(def.getId(), block);
        }

        Map<Long, InputGate> inVarsGates = inInterfaceVars.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, in -> new InputGate(in.getValue())));
        Map<Long, OutputGate> outVarsGates = outInterfaceVars.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, in -> new OutputGate(in.getValue())));

        List<InputVariable> diagramInputs = new ArrayList<>();
        List<OutputVariable> diagramOutputs = new ArrayList<>();

        for (ConnectionDefinition conn : this.connections) {

            DiagramElement fbFrom;
            DiagramElement fbTo;

            OutputVariable fromVar;
            InputVariable toVar;

            if (inInterfaceVars.containsKey(conn.getFromVarId())){
                fbFrom = inVarsGates.get(conn.getFromVarId());
                fromVar = ((InputGate)fbFrom).output();
            } else{
                Long fromBlockId = blockOutputIds.entrySet().stream().filter(bi -> bi.getValue().contains(conn.getFromVarId()))
                        .map(Map.Entry::getKey).findFirst()
                        .orElseThrow(() -> new RuntimeException("Can't find block for output " + conn.getFromVarId()));
                fbFrom = blocks.get(fromBlockId);
                fromVar = compOutputs.get(conn.getFromVarId());
            }

            if ((outInterfaceVars.containsKey(conn.getToVarId()))){
                fbTo = outVarsGates.get(conn.getToVarId());
                toVar = ((OutputGate)fbTo).input();
            } else {
                Long toBlockId = blockInputIds.entrySet().stream().filter(bi -> bi.getValue().contains(conn.getToVarId()))
                        .map(Map.Entry::getKey).findFirst()
                        .orElseThrow(() -> new RuntimeException("Can't find block for input " + conn.getToVarId()));
                fbTo = blocks.get(toBlockId);
                toVar = compInputs.get(conn.getToVarId());
            }

            if (fbFrom instanceof InputGate){
                diagramInputs.add(toVar);
            }

            if (fbTo instanceof OutputGate){
                diagramOutputs.add(fromVar);
            }

            fromVar.connect(toVar, fbFrom, fbTo, conn.isInverted());
            toVar.connect(fromVar, fbFrom, fbTo, conn.isInverted());
        }

        FBInterface fbInterface = new FBInterface(inVarsGates.values().stream().sorted(Comparator.comparing(in -> in.input().getOrder())).collect(Collectors.toList()), new ArrayList<>(outVarsGates.values()));
        return new FunctionBlockComplex(fbName, this.typeName, fbInterface,
                new Diagram(new ArrayList<>(blocks.values()), diagramInputs, diagramOutputs));
    }
}
