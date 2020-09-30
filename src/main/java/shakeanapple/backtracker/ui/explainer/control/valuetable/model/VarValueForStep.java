package shakeanapple.backtracker.ui.explainer.control.valuetable.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class VarValueForStep {
    private String fullVarName;
    private String varName;
    private String blockName;
    private String value;
    private ObjectProperty<Boolean> isCause;
    private int stepNum;

    public VarValueForStep(String fullVarName, String value, int stepNum, boolean isCause) {
        this.value = value;
        this.fullVarName = fullVarName;
        this.isCause = new SimpleObjectProperty<>(isCause);
        this.stepNum = stepNum;

        String[] nameParts = this.fullVarName.split("\\.");
        this.varName = this.fullVarName.contains(".") ? nameParts[nameParts.length - 1] : this.fullVarName;
    }

    public String getFullVarName() {
        return this.fullVarName;
    }

    public int getStepNum() {
        return this.stepNum;
    }

    public String getVarName(){
        return varName;
    }

    public String getBlockName() {
        return this.blockName;
    }

    public String getValue() {
        return this.value;
    }

    public ObjectProperty<Boolean> isCauseProperty() {
        return this.isCause;
    }
}
