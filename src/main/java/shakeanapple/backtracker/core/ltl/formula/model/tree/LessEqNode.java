package shakeanapple.backtracker.core.ltl.formula.model.tree;

import shakeanapple.backtracker.core.ltl.formula.ILtlFormulaVisitor;

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
