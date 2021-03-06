package shakeanapple.backtracker.core.ltl.evaluation.model;

import shakeanapple.backtracker.core.ltl.formula.model.tree.FormulaNode;

import java.util.List;

public interface ICalculatedNode {
    CalculationResult getResult();
    boolean isLeaf();
    FormulaNode getNode();
    List<? extends ICalculatedNode> getChildren();
}
