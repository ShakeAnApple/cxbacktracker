package shakeanapple.backtracker.core.fblockmapping.model.variable;

import shakeanapple.backtracker.common.variable.AbstractValueHolder;
import shakeanapple.backtracker.common.variable.Variable;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.core.fblockmapping.model.Connection;
import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlock;
import shakeanapple.backtracker.parser.fblockdiagram.model.ParsingConnection;
import shakeanapple.backtracker.parser.fblockdiagram.model.ParsingInput;
import shakeanapple.backtracker.parser.fblockdiagram.model.ParsingModule;

import java.util.List;

public class OutputVariable<TVal extends AbstractValueHolder> extends FBVariable<TVal> {

    private List<Connection<TVal>> connectedTo;

    protected OutputVariable(DynamicVariable<TVal> variable) {
        super(variable);
    }

    public void assignValue(TVal value){
        super.setValue(value);
        for (Connection connection: connectedTo) {
            connection.toVar().setValue(value);
        }
    }

    public void connect(InputVariable<TVal> toVar, FunctionBlock from, FunctionBlock to, boolean isInverted){
        this.connectedTo.add(new Connection<TVal>(isInverted, from, this, to, toVar));
    }

}
