package shakeanapple.backtracker.core.ltl.evaluation;

import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedFormula;

public interface ILtlInPlaceEvaluator {
    ICalculatedFormula evaluateInStep(int stepNum);
}
