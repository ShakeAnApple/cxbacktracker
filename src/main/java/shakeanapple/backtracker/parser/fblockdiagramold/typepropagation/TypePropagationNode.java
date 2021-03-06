package shakeanapple.backtracker.parser.fblockdiagramold.typepropagation;

import shakeanapple.backtracker.parser.fblockdiagramold.model.ParsingVariableInfo;
import shakeanapple.backtracker.parser.fblockdiagramold.model.ParsingVariableType;

import java.util.HashMap;
import java.util.Map;

public abstract class TypePropagationNode {
    private Map<String, TypePropagationNode> connectedVars;

    public TypePropagationNode() {
        this.connectedVars = new HashMap<>();
    }

    public abstract void propagateType();
    public abstract TypePropagationNode defineType(ParsingVariableType type);

    public void connect(ParsingVariableInfo var, String moduleType){
        this.connectedVars.put(moduleType + "." + var.getName(), new TypePropagationVariableNode(var));
    }

    Map<String, TypePropagationNode> getConnectedVars(){
        return this.connectedVars;
    }
}
