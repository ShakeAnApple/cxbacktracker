package shakeanapple.backtracker.core.ltl.evaluation.model.inplacecalc;

import shakeanapple.backtracker.core.ltl.evaluation.model.CalculatedNode;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedNode;

public class CalculatedInPlaceFormula implements ICalculatedFormula {
    private final CalculatedInPlaceNode root;

    public CalculatedInPlaceFormula(CalculatedInPlaceNode root){
        this.root = root;
    }

    @Override
    public ICalculatedNode getRoot() {
        return this.root;
    }
}
