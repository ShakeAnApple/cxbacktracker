package shakeanapple.backtracker.ui.infrasructure.control.ltl.model;

import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.ltl.explanation.model.ExplanationResult;

public class FormulaStep {
    private int stepNumber;
    private boolean isLoop;
    private FormulaCalculatedFlatSnapshot formula;

    public FormulaStep(int stepNumber, boolean isLoop, FormulaCalculatedFlatSnapshot formula) {
        this.stepNumber = stepNumber;
        this.isLoop = isLoop;
        this.formula = formula;
    }

    private FormulaStep(ICalculatedFormula calcFormula, boolean stepIsLoop, ExplanationResult explanationResult){
        this.stepNumber = calcFormula.getRoot().getResult().forStep();
        this.isLoop = stepIsLoop;
        this.formula = new FormulaCalculatedFlatSnapshot(calcFormula, explanationResult);
    }

    public int getStepNumber() {
        return this.stepNumber;
    }

    public boolean isLoop() {
        return this.isLoop;
    }

    public FormulaCalculatedFlatSnapshot getFormula() {
        return this.formula;
    }

    public static FormulaStep fromCalculatedFormula(ICalculatedFormula calcFormula, boolean stepIsLoop, ExplanationResult explanationResult){
        return new FormulaStep(calcFormula, stepIsLoop, explanationResult);
    }
}
