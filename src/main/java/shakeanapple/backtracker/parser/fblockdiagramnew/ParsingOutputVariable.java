package shakeanapple.backtracker.parser.fblockdiagramnew;

public class ParsingOutputVariable {
    private String name;
    private VarType type;
    private Assignment assignment;

    public ParsingOutputVariable(String name, VarType type, Assignment assignment) {
        this.name = name;
        this.type = type;
        this.assignment = assignment;
    }

    public static ParsingOutputVariable of(String varName, String assignment) {
        VarType type = VarType.UNKNOWN;
        assignment = assignment.replace(";", "").trim();
        Assignment as = Assignment.of(assignment);
        if (as.getNotNegatedContent().equals("boolean") || as.isNegated() || as.getNotNegatedContent().contains("&") || as.getNotNegatedContent().contains("|")
                || as.isConstBoolean()) {
            type = VarType.BOOLEAN;
        } else if (as.isConstInteger()){
            type = VarType.INTEGER;
        } else if (as.isFromOuterInterface()){
            type = VarType.UNKNOWN;
        }
        return new ParsingOutputVariable(varName.trim(), type, as);
    }

    public String getName() {
        return this.name;
    }

    public VarType getType() {
        return this.type;
    }

    public Assignment getAssignment() {
        return this.assignment;
    }
}
