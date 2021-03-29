package shakeanapple.backtracker.core.ltl.evaluation;

import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.ltl.evaluation.model.CalculatedFormula;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedNode;
import shakeanapple.backtracker.core.ltl.evaluation.model.inplacecalc.CalculatedInPlaceFormula;
import shakeanapple.backtracker.core.ltl.evaluation.model.inplacecalc.CalculatedInPlaceNode;
import shakeanapple.backtracker.core.ltl.formula.model.LtlFormula;
import shakeanapple.backtracker.core.ltl.formula.model.tree.FormulaNode;

public class LtlInPlaceEvaluator extends LtlWithCounterexampleEvaluator implements ILtlInPlaceEvaluator {
    private LtlFormula ltlFormula;

    public LtlInPlaceEvaluator(Counterexample counterexample, LtlFormula ltlFormula) {
        super(counterexample, ltlFormula);
        this.ltlFormula = ltlFormula;
    }

    public ICalculatedFormula evaluateInStep(int stepNum){
        CalculatedInPlaceNode root = this.evaluateInStepImpl(ltlFormula.getRoot(), stepNum);
        return new CalculatedInPlaceFormula(root);
    }

    private CalculatedInPlaceNode evaluateInStepImpl(FormulaNode parent, int stepNum){
        CalculatedInPlaceNode calcNode = new CalculatedInPlaceNode(parent, super.calculateNodeForStep(stepNum, parent));
        for (FormulaNode child: parent.getChildren()) {
            calcNode.addChild(this.evaluateInStepImpl(child, stepNum));
        }
        return calcNode;
    }
}
