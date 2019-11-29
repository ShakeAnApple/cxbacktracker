package shakeanapple.backtracker.core.ltl.evaluation;

import shakeanapple.backtracker.core.ltl.evaluation.model.CalculationResult;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.ltl.formula.model.tree.AndNode;
import shakeanapple.backtracker.core.ltl.formula.model.tree.FormulaNode;

public interface LtlEvaluator {
    ICalculatedFormula moveNext();
    ICalculatedFormula calculateRootForStep(int stepNum);

    CalculationResult calculateNodeForStep(int stateNum, FormulaNode formulaNode);
}
