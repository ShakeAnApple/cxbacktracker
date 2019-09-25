package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.AbstractValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.List;

public abstract class Component<TIn extends AbstractValueHolder, TOut extends AbstractValueHolder> {
    private final List<InputVariable<TIn>> inputs;
    private final List<OutputVariable<TOut>> outputs;

    protected Component(List<InputVariable<TIn>> inputs, List<OutputVariable<TOut>> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public abstract void execute();

    public List<InputVariable<TIn>> getInputs(){
        return this.inputs;
    }

    public List<OutputVariable<TOut>> getOutputs(){
        return this.outputs;
    }
}

// BooleanComponent

// ArithmeticComponent

// Delay

