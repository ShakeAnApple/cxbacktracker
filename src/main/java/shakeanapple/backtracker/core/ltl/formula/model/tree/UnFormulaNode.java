package shakeanapple.backtracker.core.ltl.formula.model.tree;

import shakeanapple.backtracker.parser.ltlformula.recent.IntGenerator;

import java.util.ArrayList;
import java.util.List;

public abstract class UnFormulaNode extends FormulaNode{
    private final int id;
    private FormulaNode child;

    public FormulaNode getChild(){
        return this.child;
    }

    public UnFormulaNode(FormulaNode child) {
        this.child = child;
        this.id = IntGenerator.instance().next();
    }

    @Override
    public List<FormulaNode> getChildren() {
        List<FormulaNode> children = new ArrayList<>();
        children.add(this.child);
        return children;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
