package shakeanapple.backtracker.core.ltl.evaluation.model;

import shakeanapple.backtracker.core.counterexample.CounterexampleCursor;
import shakeanapple.backtracker.core.ltl.evaluation.History;
import shakeanapple.backtracker.core.ltl.formula.model.LtlFormula;

public class CalculatedFormula implements ICalculatedFormula {
    private final CalculatedNode root;

    private CalculatedFormula(CalculatedNode root){
        this.root = root;
    }

    @Override
    public ICalculatedNode getRoot(){
        return this.root;
    }

    public void applyHistory(History h, CounterexampleCursor cursor){
        this.root.applyHistory(h, cursor);
    }

    public static CalculatedFormula fromBasicLtl(LtlFormula formula){
        CalculatedNode root = new CalculatedNode(formula.getRoot());
        return new CalculatedFormula(root);
    }
}
