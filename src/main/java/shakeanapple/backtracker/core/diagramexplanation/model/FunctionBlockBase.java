package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.Cause;
import shakeanapple.backtracker.core.diagramexplanation.Clocks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FunctionBlockBase extends DiagramElement implements InterfaceUpdatedListener {
    private FBInterface fbInterface;

    private BlockInterfaceHistory history;

    public FunctionBlockBase(String name, String type, FBInterface fbInterface) {
        super(name, type);

        this.fbInterface = fbInterface;
        this.fbInterface.interfaceUpdatedEvent().addListener(this);
        this.history = new BlockInterfaceHistory(this.fbInterface);
    }

    public void execute() {
        this.executeImpl();
        this.history.record(this.fbInterface, Clocks.instance().currentTime());
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

    public List<Cause> explain(OutputGate output, Integer timestamp) {
        List<Cause> allCauses = new ArrayList<>();
        List<Cause> causesForTimestamp = this.explainImpl(output, timestamp);

        allCauses.addAll(
                causesForTimestamp.stream()
                        .filter(c -> this.fbInterface.getInputs().containsKey(c.getVarName()))
                        .collect(Collectors.toList())
        );

        timestamp --;
        if (timestamp < 0){
            return allCauses;
        }

        for (Cause cause : causesForTimestamp.stream()
                .filter(c -> this.fbInterface().getOutputs().containsKey(c.getVarName()))
                .collect(Collectors.toList())) {
            List<Cause> causes = this.explain(this.fbInterface.getOutputs().get(cause.getVarName()), cause.getTimestamp() - 1);
            allCauses.addAll(causes);
        }
        return allCauses;
    }

    protected abstract List<Cause> explainImpl(OutputGate output, Integer timestamp);
}
