package shakeanapple.backtracker.core.ltl.formula.model.tree;

import shakeanapple.backtracker.core.ltl.formula.ILtlFormulaVisitor;

public class GreaterEqNode extends BinFormulaNode{

    public GreaterEqNode(FormulaNode left, FormulaNode right) {
        super(left, right);
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitGreaterEq(this);
    }

    @Override
    public String getName() {
        return ">=";
    }
}
