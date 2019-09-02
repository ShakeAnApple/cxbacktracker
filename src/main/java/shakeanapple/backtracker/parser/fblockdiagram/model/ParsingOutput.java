package shakeanapple.backtracker.parser.fblockdiagram.model;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.BooleanDynamicVariable;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.common.variable.dynamic.IntegerDynamicVariable;
import shakeanapple.backtracker.core.fblockmapping.model.variable.FBVariable;
import shakeanapple.backtracker.core.fblockmapping.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.List;

public class ParsingOutput extends ParsingModuleVariable {

    private List<ParsingConnection> outgoingConnections;

    public ParsingOutput(ParsingVariableInfo info) {
        super(info);
        this.outgoingConnections = new ArrayList<>();
    }

    public void connect(ParsingInput toVar, ParsingModule from, ParsingModule to, boolean isInverted){
        this.outgoingConnections.add(new ParsingConnection(from, this, to, toVar, isInverted));
    }

    public List<ParsingConnection> getOutgoingConnections() {
        return this.outgoingConnections;
    }

    @Override
    public FBVariable translate(){
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
        return new OutputVariable(var);
    }
}
