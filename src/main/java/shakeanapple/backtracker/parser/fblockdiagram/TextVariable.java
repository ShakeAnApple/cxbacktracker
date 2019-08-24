package shakeanapple.backtracker.parser.fblockdiagram;

public class TextVariable {
    private final String name;
    private String type;

    private String typeFromVarName;

    public TextVariable(String name) {
        this.name = name;
    }

    public TextVariable(String name, String type) {
        this(name);
        this.type = type;
    }

    public TextVariable(String name, String varName, boolean typeFromVar) {
        this(name);
        this.typeFromVarName = varName;
    }

    public String getName() {
        return this.name;
    }

    public void defineType(String type){
        this.type = type;
    }

    public String getTypeFromVarName() {
        return this.typeFromVarName;
    }

    public String getType() {
        return this.type;
    }
}
