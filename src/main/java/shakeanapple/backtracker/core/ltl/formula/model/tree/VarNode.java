package shakeanapple.backtracker.core.ltl.formula.model.tree;

import shakeanapple.backtracker.core.ltl.formula.ILtlFormulaVisitor;
import shakeanapple.backtracker.parser.ltlformula.recent.IntGenerator;

import java.util.ArrayList;
import java.util.List;

public class VarNode extends FormulaNode{
    private final int id;
    private String name;

    public VarNode(String name) {
        this.name = name;
        this.id = IntGenerator.instance().next();
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

    @Override
    public int getId() {
        return this.id;
    }
}
