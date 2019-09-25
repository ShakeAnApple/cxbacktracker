package shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.ILtlFormulaVisitor;

public class XNode extends UnFormulaNode{

    public XNode(FormulaNode child) {
        super(child);
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitX(this);
    }

    @Override
    public String getName() {
        return "X";
    }
}
