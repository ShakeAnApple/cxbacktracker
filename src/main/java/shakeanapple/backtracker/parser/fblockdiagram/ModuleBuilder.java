package shakeanapple.backtracker.parser.fblockdiagram;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.BooleanDynamicVariable;
import shakeanapple.backtracker.common.variable.dynamic.IntegerDynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.FBInterface;
import shakeanapple.backtracker.core.diagramexplanation.model.InputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.Diagram;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.BlockDefinition;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ModuleBuilder {
    private final List<String> contents;

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

    public FunctionBlockComplex parseRoot(List<String> moduleContents, Map<String, BlockDefinition> blockDefinitions) {
        // root internals -> inputs
        // root outputs -> outputs

        List<String> inputs = this.readInternals(moduleContents);

        Map<String, TextDeclaration> moduleDeclarations = new HashMap<>();
        Map<String, InputGate> inputGates = new HashMap<>();

        int order = 1;
        for (String inputStr : inputs) {
            String[] parts = inputStr.replace(";", "").trim().split(": ");
            ParsingVariableType type = this.defineType(parts[1]);
            String name = parts[0].trim();
            if (type == null) {
                moduleDeclarations.put(name, new TextDeclaration(name, parts[1]));
            } else {
                InputVariable input = new InputVariable(type == ParsingVariableType.BOOLEAN ?
                        new BooleanDynamicVariable(new BooleanValueHolder(false), name)
                        : new IntegerDynamicVariable(new IntegerValueHolder(Integer.MIN_VALUE), name), order);
                InputGate inGate = new InputGate(input);
                inputGates.put(name, inGate);
                order ++;
            }
        }

        Map<String, FunctionBlockComplex> modules = new HashMap<>();
        for (TextDeclaration declaration : moduleDeclarations.values()) {
            String moduleName = declaration.getName();
            String moduleTypeName = this.readModuleTypeName(declaration.getValue());

            FunctionBlockComplex block = blockDefinitions.get(moduleTypeName).instance(moduleName);
            modules.put(moduleName, block);
        }

        List<OutputVariable> diagramOutputs = new ArrayList<>();
        List<OutputGate> outputGates = this.readOutputs(moduleContents).stream().filter(str -> str.contains(":="))
                .map(str -> {
                    String[] parts = str.trim().replace(";", "").split(" := ");
                    String[] ownerVarParts = parts[1].split("\\.");
                    OutputVariable blockOutput = modules.get(ownerVarParts[0]).fbInterface().getOutputs().values()
                            .stream().filter(out -> out.getName().equals(ownerVarParts[1])).map(OutputGate::output).findFirst().orElse(null);
                    OutputGate blockOutputGate = modules.get(ownerVarParts[0]).fbInterface().getOutputs().values()
                            .stream().filter(out -> out.getName().equals(ownerVarParts[1])).findFirst().orElse(null);
                    OutputVariable systemOutput = new OutputVariable(blockOutput.getValue() instanceof BooleanValueHolder
                            ? new BooleanDynamicVariable(new BooleanValueHolder(false), parts[0])
                            : new IntegerDynamicVariable(new IntegerValueHolder(Integer.MIN_VALUE), parts[0]));
                    OutputGate outGate = new OutputGate(systemOutput);
                    outGate.makeIncomingConnection(blockOutputGate, modules.get(ownerVarParts[0]), outGate, false);
                    blockOutputGate.makeOutgoingConnection(outGate, modules.get(ownerVarParts[0]), outGate, false);
                    diagramOutputs.add(blockOutput);
                    return outGate;
                })
                .collect(Collectors.toList());

        List<InputVariable> diagramInputs = this.establishConnections(modules, moduleDeclarations, inputGates);
        return new FunctionBlockComplex("root", "DIAGRAM",
                new FBInterface(new ArrayList<>(inputGates.values()), outputGates),
                new Diagram(modules.values().stream().map(m -> (FunctionBlockBase) m).collect(Collectors.toList()), diagramInputs, diagramOutputs));
    }

    private List<InputVariable> establishConnections(Map<String, FunctionBlockComplex> modules, Map<String, TextDeclaration> moduleDeclarations, Map<String, InputGate> inputGates) {
        List<InputVariable> diagramInputs = new ArrayList<>();
        for (TextDeclaration declaration : moduleDeclarations.values()) {

            FunctionBlockComplex moduleTo = modules.get(declaration.getName());

            List<String> inputs = this.readInputs(declaration.getValue());
            for (int i = 0; i < inputs.size(); i++) {
                String moduleCallInput = inputs.get(i);
                String[] varNameParts = moduleCallInput.split("\\.");

                boolean isInverted = varNameParts[0].startsWith("!");
                if (varNameParts.length == 1) {
                    String varName = varNameParts[0].replace("!", "");

                    InputGate inSystemGate = inputGates.get(varName);
                    InputGate inModuleGate = moduleTo.fbInterface().getOrderedInputs().get(i);

                    if (inSystemGate == null) {
                        inModuleGate.input().setValue(this.parseConstant(varName, isInverted));
                    } else {
                        diagramInputs.add(inModuleGate.input());
                        inModuleGate.makeIncomingConnection(inSystemGate, inSystemGate, moduleTo, isInverted);
                        inSystemGate.makeOutgoingConnection(inModuleGate, inSystemGate, moduleTo, isInverted);
                    }
                } else {
                    FunctionBlockComplex moduleFrom = modules.get(varNameParts[0]);
                    OutputGate outGate = moduleFrom.fbInterface().getOutputs().get(varNameParts[1]);
                    InputGate toGate = moduleTo.fbInterface().getOrderedInputs().get(i);
                    outGate.makeOutgoingConnection(toGate, moduleFrom, moduleTo, isInverted);
                    toGate.makeIncomingConnection(outGate, moduleFrom, moduleTo, isInverted);
                }
            }
        }
        return diagramInputs;
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
