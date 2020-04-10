package shakeanapple.backtracker.parser.fblockdiagramnew;

public class ParsingInputVariable {
    private String name;
    private VarType type;
    private int order;

    public ParsingInputVariable(int order, String name, VarType type) {
        this.name = name;
        this.type = type;
        this.order = order;
    }

    public static ParsingInputVariable of(int order, String varString){
        String[] parts = varString.replace(";", "").trim().split(":");
        VarType type = parts[1].trim().toLowerCase().equals("boolean") ? VarType.BOOLEAN : VarType.INTEGER;
        return new ParsingInputVariable(order, parts[0].trim(), type);
    }

    public String getName() {
        return this.name;
    }

    public VarType getType() {
        return this.type;
    }

    public int getOrder() {
        return this.order;
    }
}
