package shakeanapple.backtracker.core.ltl.explanation.model;

public class FormulaCause {
    private int stepNum;
    private String varName;
    private String value;

    public FormulaCause(int stepNum, String varName, String value) {
        this.stepNum = stepNum;
        this.varName = varName;
        this.value = value;
    }

    public int getStepNum() {
        return this.stepNum;
    }

    public String getVarName() {
        return this.varName;
    }

    public String getValue() {
        return this.value;
    }
}
