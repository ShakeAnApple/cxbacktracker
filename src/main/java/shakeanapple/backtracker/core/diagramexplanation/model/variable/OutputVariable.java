package shakeanapple.backtracker.core.diagramexplanation.model.variable;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.Connection;
import shakeanapple.backtracker.core.diagramexplanation.model.DiagramElement;

import java.util.ArrayList;
import java.util.List;

public class OutputVariable<TVal extends ValueHolder> extends FBVariable<TVal> {


    private ValueHolder defaultValue;

    public OutputVariable(long id, DynamicVariable<TVal> variable) {
        super(variable, id);
    }

    private OutputVariable(long id, InputVariable input){
        super(input, id);
    }

    public OutputVariable(long id, DynamicVariable<TVal> variable, ValueHolder defaultValue) {
        this(id, variable);
        this.defaultValue = defaultValue;
    }

    public ValueHolder getDefaultValue() {
        return this.defaultValue;
    }

    public static OutputVariable createSharedWithInput(InputVariable input){
        return new OutputVariable(input.getId(), input);
    }

}
