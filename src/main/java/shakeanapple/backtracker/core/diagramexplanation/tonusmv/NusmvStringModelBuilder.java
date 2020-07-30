package shakeanapple.backtracker.core.diagramexplanation.tonusmv;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NusmvStringModelBuilder {

    private StringBuilder assignSectionSB = new StringBuilder();
    private StringBuilder defineSectionTopSB = new StringBuilder();
    private StringBuilder defineSectionBottomSB = new StringBuilder();
    private StringBuilder moduleSectionSB = new StringBuilder();
    private StringBuilder varSectionSB = new StringBuilder();

    private List<String> inputs = new ArrayList<>();
    private Set<String> addedModuleTypes = new HashSet<>();

    private String blockType;
    private boolean isRoot;

    public NusmvStringModelBuilder(String blockType, boolean isRoot) {
        this.blockType = blockType;
        this.isRoot = isRoot;
        this.assignSectionSB.append("ASSIGN").append(System.lineSeparator());
        this.defineSectionTopSB.append("DEFINE").append(System.lineSeparator());
        this.varSectionSB.append("VAR").append(System.lineSeparator());
    }

    public void addInputs(List<String> inputs) {
        this.inputs = inputs;
    }

    public NusmvStringModel get() {
        String moduleMain = "MODULE " + this.blockType +
                "(" + (this.isRoot ? "" : String.join(",", this.inputs)) + ")" + System.lineSeparator() +
                this.varSectionSB.toString() + System.lineSeparator() +
                this.defineSectionTopSB.toString() + System.lineSeparator() +
                this.defineSectionBottomSB.toString() + System.lineSeparator() +
                this.assignSectionSB.toString() + System.lineSeparator();

        return new NusmvStringModel(moduleMain,this.moduleSectionSB.toString() + System.lineSeparator(), this.addedModuleTypes);
    }

    public NusmvStringModelBuilder appendAssignStatement(String st) {
        this.assignSectionSB.append(st).append(System.lineSeparator());
        return this;
    }

    public NusmvStringModelBuilder appendVarStatement(String st) {
        this.varSectionSB.append(st).append(System.lineSeparator());
        return this;
    }

    public NusmvStringModelBuilder appendDefineStatement(String st, boolean toTop) {
        if (toTop) this.defineSectionTopSB.append(st).append(System.lineSeparator());
        else this.defineSectionBottomSB.append(st).append(System.lineSeparator());
        return this;
    }

    public NusmvStringModelBuilder appendModule(String module) {
        this.moduleSectionSB.append(module).append(System.lineSeparator());
        return this;
    }

    public void addModuleType(String type) {
        this.addedModuleTypes.add(type);
    }

    public boolean containsModule(String moduleType) {
        return this.addedModuleTypes.contains(moduleType);
    }
}
