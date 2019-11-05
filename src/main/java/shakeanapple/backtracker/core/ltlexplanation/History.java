package shakeanapple.backtracker.core.ltlexplanation;

import shakeanapple.backtracker.core.ltlexplanation.model.CalculationResult;
import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.tree.FormulaNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History {
    private Map<FormulaNode, List<CalculationResult>> history;

    public History() {
        this.history = new HashMap<>();
    }

    public CalculationResult getResultForNodeByStep(FormulaNode node, int step){
        if (!this.history.containsKey(node)){
            return null;
        }

        if (this.history.get(node).size() < step){
            return null;
        }

        return this.history.get(node).get(step);
    }

    public void registerNode(FormulaNode node) {
        if (!this.history.containsKey(node)){
            this.history.put(node, new ArrayList<CalculationResult>());
        }
    }

    public void addResForNode(FormulaNode node, CalculationResult res) {
        this.history.get(node).add(res);
    }
}