package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;

import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;

public abstract class BinOpFunctionBlockBasic extends FunctionBlockBasic {
    private final InputVariable left;
    private final InputVariable right;

    private final OutputVariable output;

    protected BinOpFunctionBlockBasic(String name, InputVariable left, InputVariable right, OutputVariable output) {
        super(name, new ArrayList<>() {{
            add(left);
            add(right);
        }}, new ArrayList<>() {{
            add(output);
        }});
        this.output = output;
        this.right = right;
        this.left = left;
    }

    public InputVariable getLeft() {
        return this.left;
    }

    public InputVariable getRight() {
        return this.right;
    }

    public OutputVariable getOutput() {
        return this.output;
    }
}
