package shakeanapple.backtracker.core.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.model.ltlformula.model.ILtlFormulaVisitor;

public class MinusNode extends BinFormulaNode{

    public MinusNode(FormulaNode left, FormulaNode right) {
        super(left, right);
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitMinus(this);
    }
}
