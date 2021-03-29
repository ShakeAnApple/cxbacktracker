package shakeanapple.backtracker.core.ltl.formula.model.tree;

import shakeanapple.backtracker.core.ltl.formula.ILtlFormulaVisitor;
import shakeanapple.backtracker.parser.ltlformula.recent.IntGenerator;

public class GreaterEqNode extends BinFormulaNode{

    public GreaterEqNode(FormulaNode left, FormulaNode right) {
        super(left, right, IntGenerator.instance().next());
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
