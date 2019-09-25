package shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.ILtlFormulaVisitor;

public class WNode extends BinFormulaNode{

    public WNode(FormulaNode left, FormulaNode right) {
        super(left, right);
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitW(this);
    }

    @Override
    public String getName() {
        return "W";
    }
}
