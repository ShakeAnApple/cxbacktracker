package shakeanapple.backtracker.core.ltl.evaluation;

import shakeanapple.backtracker.core.ltl.evaluation.model.CalculationResult;
import shakeanapple.backtracker.core.ltl.formula.model.tree.FormulaNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History {
    private Map<FormulaNode, Map<Integer, CalculationResult>> history;

    public History() {
        this.history = new HashMap<>();
    }

    public CalculationResult getResultForNodeByStep(FormulaNode node, int step){
        if (!this.history.containsKey(node)){
            return null;
        }

        if (!this.history.get(node).containsKey(step)){
            return null;
        }

        return this.history.get(node).get(step);
    }

    public void registerNode(FormulaNode node) {
        if (!this.history.containsKey(node)){
            this.history.put(node, new HashMap<>());
        }
    }

    public void addResForNode(FormulaNode node, CalculationResult res) {
        if (this.history.get(node).containsKey(res.forStep()) && this.history.get(node).get(res.forStep()) == null){
            this.history.get(node).replace(res.forStep(), res);
        } else{
            this.history.get(node).put(res.forStep(), res);
        }
    }

    public void addAttemptForNode(FormulaNode node, int stepNum) {
        this.history.get(node).put(stepNum, null);
    }

    public boolean attemptForNodeExists(FormulaNode node, int stepNum){
        if (!this.history.containsKey(node)){
            return false;
        }

        if (!this.history.get(node).containsKey(stepNum)){
            return false;
        }

        return this.history.get(node).get(stepNum) == null;
    }
}
