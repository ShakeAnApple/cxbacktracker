package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.common.variable.AbstractValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;

public abstract class BinOpComponent<TIn extends AbstractValueHolder, TOut extends AbstractValueHolder> extends Component<TIn, TOut> {
    private final InputVariable<TIn> left;
    private final InputVariable<TIn> right;

    private final OutputVariable<TOut> output;

    protected BinOpComponent(InputVariable<TIn> left, InputVariable<TIn> right, OutputVariable<TOut> output) {
        super(new ArrayList<>() {{
            add(left);
            add(right);
        }}, new ArrayList<>() {{
            add(output);
        }});
        this.output = output;
        this.right = right;
        this.left = left;
    }

    public InputVariable<TIn> getLeft() {
        return this.left;
    }

    public InputVariable<TIn> getRight() {
        return this.right;
    }

    public OutputVariable<TOut> getOutput() {
        return this.output;
    }
}
