package shakeanapple.backtracker.ui.explainer.control.menubar.model;

public class CustomConfig {
    private String diagramPathCustom;
    private String cxPathCustom;
    private String formulaCustom;

    public String getDiagramPathCustom() {
        return this.diagramPathCustom;
    }

    public void setDiagramPathCustom(String diagramPathCustom) {
        this.diagramPathCustom = diagramPathCustom;
    }

    public String getCxPathCustom() {
        return this.cxPathCustom;
    }

    public void setCxPathCustom(String cxPathCustom) {
        this.cxPathCustom = cxPathCustom;
    }

    public String getFormulaCustom() {
        return this.formulaCustom;
    }

    public void setFormulaCustom(String formulaCustom) {
        this.formulaCustom = formulaCustom;
    }

    public boolean isCompleted(){
        return this.cxPathCustom != null && this.diagramPathCustom != null && this.formulaCustom != null;
    }

    public void clear() {
        this.diagramPathCustom = null;
        this.cxPathCustom = null;
        this.formulaCustom = null;
    }
}
