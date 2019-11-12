package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.Cause;
import shakeanapple.backtracker.core.diagramexplanation.Clocks;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FunctionBlockBase extends DiagramElement implements InterfaceUpdatedListener {
    private FBInterface fbInterface;

    private BlockInterfaceHistory history;

    public FunctionBlockBase(String name, String type, List<InputVariable> inputs, List<OutputVariable> outputs) {
        super(name, type);

        List<InputGate> inGates = inputs.stream().map(in -> new InputGate(in, this)).collect(Collectors.toList());
        List<OutputGate> outGates = outputs.stream().map(out -> new OutputGate(out, this)).collect(Collectors.toList());

        this.fbInterface = new FBInterface(inGates, outGates);
        this.fbInterface.interfaceUpdatedEvent().addListener(this);
        this.history = new BlockInterfaceHistory(this.fbInterface);
    }

    public void execute() {
        this.executeImpl();
        this.history.record(this.fbInterface, Clocks.instance().currentTime());
    }

    protected BlockInterfaceHistory history(){
        return this.history;
    }

    public abstract void executeImpl();

    @Override
    public void onInterfaceUpdated() {
        System.out.println(this.getName() + ": interface updated");
        this.execute();
    }

    public FBInterface fbInterface() {
        return this.fbInterface;
    }

    public List<Cause> explain(OutputGate output, int timestamp) {
       return this.explainImpl(output, timestamp);
    }

    protected abstract List<Cause> explainImpl(OutputGate output, Integer timestamp);
}
