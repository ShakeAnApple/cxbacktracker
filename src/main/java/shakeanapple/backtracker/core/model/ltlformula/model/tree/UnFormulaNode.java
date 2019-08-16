package shakeanapple.backtracker.core.model.ltlformula.model.tree;

public abstract class UnFormulaNode extends FormulaNode{
    private FormulaNode child;

    public FormulaNode getChild(){
        return this.child;
    }

    public UnFormulaNode(FormulaNode child) {
        this.child = child;
    }
}
