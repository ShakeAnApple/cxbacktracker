package shakeanapple.backtracker.core.ltl.evaluation.model;

import shakeanapple.backtracker.core.counterexample.CounterexampleCursor;
import shakeanapple.backtracker.core.ltl.evaluation.History;
import shakeanapple.backtracker.core.ltl.formula.model.tree.FormulaNode;
import shakeanapple.backtracker.core.ltl.formula.model.tree.XNode;

import java.util.ArrayList;
import java.util.List;

public class CalculatedNode implements ICalculatedNode {
    private final FormulaNode node;
    private final List<CalculatedNode> children;

    private CalculationResult res;

    public CalculatedNode(FormulaNode node) {
        this.node = node;
        this.children = new ArrayList<>();
        for (FormulaNode child : node.getChildren()) {
            this.children.add(new CalculatedNode(child));
        }
    }

    @Override
    public CalculationResult getResult() {
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

    public void applyHistory(History h, CounterexampleCursor cursor) {
        this.res = h.getResultForNodeByStep(this.node, cursor.getCurStateNum());
        CounterexampleCursor timeBranch = this.node instanceof XNode ? cursor.branchNext() : cursor;
        for (CalculatedNode child : this.children) {
            child.applyHistory(h, timeBranch);
        }
    }
}
