package shakeanapple.backtracker.core.diagramexplanation.model;

public abstract class FunctionBlockBase extends DiagramElement implements InterfaceUpdatedListener {
    private FBInterface fbInterface;

    public FunctionBlockBase(String name, String type, FBInterface fbInterface){
        super(name, type);
        this.fbInterface = fbInterface;
        this.fbInterface.interfaceUpdatedEvent().addListener(this);
    }

    public abstract void execute();

    @Override
    public void onInterfaceUpdated() {
        System.out.println(this.getName() + ": interface updated");
        this.execute();
    }

    public FBInterface fbInterface() {
        return this.fbInterface;
    }
}
