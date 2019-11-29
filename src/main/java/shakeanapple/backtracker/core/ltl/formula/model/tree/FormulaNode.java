package shakeanapple.backtracker.core.ltl.formula.model.tree;

import shakeanapple.backtracker.core.ltl.formula.ILtlFormulaVisitor;

import java.util.List;

public abstract class FormulaNode {

    public abstract <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor);

    public abstract List<FormulaNode> getChildren();

    public abstract String getName();
}


