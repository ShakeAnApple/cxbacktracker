package shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition;

public class ConstantInputDefinition extends InputDefinition {
    private String value;

    public ConstantInputDefinition(long id, VarDefinitionType type, String name, String value, int order) {
        super(id, type, name, order);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
