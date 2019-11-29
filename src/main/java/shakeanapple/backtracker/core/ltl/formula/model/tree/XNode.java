package shakeanapple.backtracker.core.ltl.formula.model.tree;

import shakeanapple.backtracker.core.ltl.formula.ILtlFormulaVisitor;

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
