package shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.BooleanDynamicVariable;
import shakeanapple.backtracker.common.variable.dynamic.IntegerDynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;

public class InputDefinition {
    private long id;
    private VarDefinitionType type;
    private String name;
    private int order;

    public InputDefinition(long id, VarDefinitionType type, String name, int order) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.order = order;
    }

    public long getId() {
        return this.id;
    }

    public VarDefinitionType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public int getOrder() {
        return this.order;
    }

    public InputVariable translate() {
        if (this.type == VarDefinitionType.BOOLEAN){
            boolean val = false;
            if (this instanceof ConstantInputDefinition){
                val = ((ConstantInputDefinition) this).getValue().toLowerCase().equals("true");
            }
            BooleanValueHolder valueHolder = new BooleanValueHolder(val);
            return new InputVariable(new BooleanDynamicVariable(valueHolder, this.name), this.order);
        } else{
            int val = Integer.MIN_VALUE;
            if (this instanceof ConstantInputDefinition){
                val = Integer.parseInt(((ConstantInputDefinition)this).getValue());
            }
            IntegerValueHolder valueHolder = new IntegerValueHolder(val);
            return new InputVariable(new IntegerDynamicVariable(valueHolder, this.name), this.order);
        }
    }
}
