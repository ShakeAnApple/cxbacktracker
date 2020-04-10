package shakeanapple.backtracker.parser.fblockdiagramnew;

import java.util.List;
import java.util.stream.Collectors;

public class ModuleDeclaration {
    private String name;
    private String typeName;
    private List<Assignment> inputs;

    public ModuleDeclaration(String name, String typeName, List<Assignment> inputs) {
        this.name = name;
        this.typeName = typeName;
        this.inputs = inputs;
    }

    public static ModuleDeclaration of(String moduleDeclaration) {
        if (!moduleDeclaration.contains("(")) {
            return null;
        }

        String[] parts = moduleDeclaration.split(":");
        return new ModuleDeclaration(parts[0].trim(), readModuleTypeName(parts[1]).trim(),
                ModuleParseUtils.readInputs(parts[1]).stream().map(Assignment::of).collect(Collectors.toList()));
    }

    private static String readModuleTypeName(String moduleSpec) {
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

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return typeName;
    }

    public List<Assignment> getInputs() {
        return this.inputs;
    }
}
