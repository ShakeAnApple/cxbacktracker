package shakeanapple.backtracker.core.ltlexplanation;

import shakeanapple.backtracker.core.ltlexplanation.model.ICalculatedFormula;

public interface LtlSequentialEvaluator {
    ICalculatedFormula moveNext();
    ICalculatedFormula getForStep(int stepNum);
}
