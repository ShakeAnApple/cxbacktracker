package shakeanapple.backtracker.core.ltlexplanation.model;

import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.tree.FormulaNode;

import java.util.List;

public interface ICalculatedNode {
    CalculationResult getResult();
    FormulaNode getNode();
    List<? extends ICalculatedNode> getChildren();
}
