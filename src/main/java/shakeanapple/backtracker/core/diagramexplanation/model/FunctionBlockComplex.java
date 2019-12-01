package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.Cause;
import shakeanapple.backtracker.core.diagramexplanation.Clocks;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.DelayFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FunctionBlockComplex extends FunctionBlockBase {

    private Diagram internalDiagram;

    public FunctionBlockComplex(String name, String type, List<InputVariable> inputs, List<OutputVariable> outputs, Diagram internalDiagram) {
        super(name, type, inputs, outputs);

        this.internalDiagram = internalDiagram;
        Clocks.instance().onTick(this::releaseDelays);
        Clocks.instance().onTick(this::releaseUnconnectedBlocks);
    }

    private void releaseUnconnectedBlocks() {
        for (FunctionBlockBase fb: this.internalDiagram.getFunctionBlocks()){
            if (fb.fbInterface().getInputs().values().stream().noneMatch(i -> i.getIncomingConnection() != null)){
                System.out.println(fb.getName() + ": no connected inputs, executed");
                fb.execute();
            }
        }
    }

    private void releaseDelays() {
        this.internalDiagram.getFunctionBlocks().stream().filter(fb -> fb instanceof DelayFunctionBlockBasic).forEach(fb -> {
            DelayFunctionBlockBasic delayFb = (DelayFunctionBlockBasic) fb;
            if (fb.fbInterface().getInputs().values().stream().anyMatch(i -> i.getIncomingConnection() != null) &&
                    !this.fbInterface().getInputs().containsKey(delayFb.fbInterface().getInputs().get(delayFb.getInput().getName()).getName())) {
                System.out.println(fb.getName() + ": delay, executed");
                fb.execute();
            }
        });
    }

    public Diagram getInternalDiagram() {
        return this.internalDiagram;
    }

    @Override
    public void executeImpl() {
        this.internalDiagram.execute();
    }

    @Override
    protected List<Cause> explainImpl(OutputGate output, Integer timestamp) {
        System.out.println(String.format("Upper Block: %s, Gate: %s, Timestamp: %s", this.getName(), output.getName(), timestamp));
        OutputGate gateToExplain = (OutputGate) output.getIncomingConnection().fromGate();
        List<Cause> causes = this.internalDiagram.explain(gateToExplain, timestamp);
        Set<Cause> outputCauses = new HashSet<>();
        for (Cause cause : causes) {
            if (this.fbInterface().getInputs().get(cause.getGate().getName()) != null &&
                    this.fbInterface().getInputs().get(cause.getGate().getName()).equals(cause.getGate())
            ) {
                System.out.println(String.format("InternalD: cause '%s' added to result", cause.getGate().getName()));
                outputCauses.add(new Cause(cause.getGate(), cause.getValue(), cause.getTimestamp()));
            } else if (cause.getGate().getIncomingConnection() != null) {
                System.out.println(String.format("InternalD: cause '%s' will be processed", cause.getGate().getName()));
                outputCauses.addAll(
                        this.internalDiagram.explain((OutputGate) cause.getGate(), cause.getTimestamp())
                );
                System.out.println(String.format("InternalD: cause '%s' processed", cause.getGate().getName()));
            }
        }
        System.out.println(String.format("Upper Block Processed: %s, Gate: %s, Timestamp: %s", this.getName(), output.getName(), timestamp));
        return new ArrayList<>(outputCauses);
    }

    public static FunctionBlockComplex parse(String path, String blockDefsPath) {
        try {
            // Parser p = new Parser(path, blockDefsPath);
            Parser p = new Parser(path);
            return p.parse();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
