package shakeanapple.backtracker.core.ltlcalculation.model;

import shakeanapple.backtracker.core.ltlcalculation.History;
import shakeanapple.backtracker.core.model.ltlformula.model.tree.FormulaNode;

import java.util.ArrayList;
import java.util.List;

public class CalculatedNode implements ICalculatedNode {
    private final FormulaNode node;
    private final List<CalculatedNode> children;

    private CalculationResult res;

    public CalculatedNode(FormulaNode node) {
        this.node = node;
        this.children = new ArrayList<>();
        for (FormulaNode child:node.getChildren()) {
            this.children.add(new CalculatedNode(child));
        }
    }

    @Override
    public CalculationResult getResult(){
        return this.res;
    }

    @Override
    public FormulaNode getNode() {
        return this.node;
    }

    @Override
    public List<? extends ICalculatedNode> getChildren() {
        return this.children;
    }

    public void applyHistory(History h, int stepNum) {
        this.res = h.getResultForNodeByStep(this.node, stepNum);
        for (CalculatedNode child : this.children) {
            child.applyHistory(h, stepNum);
        }
    }
}
