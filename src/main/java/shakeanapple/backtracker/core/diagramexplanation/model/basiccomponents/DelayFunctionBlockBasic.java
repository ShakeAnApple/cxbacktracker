package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.BlockVariableHistoryItem;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.logic.AndFunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;
import shakeanapple.backtracker.core.diagramexplanation.Clocks;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.changecausetree.ChangeCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.*;
import java.util.stream.Collectors;

//TODO introduce time into system?
public class DelayFunctionBlockBasic extends FunctionBlockBasic {
    private final InputVariable input;
    private final InputVariable defValue;

    private final OutputVariable output;

    private final int delay;

    private int ticksPassed;

    private List<ValueHolder> inputsSeq = new ArrayList<>();

    public DelayFunctionBlockBasic(boolean generateId, InputVariable input, OutputVariable output, int delay, String pathInSystem) {
        super("Delay" + (generateId ? BasicBlocksIdGenerator.next("Delay") : ""), new ArrayList<>() {{
            add(input);
        }}, new ArrayList<>() {{
            add(output);
        }}, pathInSystem);

        this.input = input;
        this.output = output;
        this.delay = delay;
        this.defValue = null;
        this.ticksPassed = 0;
    }

    private DelayFunctionBlockBasic(String name, InputVariable input, OutputVariable output, int delay, String pathInSystem) {
        super(name, new ArrayList<>() {{
            add(input);
        }}, new ArrayList<>() {{
            add(output);
        }}, pathInSystem);

        this.input = input;
        this.output = output;
        this.delay = delay;
        this.defValue = null;
        this.ticksPassed = 0;
    }

    public InputVariable getInput() {
        return this.input;
    }

    @Override
    public FunctionBlockBase clone() {
        if (this.defValue != null) {
            return new DelayFunctionBlockBasic(this.getName(), this.input.clone(), this.defValue.clone(), this.output.clone(), this.delay, this.getStringPathInSystem());
        }
        return new DelayFunctionBlockBasic(this.getName(), this.input.clone(), this.output.clone(), this.delay, this.getStringPathInSystem());
    }

    public DelayFunctionBlockBasic(boolean generateId, InputVariable input, InputVariable defValue, OutputVariable output, int delay, String pathInSystem) {
        super("Delay" + (generateId ? BasicBlocksIdGenerator.next("Delay") : ""), new ArrayList<>() {{
            add(input);
            add(defValue);
        }}, new ArrayList<>() {{
            add(output);
        }}, pathInSystem);

        this.input = input;
        this.output = output;
        this.delay = delay;
        this.defValue = defValue;
        this.ticksPassed = 0;
    }

    private DelayFunctionBlockBasic(String name, InputVariable input, InputVariable defValue, OutputVariable output, int delay, String pathInSystem) {
        super(name, new ArrayList<>() {{
            add(input);
            add(defValue);
        }}, new ArrayList<>() {{
            add(output);
        }}, pathInSystem);

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
        if (this.propagateValue) {
            this.propagateValue = false;
            super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(this.inputsSeq.get(super.getSystemTime() - this.delay));
            this.ticksPassed = 0;
            return;
        }
        this.inputsSeq.add(this.input.getValue());
        if (super.getSystemTime() < this.inputsSeq.size()) {
            this.propagateValue = true;
            return;
        }
        if (super.getSystemTime() <= this.delay) {
            ValueHolder defValHolder = this.defValue != null ? this.defValue.getValue() : this.output.getDefaultValue();
            super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(defValHolder);
            this.ticksPassed++;
        }
        if (super.getSystemTime() > this.delay) {
            super.fbInterface().getOutputs().values().stream().findFirst().get().assignValue(this.inputsSeq.get(super.getSystemTime() - this.delay));
            this.ticksPassed = 0;
        }
    }

    @Override
    protected List<CauseNode> explainBasicImpl(OutputGate output, Integer timestamp) {
        if (timestamp - this.delay >= 1) {
            return Collections.singletonList(new CauseNode(super.fbInterface().getInputs().get(this.input.getName()), super.history().getVariableValueForStep(this.input.getName(), timestamp - this.delay), timestamp - this.delay));
        }
        // TODO start from zero? or 1? I always forget: YES
        return Collections.singletonList(new CauseNode(super.fbInterface().getInputs().get(this.input.getName()), super.history().getVariableValueForStep(this.input.getName(), 1), 1));
    }

    @Override
    protected List<ChangeCauseNode> explainChangeBasicImpl(OutputGate output, Integer changeStep) {
        if (changeStep - this.delay >= 1) {
            return Collections.singletonList(new ChangeCauseNode(super.fbInterface().getInputs().get(this.input.getName()),
                    super.history().getVariableValueForStep(this.input.getName(), changeStep - this.delay + 1), changeStep - this.delay + 1,
                    super.history().getVariableValueForStep(this.input.getName(), changeStep - this.delay), changeStep - delay));
        }

        return Collections.singletonList(new ChangeCauseNode(super.fbInterface().getInputs().get(this.defValue.getName()), super.history().getVariableValueForStep(this.input.getName(), 1), 1,
                super.history().getVariableValueForStep(this.input.getName(), 1), 1));

    }
}
