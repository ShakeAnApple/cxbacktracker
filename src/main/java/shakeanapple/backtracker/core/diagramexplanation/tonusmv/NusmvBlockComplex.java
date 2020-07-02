package shakeanapple.backtracker.core.diagramexplanation.tonusmv;

import java.util.List;

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

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public NusmvStringModel getStringModel() {
        return this.stringModel;
    }

    @Override
    public void writeTo(NusmvStringModelBuilder mb) {
        mb.appendVarStatement(this.name + ":" + this.type + "(" + String.join(",", this.inputsFromSystem) + ")")
                .appendModule(this.stringModel.getContents());

    }
}
