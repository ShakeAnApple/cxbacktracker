package shakeanapple.backtracker.core.diagramexplanation.tonusmv;

public class NusmvBlockBasic implements NusmvBlock{
    private String contents;
    private boolean toAssign;

    public NusmvBlockBasic(String contents, boolean toAssign) {
        this.contents = contents;
        this.toAssign = toAssign;
    }

    public String getContents() {
        return this.contents;
    }

    public boolean isToAssign() {
        return this.toAssign;
    }

    @Override
    public void writeTo(NusmvStringModelBuilder mb) {
        if (this.toAssign){
            mb.appendAssignStatement(this.contents);
        } else{
            mb.appendDefineStatement(this.contents, false);
        }
    }
}
