package shakeanapple.backtracker.core.fblockmapping.model.variable;

import shakeanapple.backtracker.common.variable.AbstractValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.core.fblockmapping.model.Connection;
import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlock;
import shakeanapple.backtracker.parser.fblockdiagram.model.ParsingConnection;
import shakeanapple.backtracker.parser.fblockdiagram.model.ParsingModule;
import shakeanapple.backtracker.parser.fblockdiagram.model.ParsingOutput;

public class InputVariable<TVal extends AbstractValueHolder> extends FBVariable<TVal> {

    private Connection<TVal> connectedFrom;

    protected InputVariable(DynamicVariable<TVal> variable) {
        super(variable);
    }

    public void connect(OutputVariable<TVal> fromVar, FunctionBlock from, FunctionBlock to, boolean isInverted){
        this.connectedFrom = new Connection<TVal>(isInverted, from, fromVar, to, this);
    }

}
