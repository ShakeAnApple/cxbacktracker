package shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.ILtlFormulaVisitor;

public class LessEqNode extends BinFormulaNode{

    public LessEqNode(FormulaNode left, FormulaNode right) {
        super(left, right);
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitLessEq(this);
    }

    @Override
    public String getName() {
        return "<=";
    }
}
