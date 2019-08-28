package shakeanapple.backtracker.core.ltlcalculation;

import shakeanapple.backtracker.core.ltlcalculation.model.ICalculatedFormula;

public interface LtlSequentialEvaluator {
    ICalculatedFormula moveNext();
    ICalculatedFormula getForStep(int stepNum);
}
