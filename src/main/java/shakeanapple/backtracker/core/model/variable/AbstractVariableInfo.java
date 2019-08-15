package shakeanapple.backtracker.core.model.variable;

import java.util.Set;

public abstract class AbstractVariableInfo<VHolderType extends AbstractValueHolder> {
    private String name;
    protected Set<VHolderType> valuesSet;

    protected AbstractVariableInfo(){}

    public AbstractVariableInfo(String name, Set<VHolderType> valuesSet) {
        this.name = name;
        this.valuesSet = valuesSet;
    }

    public String getName(){
        return name;
    }

    public Set<VHolderType> getValuesSet(){
        return valuesSet;
    }
}
