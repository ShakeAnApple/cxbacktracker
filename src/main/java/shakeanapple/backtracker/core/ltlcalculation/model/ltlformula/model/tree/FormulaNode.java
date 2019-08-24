package shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.ILtlFormulaVisitor;

import java.util.List;

public abstract class FormulaNode {

    public abstract <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor);

    public abstract List<FormulaNode> getChildren();

    public abstract String getName();
}


