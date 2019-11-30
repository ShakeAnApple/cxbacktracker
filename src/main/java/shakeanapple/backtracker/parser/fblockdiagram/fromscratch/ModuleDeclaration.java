package shakeanapple.backtracker.parser.fblockdiagram.fromscratch;

import java.util.List;

public class ModuleDeclaration {
    private String name;
    private String typeName;
    private List<String> inputs;

    public ModuleDeclaration(String name, String typeName, List<String> inputs) {
        this.name = name;
        this.typeName = typeName;
        this.inputs = inputs;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return typeName;
    }

    public List<String> getInputs() {
        return this.inputs;
    }
}
