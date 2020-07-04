package shakeanapple.backtracker.core.diagramexplanation.tonusmv;

public class NusmvBlockBasic implements NusmvBlock{
    private String statements;
    private boolean toAssign;

    public NusmvBlockBasic(String statements, boolean toAssign) {
        this.statements = statements;
        this.toAssign = toAssign;
    }

    public String getStatements() {
        return this.statements;
    }

    public boolean isToAssign() {
        return this.toAssign;
    }

    @Override
    public void writeTo(NusmvStringModelBuilder mb) {
        if (this.toAssign){
            mb.appendAssignStatement(this.statements);
        } else{
            mb.appendDefineStatement(this.statements, false);
        }
    }
}
