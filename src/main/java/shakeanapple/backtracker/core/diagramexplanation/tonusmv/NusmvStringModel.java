package shakeanapple.backtracker.core.diagramexplanation.tonusmv;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NusmvStringModel {
    private String moduleMain;
    private Set<String> internalModulesTypes;
    private String contents;
    private List<String> specifications;

    public NusmvStringModel(String moduleMain, String contents, Set<String> internalModulesTypes) {
        this.contents = contents;
        this.moduleMain = moduleMain;
        this.specifications = new ArrayList<>();
        this.internalModulesTypes = internalModulesTypes;
    }

    public Set<String> getInternalModulesTypes() {
        return this.internalModulesTypes;
    }

    public String getString(){
        return this.moduleMain.concat(System.lineSeparator())
                .concat(String.join(System.lineSeparator(), this.specifications))
                .concat(this.contents);
    }

    public void addSpecifications(List<String> specs){
        this.specifications.addAll(specs);
    }

    public List<String> getSpecifications(){
        return this.specifications;
    }
}
