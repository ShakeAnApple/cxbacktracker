package shakeanapple.backtracker.core.ltlcalculation;

import shakeanapple.backtracker.core.model.ltlformula.model.tree.FormulaNode;

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
