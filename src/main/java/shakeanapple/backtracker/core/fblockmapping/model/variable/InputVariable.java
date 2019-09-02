package shakeanapple.backtracker.core.fblockmapping.model.variable;

import shakeanapple.backtracker.common.variable.AbstractValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.core.fblockmapping.model.Connection;
import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlock;

public class InputVariable<TVal extends AbstractValueHolder> extends FBVariable<TVal> {

    private Connection<TVal> incommingConnection;

    public InputVariable(DynamicVariable<TVal> variable) {
        super(variable);
    }

    public void connect(OutputVariable<TVal> fromVar, FunctionBlock from, FunctionBlock to, boolean isInverted){
        this.incommingConnection = new Connection<TVal>(isInverted, from, fromVar, to, this);
    }

}
