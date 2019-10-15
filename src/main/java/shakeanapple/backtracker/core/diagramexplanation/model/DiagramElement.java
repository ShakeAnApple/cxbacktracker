package shakeanapple.backtracker.core.diagramexplanation.model;

public abstract class DiagramElement {
    private String name;
    private String type;

    public DiagramElement(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public abstract void evaluate();

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

}
