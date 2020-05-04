package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.Clocks;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.List;
import java.util.stream.Collectors;

public abstract class FunctionBlockBase extends DiagramElement implements InterfaceUpdatedListener {
    private FBInterface fbInterface;

    private Clocks clocks;

    private BlockInterfaceHistory history;

    public FunctionBlockBase(String name, String type, List<InputVariable> inputs, List<OutputVariable> outputs) {
        super(name, type);

        List<InputGate> inGates = inputs.stream().map(in -> new InputGate(in, this)).collect(Collectors.toList());
        List<OutputGate> outGates = outputs.stream().map(out -> new OutputGate(out, this)).collect(Collectors.toList());

        this.fbInterface = new FBInterface(inGates, outGates);
        this.fbInterface.inputInterfaceUpdatedEvent().addListener(this);
        this.fbInterface.outputInterfaceUpdatedEvent().addListener(this);
        this.history = new BlockInterfaceHistory(this.fbInterface);
        this.clocks = new Clocks();
    }

    public void execute() {
//        System.out.println(this.getName() + " exec strated");
        this.executeImpl();
//        System.out.println(this.getName() + " exec completed");
    }

    public void tickSystemTime(){
        this.clocks.tick();
    }

    protected Clocks getClocks(){
        return this.clocks;
    }

    public int getSystemTime(){
        return this.clocks.currentTime();
    }

    public BlockInterfaceHistory history(){
        return this.history;
    }

    public abstract void executeImpl();

    @Override
    public void onInputInterfaceUpdated() {
        // System.out.println(this.getName() + ": interface updated");
        this.execute();
    }

    @Override
    public void onOutputInterfaceUpdated() {
        // System.out.println(this.getName() + ": interface updated");
        this.history.record(this.fbInterface, this.clocks.currentTime());
    }

    public FBInterface fbInterface() {
        return this.fbInterface;
    }

    public ExplanationItem explain(OutputGate output, int timestamp) {
       return this.explainImpl(output, timestamp);
    }

    protected abstract ExplanationItem explainImpl(OutputGate output, Integer timestamp);

    public boolean isRoot() {
        return this.getName().equals("root");
    }

    @Override
    public abstract FunctionBlockBase clone();
}
