package shakeanapple.backtracker.parser.fblockdiagram;

import shakeanapple.backtracker.core.fblockmapping.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    private final String path;

    private final List<String> contents;

    public Parser(String path) throws IOException {
        this.path = path;

        this.contents = Files.readAllLines(Path.of(path));
    }

    public FunctionBlockDiagram parse() {
        List<TextVariable> vars = new ArrayList<>();

        Map<String, TextModuleDescription> modulesDescriptions = new HashMap<>();
        List<Integer> moduleStarts = this.contents.stream()
                .filter(line -> line.contains("MODULE"))
                .map(this.contents::indexOf)
                .sorted()
                .collect(Collectors.toList());

        int from = moduleStarts.get(0);
        for (int i = 1; i < moduleStarts.size(); i++) {
            int to = i;
            ModuleBuilder mb = new ModuleBuilder(this.contents.subList(from, to));
            TextModuleDescription m = mb.build();
            modulesDescriptions.put(m.getName(), m);
            from = to;
        }

        this.inferInputTypes(modulesDescriptions);
        Map<String, TextModule> modules = this.inferModules(modulesDescriptions);
        List<TextConnection> connections = this.inferConnections(modules);

        return this.translate(new ArrayList<>(modules.values()), connections);
    }

    private FunctionBlockDiagram translate(List<TextModule> modules, List<TextConnection> connections) {
        Map<String,FunctionBlock> blocks = new HashMap<>();
        for (TextModule module: modules) {
            FunctionBlock block = module.translate();
            blocks.put(block.getName(), block);
        }

        List<Connection> blockConnections = new ArrayList<>();
        for (TextConnection conn: connections) {
            FunctionBlock from = blocks.get(conn.from().getName());
            FunctionBlock to = blocks.get(conn.to().getName());

            FunctionBlockVariableInfo fromVar = from.getInfo().getOutputs().stream().filter(var -> var.getName().equals(conn.fromVar().getName())).findFirst().get();
            FunctionBlockVariableInfo toVar = to.getInfo().getInputs().stream().filter(var -> var.getName().equals(conn.toVar().getName())).findFirst().get();

            Connection c = new Connection(from, fromVar, to, toVar);
            blockConnections.add(c);
        }

        return new FunctionBlockDiagram(new ArrayList<>(blocks.values()), blockConnections);
    }

    private Map<String, TextModule> inferModules(Map<String, TextModuleDescription> modulesDescriptions) {
        TextModuleDescription mainDescription = modulesDescriptions.get("main");
        TextModule main = new TextModule("main", mainDescription);

        List<TextVariable> moduleVars = mainDescription.getInternals().stream()
                .filter(var -> var.getType().getTypeSpec() == VarType.MODULE)
                .collect(Collectors.toList());

        Map<String, TextModule> modules = new HashMap<>();
        modules.put(main.getName(), main);

        for (TextVariable var : moduleVars) {
            String moduleTypeName = this.readModuleTypeName(var.getType().getClue().getStringClue());
            TextModule module = new TextModule(var.getName(), modulesDescriptions.get(moduleTypeName));
            modules.put(module.getName(), module);
        }
        return modules;
    }

    private List<TextConnection> inferConnections(Map<String, TextModule> modules) {
        // Reset_priority_flip_flop : FLIP_FLOP(Set_priority_flip_flop.FF_OUTPUT_SIGN, Set_priority_flip_flop.FF_OUTPUT_SIGN_FAULT, TRUE, criteria, criteria_FAULT, TRUE, FALSE, TRUE);
        TextModule main = modules.get("main");

        List<TextVariable> moduleVars = main.getDescription().getInternals().stream()
                .filter(var -> var.getType().getTypeSpec() == VarType.MODULE)
                .collect(Collectors.toList());

        List<TextConnection> connections = new ArrayList<>();
        for (TextVariable var : moduleVars) {
            List<String> inputs = this.readInputsString(var.getType().getClue().getStringClue());
            TextModule curModule = modules.get(var.getName());
            for (int i = 0; i < inputs.size(); i++) {
                String input = inputs.get(i);
                TextVariable toVar = curModule.getDescription().getInputs().get(i);
                TextConnection conn = null;
                String[] parts = input.split(".");
                if (parts.length == 1) {
                    TextVariable fromVar = main.getDescription().getInternals().stream().filter(v -> v.getName().equals(input.replace("!", ""))).findFirst().get();
                    conn = new TextConnection(main, fromVar, curModule, toVar);
                } else if (parts.length == 2) {
                    TextModule from = modules.get(parts[0]);
                    TextVariable fromVar = from.getDescription().getInternals().stream().filter(v -> v.getName().equals(input.replace("!", ""))).findFirst().get();
                    conn = new TextConnection(from, fromVar, curModule, toVar);
                }
                connections.add(conn);
            }
        }

        return connections;
    }


    // TODO needs rework
    private void inferInputTypes(Map<String, TextModuleDescription> modules) {
        TextModuleDescription main = modules.get("main");

        List<String> modulesToInputsInferString = main.getInternals().stream()
                .filter(var -> var.getType().getTypeSpec() == VarType.MODULE)
                .map(var -> var.getType().getClue().getStringClue())
                .collect(Collectors.toList());

        List<TextVariable> internalsTypeDefined = main.getInternals().stream()
                .filter(var -> var.getType().getTypeSpec() != VarType.MODULE)
                .collect(Collectors.toList());

        Set<String> modulesProcessed = new HashSet<>();
        for (String moduleSpec : modulesToInputsInferString) {
            String name = readModuleTypeName(moduleSpec.trim());
            if (modulesProcessed.contains(name)) {
                continue;
            }

            List<String> vars = readInputsString(moduleSpec.trim());
            TextModuleDescription module = modules.get(name);
            vars.forEach(var -> {
                boolean isInverted = var.startsWith("!");
                if (isInverted) {
                    var = var.substring(1);
                }
                String v = var;
                TextVariable moduleVar = module.getInputs().stream().filter(input -> input.getName().equals(v)).findFirst().get();
                TextVariable typedVar = internalsTypeDefined.stream().filter(input -> input.getName().equals(v)).findFirst().get();
                moduleVar.defineType(typedVar.getType().getTypeSpec());
                moduleVar.isInverted(isInverted);
            });
            modulesProcessed.add(moduleSpec);
        }
    }

    private List<String> readInputsString(String moduleSpec) {
        Pattern p = Pattern.compile("\\(([()])\\)");
        Matcher m = p.matcher(moduleSpec);
        String inputsString = "";
        if (m.find()) {
            inputsString = m.group(1);
        }
        if (inputsString == null || inputsString.isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.stream(inputsString.split(", "))
                .collect(Collectors.toList());
    }

    private String readModuleTypeName(String moduleSpec) {
        char next = ' ';
        int nextIdx = 0;
        StringBuilder name = new StringBuilder();
        while (next != '(') {
            name.append(next);
            next = moduleSpec.charAt(nextIdx);
            nextIdx++;
        }
        return name.toString();
    }

}
