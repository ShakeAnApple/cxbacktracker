package shakeanapple.backtracker.core.diagramexplanation.model;

public abstract class FunctionBlockBase extends DiagramElement {
    private FBInterface fbInterface;

    public FunctionBlockBase(String name, String type, FBInterface fbInterface){
        super(name, type);
        this.fbInterface = fbInterface;
    }

    public abstract void evaluate();

    public FBInterface fbInterface() {
        return this.fbInterface;
    }
}
