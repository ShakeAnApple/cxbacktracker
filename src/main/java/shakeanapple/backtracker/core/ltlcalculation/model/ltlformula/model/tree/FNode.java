package shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.ILtlFormulaVisitor;

public class FNode extends UnFormulaNode{

    public FNode(FormulaNode child) {
        super(child);
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitF(this);
    }

    @Override
    public String getName() {
        return "F";
    }
}
