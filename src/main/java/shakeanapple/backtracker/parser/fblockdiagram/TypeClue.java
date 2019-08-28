package shakeanapple.backtracker.parser.fblockdiagram;

public class TypeClue {
    private boolean isDefinedByInput;
    private boolean isDefinedFromVarName;
    private String stringClue;

    public TypeClue(boolean isDefinedByInput, boolean isDefinedFromVarName, String stringClue) {
        this.isDefinedByInput = isDefinedByInput;
        this.isDefinedFromVarName = isDefinedFromVarName;
        this.stringClue = stringClue;
    }

    public boolean isDefinedByInput() {
        return this.isDefinedByInput;
    }

    public void isDefinedByInput(boolean isDefinedByInput) {
        this.isDefinedByInput = isDefinedByInput;
    }

    public String getStringClue() {
        return this.stringClue;
    }

    public boolean isDefinedFromVarName() {
        return this.isDefinedFromVarName;
    }

    public void isDefinedFromVarName(boolean isDefinedFromVarName) {
        this.isDefinedFromVarName = isDefinedFromVarName;
    }
}
