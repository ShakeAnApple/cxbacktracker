package shakeanapple.backtracker.core.fblockmapping.model.variable;

import shakeanapple.backtracker.common.variable.AbstractValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.core.fblockmapping.model.Connection;
import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlock;

import java.util.ArrayList;
import java.util.List;

public class OutputVariable<TVal extends AbstractValueHolder> extends FBVariable<TVal> {

    private List<Connection<TVal>> outcomingConnections;

    public OutputVariable(DynamicVariable<TVal> variable) {
        super(variable);
        this.outcomingConnections = new ArrayList<>();
    }

    public void assignValue(TVal value){
        super.setValue(value);
        for (Connection connection: outcomingConnections) {
            if (connection.isInverted()){
                value.invert();
            }
            connection.toVar().setValue(value);
        }
    }

    public List<Connection<TVal>> getOutcomingConnections() {
        return this.outcomingConnections;
    }

    public void connect(InputVariable<TVal> toVar, FunctionBlock from, FunctionBlock to, boolean isInverted){
        this.outcomingConnections.add(new Connection<TVal>(isInverted, from, this, to, toVar));
    }

}
