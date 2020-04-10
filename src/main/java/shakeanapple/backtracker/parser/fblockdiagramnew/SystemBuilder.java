package shakeanapple.backtracker.parser.fblockdiagramnew;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.BooleanDynamicVariable;
import shakeanapple.backtracker.common.variable.dynamic.IntegerDynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.*;
import java.util.stream.Collectors;

public class SystemBuilder {
    private final String rootName;
    private BlockDefinitionsCache basicDefinitionsCache = new BlockDefinitionsCache();
//    private Map<String, FunctionBlockComplex> complexBlocks = new HashMap<>();
    private Map<String, ParsingModule> systemParsingModules;

    private Random random = new Random();

    public SystemBuilder(String rootName, List<ParsingModule> systemModules) {
        this.rootName = rootName;
        this.systemParsingModules = systemModules.stream().collect(Collectors.toMap(ParsingModule::getModuleType, m -> m));
    }

    public FunctionBlockComplex buildRoot(ParsingModule root) {
        return this.buildComplex(root, rootName);
    }

    private FunctionBlockComplex buildComplex(ParsingModule m, String name) {

        List<InputVariable> diagramInputs = new ArrayList<>();
        List<OutputVariable> diagramOutputs = new ArrayList<>();

        Map<String, FunctionBlockBase> functionBlocks = new HashMap<>();
        for (ModuleDeclaration declaration : m.getDependencies()) {
            ParsingModule parsingModule = this.systemParsingModules.get(declaration.getTypeName());
            FunctionBlockBase newBlock = parsingModule.isComplex() ? this.buildComplex(parsingModule, declaration.getName()) :
                    this.basicDefinitionsCache.parseAndCache(parsingModule.getContents()).instance(declaration.getName());
            functionBlocks.put(declaration.getName(), newBlock);
        }

        List<ConnectionDescription> systemInputInterfaceConnections = new ArrayList<>();
        List<ConnectionDescription> systemOutputInterfaceConnections = new ArrayList<>();
        for (ModuleDeclaration declaration : m.getDependencies()) {
            FunctionBlockBase blockTo = functionBlocks.get(declaration.getName());
            for (int i = 0; i < declaration.getInputs().size(); i++) {
                Assignment input = declaration.getInputs().get(i);
                Gate gateTo = blockTo.fbInterface().getOrderedInputs().get(i);
                if (input.isFromOuterInterface()) {
                    diagramInputs.add(gateTo.input());
                    systemInputInterfaceConnections.add(new ConnectionDescription(input.getNotNegatedContent(), null, gateTo, input.isNegated()));
                } else if (input.isConstBoolean() || input.isConstInteger()) {
                    gateTo.input().setValue(this.parseConstant(input));
                } else {
                    FunctionBlockBase blockFrom = functionBlocks.get(input.getBlockFromName());
                    Gate gateFrom = blockFrom.fbInterface().getOutputs().get(input.getVarFromName());
                    gateFrom.makeOutgoingConnection(gateTo, blockFrom, blockTo, input.isNegated());
                    gateTo.makeIncomingConnection(gateFrom, blockFrom, blockTo, input.isNegated());
                }
            }
        }

        List<InputVariable> interfaceInputs = m.getInputs().stream().map(in -> new InputVariable(random.nextLong(),
                in.getType() == VarType.BOOLEAN ? new BooleanDynamicVariable(new BooleanValueHolder(false), in.getName())
                        : new IntegerDynamicVariable(new IntegerValueHolder(Integer.MIN_VALUE), in.getName()), in.getOrder())).collect(Collectors.toList());

        List<OutputVariable> interfaceOutputs = new ArrayList<>();
        for (ParsingOutputVariable out : m.getOutputs()) {
            VarType type = out.getType();
            Assignment as = out.getAssignment();
            if (type == VarType.UNKNOWN) {
                ParsingInputVariable correspondingInput;
                if (as.isFromOuterInterface()) {
                    correspondingInput = m.getInputs().stream()
                            .filter(in -> in.getName().equals(out.getAssignment().getNotNegatedContent()))
                            .findFirst().orElse(null);
                    type = correspondingInput.getType();
                } else if (as.isConstBoolean()) {
                    type = VarType.BOOLEAN;
                } else if (as.isConstInteger()) {
                    type = VarType.INTEGER;
                } else {
                    Gate from = functionBlocks.get(as.getBlockFromName()).fbInterface().getOutputs().get(as.getVarFromName());
                    type = from.input().getValue() instanceof BooleanValueHolder ? VarType.BOOLEAN : VarType.INTEGER;
                }
            }
            OutputVariable outputVariable = new OutputVariable(this.random.nextLong(),
                    type == VarType.BOOLEAN ? new BooleanDynamicVariable(new BooleanValueHolder(false), out.getName())
                            : new IntegerDynamicVariable(new IntegerValueHolder(Integer.MIN_VALUE), out.getName()));
            interfaceOutputs.add(outputVariable);

            if (!as.isFromOuterInterface() && !as.isConstBoolean() && !as.isConstInteger()) {
                Gate gateFrom = functionBlocks.get(as.getBlockFromName()).fbInterface().getOutputs().get(as.getVarFromName());
                systemOutputInterfaceConnections.add(new ConnectionDescription(out.getName(), gateFrom, null, as.isNegated()));
            }
            if (as.isConstInteger() || as.isConstBoolean()) {
                outputVariable.setValue(this.parseConstant(as));
            }
            diagramOutputs.add(outputVariable);
        }

        Diagram diagram = new Diagram(new ArrayList<>(functionBlocks.values()), diagramInputs, diagramOutputs);
        FunctionBlockComplex fb = new FunctionBlockComplex(name, m.getModuleType(), interfaceInputs, interfaceOutputs, diagram);

        for (ConnectionDescription connDescr : systemInputInterfaceConnections) {
            Gate fromGate = fb.fbInterface().getInputs().get(connDescr.getInterfaceVarName());
            fromGate.makeOutgoingConnection(connDescr.getToGate(), fromGate.getOwner(), connDescr.getToGate().getOwner(), connDescr.isInverted());
            connDescr.getToGate().makeIncomingConnection(fromGate, fromGate.getOwner(), connDescr.getToGate().getOwner(), connDescr.isInverted());
        }

        for (ConnectionDescription connDescr : systemOutputInterfaceConnections) {
            Gate toGate = fb.fbInterface().getOutputs().get(connDescr.getInterfaceVarName());
            toGate.makeIncomingConnection(connDescr.getFromGate(), connDescr.getFromGate().getOwner(), toGate.getOwner(), connDescr.isInverted());
            connDescr.getFromGate().makeOutgoingConnection(toGate, connDescr.getFromGate().getOwner(), toGate.getOwner(), connDescr.isInverted());
        }

//        this.complexBlocks.put(m.getModuleType(), fb);
        return fb;
    }

    private ValueHolder parseConstant(Assignment input) {
        if (input.isConstBoolean()) {
            return new BooleanValueHolder(input.getNotNegatedContent().toLowerCase().equals("true"));
        }
        if (input.isConstInteger()) {
            return new IntegerValueHolder(Integer.parseInt(input.getNotNegatedContent()));
        }
        throw new RuntimeException(input.getNotNegatedContent() + " not a constant!");
    }

}
