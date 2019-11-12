package shakeanapple.backtracker.core.diagramexplanation.model.variable;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.Connection;
import shakeanapple.backtracker.core.diagramexplanation.model.DiagramElement;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;

public class InputVariable<TVal extends ValueHolder> extends FBVariable<TVal> {

    private int order;

    public InputVariable(long id, DynamicVariable<TVal> variable, int order) {
        super(variable, id);
        this.order = order;
    }

    private InputVariable(long id, OutputVariable output){
        super(output, id);
        this.order = 0;
    }

    public int getOrder() {
        return this.order;
    }

    public static InputVariable createSharedWithOutput(OutputVariable output, long id){
        return new InputVariable(id, output);
    }
}
