package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.Cause;
import shakeanapple.backtracker.core.diagramexplanation.Clocks;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.DelayFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.*;
import java.util.stream.Collectors;

public class Diagram {
    private List<FunctionBlockBase> functionBlocks;

    private Map<Long, InputVariable> inputs;
    private Map<Long, OutputVariable> outputs;

    public Diagram(List<FunctionBlockBase> functionBlocks, List<InputVariable> inputs, List<OutputVariable> outputs) {
        this.functionBlocks = functionBlocks;
        this.inputs = inputs.stream().collect(Collectors.toMap(InputVariable::getId, in -> in));
        this.outputs = outputs.stream().collect(Collectors.toMap(OutputVariable::getId, out -> out));;
    }

    public List<FunctionBlockBase> getFunctionBlocks() {
        return this.functionBlocks;
    }

    public Map<Long, InputVariable> getInputs() {
        return this.inputs;
    }

    public Map<Long, OutputVariable> getOutputs() {
        return this.outputs;
    }

    public void execute() {
        for (FunctionBlockBase fb: this.functionBlocks){
            if (fb.fbInterface().getInputs().values().stream().noneMatch(i -> i.getIncomingConnection() != null)){
                System.out.println(fb.getName() + ": no connected inputs, executed");
                fb.execute();
            } else if ((fb instanceof DelayFunctionBlockBasic) && Clocks.instance().currentTime() == 1){
                System.out.println(fb.getName() + ": delay, executed");
                fb.execute();
            }
        }
    }


    // TODO lazy recursion
    public List<Cause> explain(OutputGate gateToExplain, Integer timestamp) {
        List<Cause> causes = gateToExplain.getOwner().explain(gateToExplain, timestamp);
        Set<Cause> outputCauses = new HashSet<>();
        for (Cause cause: causes) {
            if (this.inputs.containsKey(cause.getGate().output().getId())) {
                System.out.println(String.format("InternalD: cause '%s' added to result", cause.getGate().getIncomingConnection().fromGate().getName()));
                outputCauses.add(new Cause(cause.getGate().getIncomingConnection().fromGate(), cause.getValue(), cause.getTimestamp()));
            } else if (cause.getGate().getIncomingConnection() != null){
                System.out.println(String.format("InternalD: cause '%s' will be processed", cause.getGate().getName()));
                outputCauses.addAll(
                        this.explain((OutputGate) cause.getGate().getIncomingConnection().fromGate(), cause.getTimestamp())
                );
                System.out.println(String.format("InternalD: cause '%s' processed", cause.getGate().getName()));
            }
        }
        return new ArrayList<>(outputCauses);
    }
}
