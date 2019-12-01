package shakeanapple.backtracker.ui.explainer.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class VarValueForStep {
    private String varName;
    private String value;
    private ObjectProperty<Boolean> isCause;

    public VarValueForStep(String varName, String value, boolean isCause) {
        this.value = value;
        this.varName = varName;
        this.isCause = new SimpleObjectProperty<>(isCause);
    }

    public String getVarName() {
        return this.varName;
    }

    public String getValue() {
        return this.value;
    }

    public ObjectProperty<Boolean> isCauseProperty() {
        return this.isCause;
    }
}
