package shakeanapple.backtracker.core.ltlformula.model.tree;

public abstract class BinFormulaNode extends FormulaNode{
    private FormulaNode left;
    private FormulaNode right;

    public BinFormulaNode(FormulaNode left, FormulaNode right) {
        this.left = left;
        this.right = right;
    }
}
