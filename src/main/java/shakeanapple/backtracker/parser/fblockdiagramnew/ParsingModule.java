package shakeanapple.backtracker.parser.fblockdiagramnew;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParsingModule {
    private String moduleType;
    private boolean isRoot;
    private List<String> contents;
    private boolean isComplex;

    private List<ParsingInputVariable> inputs = new ArrayList<>();
    private List<ParsingOutputVariable> outputs = new ArrayList<>();
    private List<ParsingOutputVariable> internals = new ArrayList<>();

    private List<ModuleDeclaration> dependencies = new ArrayList<>();

    public ParsingModule(String moduleType, boolean isRoot, List<String> contents) {
        this.moduleType = moduleType;
        this.isRoot = isRoot;
        this.contents = contents;

        this.init();
    }

    private void init() {
        List<String> ins = ModuleParseUtils.readInputs(this.contents.get(0));
        this.inputs = IntStream.range(0, ins.size()).mapToObj(idx -> ParsingInputVariable.of(idx, ins.get(idx))).collect(Collectors.toList());

        List<String> allInternals = this.readInternals();
        int order = 0;
        for (String internalDeclaration : allInternals){
            ModuleDeclaration moduleDeclaration = ModuleDeclaration.of(internalDeclaration);
            if (moduleDeclaration != null){
                this.dependencies.add(moduleDeclaration);
            } else {
                if (this.isRoot){
                    this.inputs.add(ParsingInputVariable.of(order++, internalDeclaration));
                } else{
                    String[] parts = internalDeclaration.replace(";", "").trim().split(":");
                    this.internals.add(ParsingOutputVariable.of(parts[0], parts[1]));
                }
            }
        }

        if (this.dependencies.size() != 0){
            this.isComplex = true;
            this.outputs = this.readOutputsBlock().stream().map(out -> {
                String[] parts = out.trim().split(":=");
                return ParsingOutputVariable.of(parts[0], parts[1]);
            }).collect(Collectors.toList());
        }
    }


    private List<String> readInternals() {
        return this.contents.subList(this.contents.indexOf("VAR") + 1, this.contents.indexOf("DEFINE")).stream().map(str -> str.contains("--") ? str.substring(0, str.indexOf("--")) : str)
                .collect(Collectors.toList());
    }

    private List<String> readOutputsBlock() {
        return this.contents.subList(this.contents.indexOf("DEFINE") + 1, this.contents.indexOf("ASSIGN")).stream().map(str -> str.contains("--") ? str.substring(0, str.indexOf("--")) : str)
                .collect(Collectors.toList());
    }

    public String getModuleType() {
        return this.moduleType;
    }

    public boolean isRoot() {
        return this.isRoot;
    }

    public List<String> getContents() {
        return this.contents;
    }

    public boolean isComplex() {
        return this.isComplex;
    }

    public List<ParsingInputVariable> getInputs() {
        return this.inputs;
    }

    public List<ParsingOutputVariable> getOutputs() {
        return this.outputs;
    }

    public List<ParsingOutputVariable> getInternals() {
        return this.internals;
    }

    public List<ModuleDeclaration> getDependencies() {
        return this.dependencies;
    }
}
