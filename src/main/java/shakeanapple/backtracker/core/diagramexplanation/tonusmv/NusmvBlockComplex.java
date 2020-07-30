package shakeanapple.backtracker.core.diagramexplanation.tonusmv;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class  NusmvBlockComplex implements NusmvBlock{
    private String name;
    private String type;
    private NusmvStringModel stringModel;
    private List<String> inputsFromSystem;

    public NusmvBlockComplex(String name, String type, List<String> inputsFromSystem, NusmvStringModel stringModel) {
        this.name = name;
        this.type = type;
        this.stringModel = stringModel;
        this.inputsFromSystem = inputsFromSystem;
    }

    public NusmvBlockComplex(String name, String type, List<String> inputsFromSystem) {
        this.name = name;
        this.type = type;
        this.stringModel = new NusmvStringModel("", "", new HashSet<>());
        this.inputsFromSystem = inputsFromSystem;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public NusmvStringModel getStringModel() {
        return this.stringModel;
    }

    @Override
    public void writeTo(NusmvStringModelBuilder mb) {
        mb.addModuleType(this.type);
        for (String type: this.stringModel.getInternalModulesTypes()){
            mb.addModuleType(type);
        }
        mb.appendVarStatement(this.name + ": " + this.type + "(" + String.join(",", this.inputsFromSystem) + ");")
                .appendModule(this.stringModel.getString());

    }
}
