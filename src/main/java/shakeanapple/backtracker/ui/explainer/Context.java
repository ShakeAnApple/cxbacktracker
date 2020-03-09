package shakeanapple.backtracker.ui.explainer;

import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.IdGenerator;

public class Context {
    private int currentStep = 0;
    private Counterexample cx;
    private String formulaStr;

    private static Context instance;
    private String diagramPath;

    private Context() {
    }

    public static Context instance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public int getCurrentStep(){
        return this.currentStep;
    }

    public void setCurrentStep(int stepNum){
        this.currentStep = stepNum;
    }

    public void setCounterexample(Counterexample cx){
        this.cx = cx;
    }

    public Counterexample getCounterexample() {
        return this.cx;
    }

    public String getFormulaStr() {
        return this.formulaStr;
    }

    public void setFormulaStr(String formulaStr) {
        this.formulaStr = formulaStr;
    }

    public String getDiagramPath() {
        return this.diagramPath;
    }

    public void setDiagramPath(String diagramPath){
        this.diagramPath = diagramPath;
    }

    public void reset() {
        this.currentStep = 0;
        this.cx = null;
        this.diagramPath = null;
        this.formulaStr = null;
    }
}
