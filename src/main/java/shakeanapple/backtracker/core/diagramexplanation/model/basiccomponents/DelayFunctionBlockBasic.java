package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.Clocks;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO introduce time into system?
public class DelayFunctionBlockBasic extends FunctionBlockBasic {
    private final InputVariable input;
    private final InputVariable defValue;

    private final OutputVariable output;

    private final int delay;

    private int ticksPassed;

    private List<ValueHolder> inputsSeq = new ArrayList<>();

    public DelayFunctionBlockBasic(InputVariable input, OutputVariable output, int delay) {
        super("Delay", new ArrayList<>() {{
            add(input);
        }}, new ArrayList<>() {{
            add(output);
        }});

        this.input = input;
        this.output = output;
        this.delay = delay;
        this.defValue = null;
        this.ticksPassed = 0;
    }

    public InputVariable getInput() {
        return this.input;
    }

    public DelayFunctionBlockBasic(InputVariable input, InputVariable defValue, OutputVariable output, int delay) {
        super("Delay", new ArrayList<>() {{
            add(input);
            add(defValue);
        }}, new ArrayList<>() {{
            add(output);
        }});

        this.input = input;
        this.output = output;
        this.delay = delay;
        this.defValue = defValue;
        this.ticksPassed = 0;
    }

    private boolean propagateValue = false;

    // TODO ask Igor (what? oO)
    @Override
    public void executeImpl() {
        if (this.propagateValue){
            this.propagateValue = false;
            super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(this.inputsSeq.get(Clocks.instance().currentTime() - this.delay));
            this.ticksPassed = 0;
            return;
        }
        this.inputsSeq.add(this.input.getValue());
        if (Clocks.instance().currentTime() < this.inputsSeq.size()){
            this.propagateValue = true;
            return;
        }
        if (Clocks.instance().currentTime() <= this.delay) {
            ValueHolder defValHolder = this.defValue != null ? this.defValue.getValue() : this.output.getDefaultValue();
            super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(defValHolder);
            this.ticksPassed++;
        }
        if (Clocks.instance().currentTime() > this.delay){
            super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(this.inputsSeq.get(Clocks.instance().currentTime() - this.delay));
            this.ticksPassed = 0;
        }
    }

    @Override
    protected List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp) {
        if (timestamp - this.delay >= 1){
            return Collections.singletonList(new CauseNode(super.fbInterface().getInputs().get(this.input.getName()), super.history().getVariableValueForStep(this.input.getName(), timestamp - this.delay), timestamp - this.delay));
        }
        // TODO start from zero? or 1? I always forget
        return Collections.singletonList(new CauseNode(super.fbInterface().getInputs().get(this.input.getName()), super.history().getVariableValueForStep(this.input.getName(), 1), 1));
    }
}
