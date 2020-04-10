package shakeanapple.backtracker.parser.fblockdiagramnew;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SystemLexer {
    private List<String> contents;
    private String rootName;

    public SystemLexer(List<String> contents, String rootName) {
        this.contents = contents;
        this.rootName = rootName;
    }

    public List<ParsingModule> extractModulesAndDependencies() {
        Map<String, ParsingModule> modulesByTypes = new HashMap<>();
        ParsingModule root = new ParsingModule(rootName, true, this.extractModuleString(rootName));
        modulesByTypes.put(rootName, root);
        this.extractModulesFromTo(root, modulesByTypes);
        return new ArrayList<>(modulesByTypes.values());
    }

    private void extractModulesFromTo(ParsingModule parsingModuleParent, Map<String, ParsingModule> modulesByTypes){
        for (ModuleDeclaration dependency: parsingModuleParent.getDependencies()) {
            if (!modulesByTypes.containsKey(dependency.getTypeName())) {
                ParsingModule module = new ParsingModule(dependency.getTypeName(), false, this.extractModuleString(dependency.getTypeName()));
                modulesByTypes.put(dependency.getTypeName(), module);
                if (module.isComplex()) {
                    this.extractModulesFromTo(module, modulesByTypes);
                }
            }
        }
    }

    private List<String> extractModuleString(String moduleType){
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
}
