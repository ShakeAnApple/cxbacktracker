package shakeanapple.backtracker.parser.fblockdiagramold.model;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.BooleanDynamicVariable;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.common.variable.dynamic.IntegerDynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.FBVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;

public class ParsingInput extends ParsingModuleVariable {

    private ParsingConnection incommingConnection;

    public ParsingInput(ParsingVariableInfo info) {
        super(info);
    }

    public void connect(ParsingOutput fromVar, ParsingModule from, ParsingModule to, boolean isInverted){
        this.incommingConnection = new ParsingConnection(from, fromVar, to, this, isInverted);
    }

    public ParsingConnection getIncommingConnection() {
        return this.incommingConnection;
    }

    @Override
    public FBVariable translate() {
        DynamicVariable var;
        switch (this.getInfo().getType()) {
            case BOOLEAN:
                var = new BooleanDynamicVariable(new BooleanValueHolder(false), this.getInfo().getName());
                break;
            case INTEGER:
                var = new IntegerDynamicVariable(new IntegerValueHolder(Integer.MIN_VALUE), this.getInfo().getName());
                break;
            default:
                throw new RuntimeException("Unsupported var type: " + this.getInfo().getType().name());
        }
        return new InputVariable(var, Integer.MIN_VALUE);
    }
}
