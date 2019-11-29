package shakeanapple.backtracker.core.ltl.formula.model.tree;

import shakeanapple.backtracker.core.ltl.formula.ILtlFormulaVisitor;

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
