package shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.tree;

import java.util.ArrayList;
import java.util.List;

public abstract class UnFormulaNode extends FormulaNode{
    private FormulaNode child;

    public FormulaNode getChild(){
        return this.child;
    }

    public UnFormulaNode(FormulaNode child) {
        this.child = child;
    }

    @Override
    public List<FormulaNode> getChildren() {
        List<FormulaNode> children = new ArrayList<>();
        children.add(this.child);
        return children;
    }
}
