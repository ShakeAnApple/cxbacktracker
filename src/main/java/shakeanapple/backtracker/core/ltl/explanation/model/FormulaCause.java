package shakeanapple.backtracker.core.ltl.explanation.model;

public class FormulaCause {
    private int stepNum;
    private String varName;
    private String value;
    private int nodeId;

    public FormulaCause(int stepNum, String varName, String value, int nodeId) {
        this.stepNum = stepNum;
        this.varName = varName;
        this.value = value;
        this.nodeId = nodeId;
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

    public int getNodeId() {
        return this.nodeId;
    }
}
