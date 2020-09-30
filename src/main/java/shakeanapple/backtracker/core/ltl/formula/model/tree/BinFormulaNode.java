package shakeanapple.backtracker.core.ltl.formula.model.tree;

import java.util.ArrayList;
import java.util.List;

public abstract class BinFormulaNode extends FormulaNode{
    protected int id;
    private FormulaNode left;
    private FormulaNode right;

    public BinFormulaNode(FormulaNode left, FormulaNode right, int id) {
        this.left = left;
        this.right = right;
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
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
