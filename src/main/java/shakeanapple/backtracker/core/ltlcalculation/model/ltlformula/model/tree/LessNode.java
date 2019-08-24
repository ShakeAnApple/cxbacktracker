package shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.ILtlFormulaVisitor;

public class LessNode extends BinFormulaNode{

    public LessNode(FormulaNode left, FormulaNode right) {
        super(left, right);
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitLess(this);
    }

    @Override
    public String getName() {
        return "<";
    }
}
