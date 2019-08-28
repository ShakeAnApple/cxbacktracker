package shakeanapple.backtracker.parser.fblockdiagram;

public class Type {
    private VarType typeSpec;
    private TypeClue clue;

    public Type(VarType typeSpec, TypeClue clue) {
        this.typeSpec = typeSpec;
        this.clue = clue;
    }

    public Type(VarType type) {
        this.typeSpec = type;
    }

    public VarType getTypeSpec() {
        return this.typeSpec;
    }

    public TypeClue getClue() {
        return this.clue;
    }

    public void defineType(VarType type) {
        this.typeSpec = type;
    }
}
