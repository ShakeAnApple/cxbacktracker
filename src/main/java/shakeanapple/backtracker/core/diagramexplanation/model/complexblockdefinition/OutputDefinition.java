package shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.dynamic.BooleanDynamicVariable;
import shakeanapple.backtracker.common.variable.dynamic.IntegerDynamicVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

public class OutputDefinition {
    private long id;
    private VarDefinitionType type;
    private String name;
    private String defaultValue;

    public OutputDefinition(long id, VarDefinitionType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public OutputDefinition(long id, VarDefinitionType type, String name, String defaultValue) {
        this(id, type, name);
        this.defaultValue = defaultValue.isEmpty() ? null : defaultValue;
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

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public OutputVariable translate() {
        if (this.type == VarDefinitionType.BOOLEAN){
            if (this.defaultValue != null){
                BooleanValueHolder valueHolder = new BooleanValueHolder(this.getDefaultValue().toLowerCase().equals("true"));
                return new OutputVariable(new BooleanDynamicVariable(valueHolder, this.name), valueHolder);
            }
            return new OutputVariable(new BooleanDynamicVariable(new BooleanValueHolder(false), this.name));
        } else{
            if (this.defaultValue != null){
                IntegerValueHolder valueHolder = new IntegerValueHolder(Integer.parseInt(this.defaultValue));
                return new OutputVariable(new IntegerDynamicVariable(valueHolder, this.name), valueHolder);
            }
            return new OutputVariable(new IntegerDynamicVariable(new IntegerValueHolder(Integer.MIN_VALUE), this.name));
        }
    }
}
