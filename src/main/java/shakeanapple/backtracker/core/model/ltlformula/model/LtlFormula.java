package shakeanapple.backtracker.core.model.ltlformula.model;

import shakeanapple.backtracker.core.model.ltlformula.model.tree.FormulaNode;
import shakeanapple.backtracker.parser.ltlformula.LtlModelBuilder;

public class LtlFormula {
    private final FormulaNode root;

    public LtlFormula(FormulaNode root) {
        this.root = root;
    }

    public FormulaNode getRoot(){
        return this.root;
    }

    public <TRes> TRes applyVisitor(ILtlFormulaVisitor<TRes> visitor){
        return this.root.apply(visitor);
    }

    public static LtlFormula parse(String str){
        return new LtlModelBuilder().complete(str);
    }
}
