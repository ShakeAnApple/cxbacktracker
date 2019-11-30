package shakeanapple.backtracker.parser.fblockdiagram.fromscratch;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.Variable;
import shakeanapple.backtracker.common.variable.dynamic.BooleanDynamicVariable;
import shakeanapple.backtracker.common.variable.dynamic.IntegerDynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.*;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;
import shakeanapple.backtracker.parser.fblockdiagram.xmlbasiccomponents.ConnectionDescription;
import shakeanapple.backtracker.parser.fblockdiagram.xmlbasiccomponents.ParsingVariableType;
import shakeanapple.backtracker.parser.fblockdiagram.xmlbasiccomponents.TextDeclaration;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ModuleBuilder {
    private final List<String> contents;
    private final BlockDefinitionsCache blockDefinitionsCache = new BlockDefinitionsCache();

    public ModuleBuilder(List<String> contents) {
        this.contents = contents;
    }

    public List<String> extractModule(String moduleType) {
        String startStr = this.contents.stream()
                .filter(str -> str.toLowerCase().contains("module " + moduleType.toLowerCase()))
                .findFirst().orElse(null);

        if (startStr == null) {
            throw new RuntimeException("No module with the type: " + moduleType);
        }

        int startIdx = this.contents.indexOf(startStr);
        List<String> res = this.contents.subList(startIdx + 1, this.contents.size())
                .stream()
                .takeWhile(str -> !str.toLowerCase().startsWith("module"))
                .collect(Collectors.toList());
        res.add(0, startStr);
        return res;
    }

    public FunctionBlockComplex parseRoot(List<String> moduleContents) {
        // root internals -> inputs
        // root outputs -> outputs

        Random r = new Random();

        List<String> inputs = this.readInternals(moduleContents);

        Map<String, ModuleDeclaration> moduleDeclarations = new HashMap<>();
        Map<String, InputVariable> inputVars = new HashMap<>();

        int order = 1;
        for (String inputStr : inputs) {
            String[] parts = inputStr.replace(";", "").trim().split(": ");
            ParsingVariableType type = this.defineType(parts[1]);
            String name = parts[0].trim();
            if (type == null) {
                moduleDeclarations.put(name, new ModuleDeclaration(name, this.readModuleTypeName(parts[1]), this.readInputs(parts[1])));
            } else {
                InputVariable input = new InputVariable(r.nextLong(), type == ParsingVariableType.BOOLEAN ?
                        new BooleanDynamicVariable(new BooleanValueHolder(false), name)
                        : new IntegerDynamicVariable(new IntegerValueHolder(Integer.MIN_VALUE), name), order);
                inputVars.put(name, input);
                order ++;
            }
        }

        Map<String, FunctionBlockComplex> modules = new HashMap<>();
        for (ModuleDeclaration declaration : moduleDeclarations.values()) {

            FunctionBlockComplex block;
            block = this.blockDefinitionsCache.parseAndCache(this.extractModule(declaration.getTypeName())).instance(declaration.getName());

//            if (this.blockDefinitionsCache.definitionExists(declaration.getTypeName())) {
//                block = this.blockDefinitionsCache.get(declaration.getTypeName()).instance(declaration.getName());
//            } else {
//                block = this.blockDefinitionsCache.parseAndCache(this.extractModule(declaration.getTypeName())).instance(declaration.getName());
//            }
            modules.put(declaration.getName(), block);
        }

        Map<Long, InputVariable> diagramInputs = new HashMap<>();
        Map<String, List<ConnectionDescription>> systemInputConnections = new HashMap<>();
        for (ModuleDeclaration declaration: moduleDeclarations.values()){

            FunctionBlockComplex block = modules.get(declaration.getName());
            List<String> blockInputs = declaration.getInputs();
            for (int i = 0; i < blockInputs.size(); i++) {
                String moduleCallInput = blockInputs.get(i);
                String[] varNameParts = moduleCallInput.split("\\.");

                boolean isInverted = varNameParts[0].startsWith("!");
                if (varNameParts.length == 1) {
                    String varName = varNameParts[0].replace("!", "");

                    InputVariable inSystemVar = inputVars.get(varName);
                    InputGate inModuleGate = block.fbInterface().getOrderedInputs().get(i);

                    if (inSystemVar == null) {
                        inModuleGate.input().setValue(this.parseConstant(varName, isInverted));
                    } else {
                        if (!diagramInputs.containsKey(inModuleGate.input().getId())){
                            diagramInputs.put(inModuleGate.input().getId(), inModuleGate.input());
                        }
                        if (!systemInputConnections.containsKey(inSystemVar.getName())){
                            systemInputConnections.put(inSystemVar.getName(), new ArrayList<>());
                        }
                        systemInputConnections.get(inSystemVar.getName()).add(new ConnectionDescription(inModuleGate, isInverted));
                    }
                } else {
                    FunctionBlockComplex moduleFrom = modules.get(varNameParts[0]);
                    OutputGate outGate = moduleFrom.fbInterface().getOutputs().get(varNameParts[1]);
                    InputGate toGate = block.fbInterface().getOrderedInputs().get(i);
                    outGate.makeOutgoingConnection(toGate, outGate.getOwner(), toGate.getOwner(), isInverted);
                    toGate.makeIncomingConnection(outGate, outGate.getOwner(), toGate.getOwner(), isInverted);
                }
            }
        }

        List<OutputVariable> diagramOutputs = new ArrayList<>();
        Map<String, OutputGate> systemOutputConnections = new HashMap<>();
        Map<String, OutputVariable> systemOutputs = this.readOutputs(moduleContents).stream().filter(str -> str.contains(":="))
                .map(str -> {
                    String[] parts = str.trim().replace(";", "").split(" := ");
                    String[] ownerVarParts = parts[1].split("\\.");
                    OutputVariable blockOutput = modules.get(ownerVarParts[0]).fbInterface().getOutputs().values()
                            .stream().filter(out -> out.getName().equals(ownerVarParts[1])).map(OutputGate::output).findFirst().orElse(null);
                    OutputGate blockOutputGate = modules.get(ownerVarParts[0]).fbInterface().getOutputs().values()
                            .stream().filter(out -> out.getName().equals(ownerVarParts[1])).findFirst().orElse(null);
                    OutputVariable systemOutput = new OutputVariable(r. nextLong(), blockOutput.getValue() instanceof BooleanValueHolder
                            ? new BooleanDynamicVariable(new BooleanValueHolder(false), parts[0])
                            : new IntegerDynamicVariable(new IntegerValueHolder(Integer.MIN_VALUE), parts[0]));
                    diagramOutputs.add(blockOutput);
                    systemOutputConnections.put(systemOutput.getName(), blockOutputGate);
                    return systemOutput;
                })
                .collect(Collectors.toMap(Variable::getName, out -> out));

        FunctionBlockComplex root = new FunctionBlockComplex("root", "DIAGRAM",
                new ArrayList<>(inputVars.values()), new ArrayList<>(systemOutputs.values()),
                new Diagram(modules.values().stream().map(m -> (FunctionBlockBase) m).collect(Collectors.toList()), new ArrayList<>(diagramInputs.values()), diagramOutputs));

        // TODO check cautiously if transition should be to GATE or to BLOCK
        systemOutputConnections.forEach((varName, internalGate) -> {
            OutputGate systemGate = root.fbInterface().getOutputs().get(varName);
            systemGate.makeIncomingConnection(internalGate, internalGate.getOwner(), systemGate.getOwner(), false);
            internalGate.makeOutgoingConnection(systemGate, internalGate.getOwner(), systemGate.getOwner(), false);
        } );

        systemInputConnections.forEach((varName, connDescrs) -> {
            InputGate systemGate = root.fbInterface().getInputs().get(varName);
            connDescrs.forEach(connDescr -> {
                systemGate.makeOutgoingConnection(connDescr.getToGate(), systemGate.getOwner(), connDescr.getToGate().getOwner(), connDescr.isInverted());
                connDescr.getToGate().makeIncomingConnection(systemGate, systemGate.getOwner(), connDescr.getToGate().getOwner(), connDescr.isInverted());
            });
        } );

        return root;
    }

    private ValueHolder parseConstant(String varName, boolean isInverted) {
        if (this.isBoolean(varName)) {
            if (isInverted){
                return new BooleanValueHolder(varName.toLowerCase().equals("false"));
            }
            return new BooleanValueHolder(varName.toLowerCase().equals("true"));
        }
        if (this.isInteger(varName)) {
            return new IntegerValueHolder(Integer.valueOf(varName));
        }
        return null;
    }

    private ParsingVariableType defineType(String clue) {
        return this.isBoolean(clue) ? ParsingVariableType.BOOLEAN :
                (this.isInteger(clue) ? ParsingVariableType.INTEGER : null);
    }

    private String readModuleTypeName(String moduleSpec) {
        int nextIdx = 0;
        char next = moduleSpec.charAt(nextIdx);
        StringBuilder name = new StringBuilder();
        while (next != '(') {
            name.append(next);
            nextIdx++;
            next = moduleSpec.charAt(nextIdx);
        }
        return name.toString();
    }

    private boolean isInteger(String clue) {
        if (clue.toLowerCase().equals("integer")) {
            return true;
        }
        try {
            Integer.parseInt(clue.substring(0, 1));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean isBoolean(String clue) {
        if (clue.equals("boolean") || clue.startsWith("!") || clue.contains("&") || clue.contains("|")) {
            return true;
        }
        if (clue.toLowerCase().equals("true") || clue.toLowerCase().equals("false")) {
            return true;
        }
        return false;
    }

    private List<String> readOutputs(List<String> moduleContents) {
        return moduleContents.subList(moduleContents.indexOf("DEFINE") + 1, moduleContents.indexOf("ASSIGN"));
    }

    private List<String> readInternals(List<String> moduleContents) {
        return moduleContents.subList(moduleContents.indexOf("VAR") + 1, moduleContents.indexOf("DEFINE"));
    }

    private List<String> readInputs(String moduleSignature) {
        Pattern p = Pattern.compile("\\((.*?)\\)");
        Matcher m = p.matcher(moduleSignature);
        String inputsString = "";

        if (m.find()) {
            inputsString = m.group(1);
        }
        if (inputsString == null || inputsString.isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.asList(inputsString.split(", "));
    }
}
