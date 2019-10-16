package shakeanapple.backtracker.core.diagramexplanation.model.variable;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.Connection;
import shakeanapple.backtracker.core.diagramexplanation.model.DiagramElement;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;

public class InputVariable<TVal extends ValueHolder> extends FBVariable<TVal> {

    private int order;

    public InputVariable(DynamicVariable<TVal> variable, int order) {
        super(variable);
        this.order = order;
    }

    private InputVariable(OutputVariable output){
        super(output);
        this.order = 0;
    }

    public int getOrder() {
        return this.order;
    }

    public static InputVariable createSharedWithOutput(OutputVariable output){
        return new InputVariable(output);
    }
}
