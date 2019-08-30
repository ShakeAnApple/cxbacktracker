package shakeanapple.backtracker.parser.fblockdiagram;

import shakeanapple.backtracker.parser.fblockdiagram.model.*;
import shakeanapple.backtracker.parser.fblockdiagram.typepropagation.TypePropagationConstantNode;
import shakeanapple.backtracker.parser.fblockdiagram.typepropagation.TypePropagationNode;
import shakeanapple.backtracker.parser.fblockdiagram.typepropagation.TypePropagationVariableNode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ModuleBuilder {
    private final List<String> contents;

    private Map<String, TypePropagationNode> typeConnectedVars;

    public ModuleBuilder(List<String> contents) {
        this.contents = contents;
        this.typeConnectedVars = new HashMap<>();
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

    public ParsingModuleType parseModule(String moduleTypeName, List<String> moduleContents) {
        List<String> inputs = this.readInputs(moduleContents.get(0));
        List<String> internals = this.readInternals(moduleContents);
        List<String> outputs = this.readOutputs(moduleContents);

        List<ParsingVariableInfo> internalVariables = this.parseInternals(internals);
        for (ParsingVariableInfo var : internalVariables) {
            this.typeConnectedVars.put(moduleTypeName + "." + var.getName(), new TypePropagationVariableNode(var));
        }

        List<ParsingVariableInfo> inputVariables = inputs.stream()
                .map(str -> {
                    ParsingVariableInfo info = new ParsingVariableInfo(str, ParsingVariableType.UNKNOWN);
                    this.typeConnectedVars.put(moduleTypeName + "." + info.getName(), new TypePropagationVariableNode(info));
                    return info;
                }).collect(Collectors.toList());

        List<ParsingVariableInfo> outputVariables = this.parseOutputs(outputs, moduleTypeName);
        return new ParsingModuleType(moduleTypeName, inputVariables, outputVariables, internalVariables);
    }

    private List<ParsingVariableInfo> parseOutputs(List<String> outputs, String moduleTypeName) {
        return outputs
                .stream()
                .filter(str -> str.contains(":="))
                .map(str -> {
                    String[] parts = str.trim().replace(";", "").split(" := ");
                    if (parts.length < 2) {
                        String varName = parts[0].replace(":=", "").trim();
                        List<String> caseDefs = this.extractCase(varName, outputs);
                        String lastDef = caseDefs.get(caseDefs.size() - 1).trim().split(": ")[0].trim();
                        ParsingVariableType type = this.defineType(lastDef);
                        ParsingVariableInfo out = new ParsingVariableInfo(varName, type);
                        this.typeConnectedVars.put(moduleTypeName + "." + out.getName(), new TypePropagationVariableNode(out));
                        return out;
                    }
                    ParsingVariableType type = this.defineType(parts[1]);
                    ParsingVariableInfo out = new ParsingVariableInfo(parts[0], type);
                    if (type == null) {
                        this.typeConnectedVars.get(moduleTypeName + "." + parts[1].replace("!", "")).connect(out, moduleTypeName);
                    } else {
                        this.typeConnectedVars.put(moduleTypeName + "." + parts[0].replace("!", ""), new TypePropagationVariableNode(out));
                    }
                    return out;
                }).collect(Collectors.toList());
    }

    private List<String> extractCase(String varName, List<String> contents) {
        String startStr = contents.stream().filter(str -> str.contains(varName)).findFirst().get();
        return contents.subList(contents.indexOf(startStr) + 2, contents.size())
                .stream()
                .takeWhile(str -> !str.contains("esac"))
                .collect(Collectors.toList());
    }

    public ParsingDiagram parseRoot(List<String> moduleContents) {
        // root internals -> outputs
        // root outputs -> inputs

        List<String> outputs = this.readInternals(moduleContents);

        Map<String, TextDeclaration> moduleDeclarations = new HashMap<>();
        List<ParsingVariableInfo> outputVars = new ArrayList<>();

        for (String outputStr : outputs) {
            String[] parts = outputStr.replace(";", "").trim().split(": ");
            ParsingVariableType type = this.defineType(parts[1]);
            String name = parts[0].trim();
            if (type == null) {
                moduleDeclarations.put(name, new TextDeclaration(name, parts[1]));
            } else {
                ParsingVariableInfo output = new ParsingVariableInfo(name, type);
                outputVars.add(output);
                this.typeConnectedVars.put(output.getName(), new TypePropagationVariableNode(output));
            }
        }

        Map<String, ParsingModuleType> modulesTypes = new HashMap<>();
        Map<String, ParsingModule> modules = new HashMap<>();
        for (TextDeclaration declaration : moduleDeclarations.values()) {
            String moduleName = declaration.getName();
            String moduleTypeName = this.readModuleTypeName(declaration.getValue());

            if (!modulesTypes.containsKey(moduleTypeName)) {
                List<String> contents = this.extractModule(moduleTypeName);
                ParsingModuleType moduleType = this.parseModule(moduleTypeName, contents);
                modulesTypes.put(moduleTypeName, moduleType);
            }
            modules.put(moduleName, new ParsingModule(moduleName, modulesTypes.get(moduleTypeName)));
        }


        Set<String> modulesTypesProcessed = new HashSet<>();
        for (ParsingModule module : modules.values()) {
            boolean isNew = modulesTypesProcessed.add(module.getTypeInfo().getTypeName());
            if (!isNew) {
                continue;
            }
            TextDeclaration declaration = moduleDeclarations.get(module.getName());
            List<String> moduleCallInputs = this.readInputs(declaration.getValue());
            for (int i = 0; i < moduleCallInputs.size(); i++) {
                String moduleCallInput = moduleCallInputs.get(i);
                String inputName = moduleCallInput.replace("!", "");
                TypePropagationNode varNode = this.typeConnectedVars.get(inputName);
                if (varNode == null) {
                    ParsingVariableType type = this.defineType(inputName);
                    if (type != null) {
                        varNode = new TypePropagationConstantNode(type);
                        this.typeConnectedVars.put(inputName, varNode);
                    } else {
                        String[] varNameParts = inputName.split(".");
                        ParsingModuleType moduleOwner = modules.get(varNameParts[0]).getTypeInfo();
                        // TODO may not be the root, rework later
                        varNode = this.typeConnectedVars.get(moduleOwner.getTypeName() + "." + varNameParts[1]);
                    }
                }

                varNode.connect(module.getTypeInfo().getInputs().get(i), module.getTypeInfo().getTypeName());
            }
        }

        List<ParsingVariableInfo> inputVars = this.readOutputs(moduleContents).stream().filter(str -> str.contains(":="))
                .map(str -> {
                    String[] parts = str.trim().replace(";", "").split(" := ");
                    ParsingVariableInfo in = new ParsingVariableInfo(parts[0], ParsingVariableType.UNKNOWN);

                    String[] ownerVarParts = parts[1].split("\\.");
                    String ownerType = modules.get(ownerVarParts[0]).getTypeInfo().getTypeName();
                    this.typeConnectedVars.get(ownerType + "." + ownerVarParts[1]).connect(in, "");
                    return in;
                })
                .collect(Collectors.toList());

        for (TypePropagationNode node : this.typeConnectedVars.values()) {
            node.propagateType();
        }

        ParsingModule rootModule = new ParsingModule("main", true, new ParsingModuleType("main", inputVars, outputVars, new ArrayList<>()));
        this.establishConnections(rootModule, modules, moduleDeclarations);
        return new ParsingDiagram(rootModule, modules);
    }

    private void establishConnections(ParsingModule rootModule, Map<String, ParsingModule> modules, Map<String, TextDeclaration> moduleDeclarations) {
        for (TextDeclaration declaration : moduleDeclarations.values()) {

            ParsingModule moduleTo = modules.get(declaration.getName());

            List<String> inputs = this.readInputs(declaration.getValue());
            for (int i = 0; i < inputs.size(); i++) {
                String moduleCallInput = inputs.get(i);
                String[] varNameParts = moduleCallInput.split("\\.");

                boolean isInverted = varNameParts[0].startsWith("!");
                if (varNameParts.length == 1) {
                    String varName = varNameParts[0].replace("!", "");
                    ParsingOutput output = rootModule.getOutputsMap().get(varName);
                    if (output == null) {
                        output = this.parseConstant(varName);
                        rootModule.addOutput(output);
                    }
                    ParsingInput toVar = moduleTo.getInputs().get(i);
                    toVar.connect(output, rootModule, moduleTo, isInverted);
                    output.connect(toVar, rootModule, moduleTo, isInverted);
                } else {
                    ParsingModule moduleFrom = modules.get(varNameParts[0]);
                    ParsingOutput outVar = moduleFrom.getOutputsMap().get(varNameParts[1]);
                    ParsingInput toVar = moduleTo.getInputs().get(i);
                    outVar.connect(toVar, moduleFrom, moduleTo, isInverted);
                    toVar.connect(outVar, moduleFrom, moduleTo, isInverted);
                }
            }
        }
    }

    private ParsingOutputConstant parseConstant(String varName) {
        if (this.isBoolean(varName)) {
            return new ParsingOutputConstant<Boolean>(new ParsingVariableInfo(varName, ParsingVariableType.BOOLEAN), varName.toLowerCase().equals("true"));
        }
        if (this.isInteger(varName)) {
            return new ParsingOutputConstant<Integer>(new ParsingVariableInfo(varName, ParsingVariableType.INTEGER), Integer.valueOf(varName));
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

    private List<ParsingVariableInfo> parseInternals(List<String> internals) {
        return internals.stream().map(str -> {
            String[] parts = str.replace(";", "").trim().split(": ");
            ParsingVariableType type = this.isBoolean(parts[1]) ? ParsingVariableType.BOOLEAN :
                    (this.isInteger(parts[1]) ? ParsingVariableType.INTEGER : null);
            return new ParsingVariableInfo(parts[0], type);
        }).collect(Collectors.toList());
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
