package shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.ILtlFormulaVisitor;

public class MulNode extends BinFormulaNode{

    public MulNode(FormulaNode left, FormulaNode right) {
        super(left, right);
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitMul(this);
    }

    @Override
    public String getName() {
        return "*";
    }
}
