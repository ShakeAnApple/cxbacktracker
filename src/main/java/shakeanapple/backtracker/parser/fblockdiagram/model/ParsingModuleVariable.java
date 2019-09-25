package shakeanapple.backtracker.parser.fblockdiagram.model;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.BooleanDynamicVariable;
import shakeanapple.backtracker.common.variable.dynamic.DynamicVariable;
import shakeanapple.backtracker.common.variable.dynamic.IntegerDynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.FBVariable;

public class ParsingModuleVariable {
    private ParsingVariableInfo info;

    public ParsingModuleVariable(ParsingVariableInfo info) {
        this.info = info;
    }

    public ParsingVariableInfo getInfo(){
        return this.info;
    }

    public FBVariable translate() {
        DynamicVariable var;
        switch (this.info.getType()) {
            case BOOLEAN:
                var = new BooleanDynamicVariable(new BooleanValueHolder(false), this.info.getName());
                break;
            case INTEGER:
                var = new IntegerDynamicVariable(new IntegerValueHolder(Integer.MIN_VALUE), this.info.getName());
                break;
            default:
                throw new RuntimeException("Unsupported var type: " + this.info.getType().name());
        }
        return new FBVariable(var);
    }
}
