package shakeanapple.backtracker.core.ltl.explanation;

import shakeanapple.backtracker.core.ltl.explanation.model.ExplanationResult;
import shakeanapple.backtracker.core.ltl.formula.model.tree.FormulaNode;

public interface ILtlFormulaExplainer {
    ExplanationResult explainNodeForStep(FormulaNode node, int stepNum);
    ExplanationResult explainRootForStep(int stepNum);
}
