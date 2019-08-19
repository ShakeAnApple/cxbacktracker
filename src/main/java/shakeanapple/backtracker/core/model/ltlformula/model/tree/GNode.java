package shakeanapple.backtracker.core.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.model.ltlformula.model.ILtlFormulaVisitor;

public class GNode extends UnFormulaNode{

    public GNode(FormulaNode child) {
        super(child);
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitG(this);
    }

    @Override
    public String getName() {
        return "G";
    }
}
