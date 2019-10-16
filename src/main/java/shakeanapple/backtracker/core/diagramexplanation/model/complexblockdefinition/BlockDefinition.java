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
import java.util.stream.Collector;
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

        Map<Long, InputGate> compInputs = new HashMap<>();
        Map<Long, OutputGate> compOutputs = new HashMap<>();

        Map<Long, DiagramElement> blocks = new HashMap<>();

        for (BasicComponentDefinitionAbstract def : this.components) {
            blockInputIds.put(def.getId(), def.getInputs().stream().map(InputDefinition::getId).collect(Collectors.toList()));
            blockOutputIds.put(def.getId(), def.getOutputs().stream().map(OutputDefinition::getId).collect(Collectors.toList()));

            FunctionBlockBasic block;
            if (def instanceof ChoiceComponentDefinition) {
                ChoiceComponentDefinition choiceDef = (ChoiceComponentDefinition) def;
                List<Choice> choices = choiceDef.getChoices().stream()
                        .map(chDef -> new Choice(chDef.getCondition().translate(), chDef.getOutput().translate())).collect(Collectors.toList());

                block = FunctionBlockBasic.choiceInstance(choices, choiceDef.getOutput().translate());

                for(ChoiceDefinition chDef : choiceDef.getChoices()){
                    compInputs.put(chDef.getCondition().getId(), block.fbInterface().getInputs().get(chDef.getCondition().getName()));
                    compInputs.put(chDef.getOutput().getId(), block.fbInterface().getInputs().get(chDef.getOutput().getName()));
                }

                compOutputs.put(choiceDef.getOutput().getId(), block.fbInterface().getOutputs().values().stream().findFirst().get());

            } else if (def instanceof DelayComponentDefinition) {
                DelayComponentDefinition delayDef = (DelayComponentDefinition) def;
                InputVariable input = delayDef.getInput().translate();
                InputVariable defValue = delayDef.getDefValue() != null ? delayDef.getDefValue().translate() : null;
                OutputVariable output = delayDef.getOutput().translate();

                block = FunctionBlockBasic.delayInstance(input, defValue, output, delayDef.getDelay());

                if (defValue != null){
                    compInputs.put(delayDef.getDefValue().getId(), block.fbInterface().getInputs().get(delayDef.getDefValue().getName()));
                }
                compInputs.put(delayDef.getInput().getId(), block.fbInterface().getInputs().get(delayDef.getInput().getName()));
                compOutputs.put(delayDef.getOutput().getId(), block.fbInterface().getOutputs().values().stream().findFirst().get());
            } else {
                BasicComponentDefinition basicDef = (BasicComponentDefinition) def;
                List<InputVariable> inputs = basicDef.getInputs().stream().map(InputDefinition::translate)
                        .sorted(Comparator.comparing(InputVariable::getOrder)).collect(Collectors.toList());
                List<OutputVariable> outputs = basicDef.getOutputs().stream().map(OutputDefinition::translate).collect(Collectors.toList());

                block = FunctionBlockBasic.instance(basicDef.getType(), inputs, outputs);

                compInputs.putAll(basicDef.getInputs().stream().collect(Collectors.toMap(InputDefinition::getId, in -> block.fbInterface().getInputs().get(in.getName()))));
                compOutputs.putAll(basicDef.getOutputs().stream().collect(Collectors.toMap(OutputDefinition::getId, out -> block.fbInterface().getOutputs().get(out.getName()))));
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

            Gate fromGate;
            Gate toGate;

            if (inInterfaceVars.containsKey(conn.getFromVarId())){
                fbFrom = inVarsGates.get(conn.getFromVarId());
                fromGate = ((InputGate)fbFrom);
            } else{
                Long fromBlockId = blockOutputIds.entrySet().stream().filter(bi -> bi.getValue().contains(conn.getFromVarId()))
                        .map(Map.Entry::getKey).findFirst()
                        .orElseThrow(() -> new RuntimeException("Can't find block for output " + conn.getFromVarId()));
                fbFrom = blocks.get(fromBlockId);
                fromGate = compOutputs.get(conn.getFromVarId());
            }

            if ((outInterfaceVars.containsKey(conn.getToVarId()))){
                fbTo = outVarsGates.get(conn.getToVarId());
                toGate = ((OutputGate)fbTo);
            } else {
                Long toBlockId = blockInputIds.entrySet().stream().filter(bi -> bi.getValue().contains(conn.getToVarId()))
                        .map(Map.Entry::getKey).findFirst()
                        .orElseThrow(() -> new RuntimeException("Can't find block for input " + conn.getToVarId()));
                fbTo = blocks.get(toBlockId);
                toGate = compInputs.get(conn.getToVarId());
            }

            if (fbFrom instanceof InputGate){
                diagramInputs.add(toGate.input());
            }

            if (fbTo instanceof OutputGate){
                diagramOutputs.add(fromGate.output());
            }

            fromGate.makeOutgoingConnection(toGate, fbFrom, fbTo, conn.isInverted());
            toGate.makeIncomingConnection(fromGate, fbFrom, fbTo, conn.isInverted());
        }

        FBInterface fbInterface = new FBInterface(inVarsGates.values().stream().sorted(Comparator.comparing(in -> in.input().getOrder())).collect(Collectors.toList()), new ArrayList<>(outVarsGates.values()));
        return new FunctionBlockComplex(fbName, this.typeName, fbInterface,
                new Diagram(new ArrayList<>(blocks.values()), diagramInputs, diagramOutputs));
    }
}
