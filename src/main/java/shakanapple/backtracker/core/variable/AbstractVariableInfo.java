package shakeanapple.backtracker.core.variable;

import java.util.List;
import java.util.Set;

public abstract class AbstractVariableInfo<VHolderType extends AbstractValueHolder> {
    private String _name;
    protected Set<VHolderType> _valuesSet;

    protected AbstractVariableInfo(){}

    public AbstractVariableInfo(String name, int order, Set<VHolderType> valuesSet) {
        _name = name;
        _valuesSet = valuesSet;
    }

    public String getName(){
        return _name;
    }

    public Set<VHolderType> getValuesSet(){
        return _valuesSet;
    }
}
