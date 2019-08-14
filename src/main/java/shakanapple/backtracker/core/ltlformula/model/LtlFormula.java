package shakeanapple.backtracker.core.ltlformula.model;

import shakeanapple.backtracker.core.ltlformula.model.tree.FormulaNode;
import shakeanapple.backtracker.core.ltlformula.parser.LtlModelBuilder;

public class LtlFormula {
    private final FormulaNode root;

    public LtlFormula(FormulaNode root) {
        this.root = root;
    }

    public FormulaNode getRoot(){
        return this.root;
    }

    public static LtlFormula parse(String str){
        return new LtlModelBuilder().complete(str);
    }
}
