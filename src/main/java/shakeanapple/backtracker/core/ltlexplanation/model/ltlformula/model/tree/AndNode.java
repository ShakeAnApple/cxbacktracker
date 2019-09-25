package shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.ILtlFormulaVisitor;

public class AndNode extends BinFormulaNode{

    public AndNode(FormulaNode left, FormulaNode right) {
        super(left, right);
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitAnd(this);
    }

    @Override
    public String getName() {
        return "And";
    }
}
