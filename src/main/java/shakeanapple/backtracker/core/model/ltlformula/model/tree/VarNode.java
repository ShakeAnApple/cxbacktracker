package shakeanapple.backtracker.core.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.model.ltlformula.model.ILtlFormulaVisitor;

public class VarNode extends FormulaNode{
    private String name;

    public VarNode(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitVar(this);
    }
}
