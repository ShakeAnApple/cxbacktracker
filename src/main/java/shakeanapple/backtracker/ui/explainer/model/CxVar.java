package shakeanapple.backtracker.ui.explainer.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class CxVar {
    private String varName;
    private ObjectProperty<List<VarValueForStep>> varValues;

    public CxVar(String varName, List<VarValueForStep> varValues) {
        this.varName = varName;
        this.varValues = new SimpleObjectProperty<>(varValues);
    }

    public String getVarName() {
        return this.varName;
    }

    public List<VarValueForStep> getVarValues() {
        return varValues.get();
    }

    public ObjectProperty<List<VarValueForStep>> varValuesProperty() {
        return varValues;
    }
}
