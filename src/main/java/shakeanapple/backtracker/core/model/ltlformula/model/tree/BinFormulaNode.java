package shakeanapple.backtracker.core.model.ltlformula.model.tree;

import java.util.ArrayList;
import java.util.List;

public abstract class BinFormulaNode extends FormulaNode{
    private FormulaNode left;
    private FormulaNode right;

    public BinFormulaNode(FormulaNode left, FormulaNode right) {
        this.left = left;
        this.right = right;
    }

    public FormulaNode getLeft(){
        return this.left;
    }

    public FormulaNode getRight(){
        return this.right;
    }

    @Override
    public List<FormulaNode> getChildren() {
        List<FormulaNode> children = new ArrayList<>();
        children.add(this.left);
        children.add(this.right);
        return children;
    }
}
