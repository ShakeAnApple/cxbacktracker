package shakeanapple.backtracker.core.ltlformula.model.tree;

public abstract class UnFormulaNode extends FormulaNode{
    private FormulaNode child;

    public UnFormulaNode(FormulaNode child) {
        this.child = child;
    }
}
