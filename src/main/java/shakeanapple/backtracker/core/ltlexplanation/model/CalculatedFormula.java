package shakeanapple.backtracker.core.ltlexplanation.model;

import shakeanapple.backtracker.core.ltlexplanation.History;
import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.LtlFormula;

public class CalculatedFormula implements ICalculatedFormula {
    private final CalculatedNode root;

    private CalculatedFormula(CalculatedNode root){
        this.root = root;
    }

    @Override
    public ICalculatedNode getRoot(){
        return this.root;
    }

    public void applyHistory(History h, int stepNum){
        this.root.applyHistory(h, stepNum);
    }

    public static CalculatedFormula fromBasicLtl(LtlFormula formula){
        CalculatedNode root = new CalculatedNode(formula.getRoot());
        return new CalculatedFormula(root);
    }
}