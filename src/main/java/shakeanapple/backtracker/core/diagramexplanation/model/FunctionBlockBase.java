package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.Clocks;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.changecausetree.ChangeExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.List;
import java.util.stream.Collectors;

public abstract class FunctionBlockBase extends DiagramElement implements InterfaceUpdatedListener {
    private FBInterface fbInterface;

    private Clocks clocks;

    private BlockInterfaceHistory history;

    private String stringPathInSystem;

    public FunctionBlockBase(String name, String type, List<InputVariable> inputs, List<OutputVariable> outputs, String stringPathInSystem) {
        super(name, type);

        List<InputGate> inGates = inputs.stream().map(in -> new InputGate(in, this)).collect(Collectors.toList());
        List<OutputGate> outGates = outputs.stream().map(out -> new OutputGate(out, this)).collect(Collectors.toList());

        this.stringPathInSystem = stringPathInSystem;

        this.fbInterface = new FBInterface(inGates, outGates);
        this.fbInterface.inputInterfaceUpdatedEvent().addListener(this);
        this.fbInterface.outputInterfaceUpdatedEvent().addListener(this);
        this.history = new BlockInterfaceHistory(this.fbInterface);
        this.clocks = new Clocks();
    }

    public String getStringPathInSystem() {
        return this.stringPathInSystem;
    }

    public void execute() {
        this.executeImpl();
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
        this.execute();
    }

    @Override
    public void onOutputInterfaceUpdated() {
        this.history.record(this.fbInterface, this.clocks.currentTime());
    }

    public FBInterface fbInterface() {
        return this.fbInterface;
    }

    // FIXME explanation should be implemented as visitor!
    public ExplanationItem explain(OutputGate output, int timestamp) {
       return this.explainImpl(output, timestamp);
    }
    protected abstract ExplanationItem explainImpl(OutputGate output, Integer timestamp);

    // FIXME explanation should be implemented as visitor!
    public ChangeExplanationItem explainChange(OutputGate output, int timestamp) {
        return this.explainChangeImpl(output, timestamp);
    }
    protected abstract ChangeExplanationItem explainChangeImpl(OutputGate output, Integer timestamp);

    // FIXME explanation should be implemented as visitor!
    public ChangeExplanationItem explainHistoryChange(OutputGate output, int timestamp) {
        return this.explainHistoryChangeImpl(output, timestamp);
    }
    protected abstract ChangeExplanationItem explainHistoryChangeImpl(OutputGate output, Integer timestamp);


    public boolean isRoot() {
        return this.getName().equals("main");
    }

    @Override
    public abstract FunctionBlockBase clone();
}
