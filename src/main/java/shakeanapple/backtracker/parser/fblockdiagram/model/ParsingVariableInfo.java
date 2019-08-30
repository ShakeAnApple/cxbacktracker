package shakeanapple.backtracker.parser.fblockdiagram.model;

public class ParsingVariableInfo {
    private final String name;
    private ParsingVariableType type;

    public ParsingVariableInfo(String name) {
        this.name = name;
    }

    public ParsingVariableInfo(String name, ParsingVariableType type) {
        this(name);
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public ParsingVariableType getType() {
        return this.type;
    }

    public void defineType(ParsingVariableType type) {
        this.type = type;
    }
}
