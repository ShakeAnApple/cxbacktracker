package shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.tree;

import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.ILtlFormulaVisitor;

import java.util.ArrayList;
import java.util.List;

public class VarNode extends FormulaNode{
    private String name;

    public VarNode(String name) {
        this.name = name;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public <TRes> TRes apply(ILtlFormulaVisitor<TRes> visitor) {
        return visitor.visitVar(this);
    }

    @Override
    public List<FormulaNode> getChildren() {
        return new ArrayList<>();
    }
}
