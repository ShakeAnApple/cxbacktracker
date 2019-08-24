package shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.ILtlFormulaVisitor;

public class NotEqNode extends BinFormulaNode{

    public NotEqNode(FormulaNode left, FormulaNode right) {
        super(left, right);
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitNotEq(this);
    }

    @Override
    public String getName() {
        return "<>";
    }
}
