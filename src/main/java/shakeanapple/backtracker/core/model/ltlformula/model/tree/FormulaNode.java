package shakeanapple.backtracker.core.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.model.ltlformula.model.ILtlFormulaVisitor;

public abstract class FormulaNode {

    public abstract <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor);
}


