package shakeanapple.backtracker.parser.fblockdiagram;

import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlockVariableInfo;
import shakeanapple.backtracker.core.fblockmapping.model.VariableType;

public class TextVariable {
    private final String name;
    private Type type;
    private boolean isInverted;

//    private String type;

//    private String typeFromVarName;
//    private boolean isTypeDefinedByInput;

    public TextVariable(String name) {
        this.name = name;
    }

    public TextVariable(String name, Type type) {
        this(name);
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void defineType(VarType type){
        this.type.defineType(type);
    }

    public Type getType() {
        return this.type;
    }

    public void isInverted(boolean inverted) {
        this.isInverted = inverted;
    }

    public boolean isInverted() {
        return this.isInverted;
    }

    public FunctionBlockVariableInfo translate() {
        return new FunctionBlockVariableInfo(this.name, this.isInverted, this.getType().getTypeSpec() == VarType.BOOLEAN ? VariableType.BOOLEAN : VariableType.INTEGER);
    }
}
