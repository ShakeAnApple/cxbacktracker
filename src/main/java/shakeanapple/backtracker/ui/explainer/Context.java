package shakeanapple.backtracker.ui.explainer;

import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.SpecVerified;

import java.util.List;

public class Context {
    private int currentStep = 0;

    private static Context instance;
    private String diagramPath;

    private List<SpecVerified> specsVerified;
    private SpecVerified activeSpecVerified;

    private Context() {
    }

    public static Context instance() {
        if (instance == null) {
            instance = new Context();
        }
        return instance;
    }

    public SpecVerified getActiveSpecVerified() {
        return this.activeSpecVerified;
    }

    public List<SpecVerified> getSpecsVerified() {
        return this.specsVerified;
    }

    public int getCurrentStep(){
        return this.currentStep;
    }

    public void setCurrentStep(int stepNum){
        this.currentStep = stepNum;
    }

    public void setSpecsVerified(List<SpecVerified> specsVerified){
        this.specsVerified = specsVerified;
        this.activeSpecVerified = this.specsVerified.get(0);
    }

    public Counterexample getCounterexample() {
        return this.activeSpecVerified.getCx();
    }

    public String getFormulaStr() {
        return this.activeSpecVerified.getFormulaStr();
    }

    public String getDiagramPath() {
        return this.diagramPath;
    }

    public void setDiagramPath(String diagramPath){
        this.diagramPath = diagramPath;
    }

    public void reset() {
        this.currentStep = 0;
        this.diagramPath = null;
        this.specsVerified.clear();
    }

    public void setActiveSpecVerified(SpecVerified newFormula) {
        this.activeSpecVerified = this.specsVerified.stream().filter(spec -> spec.toString().equals(newFormula.toString())).findFirst().get();
    }
}
