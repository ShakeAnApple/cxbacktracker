package shakeanapple.backtracker.core.ltlcalculation.model;

import shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.tree.FormulaNode;

import java.util.List;

public interface ICalculatedNode {
    CalculationResult getResult();
    FormulaNode getNode();
    List<? extends ICalculatedNode> getChildren();
}
