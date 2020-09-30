package shakeanapple.backtracker.core.ltl.evaluation.model.inplacecalc;

import shakeanapple.backtracker.core.ltl.evaluation.model.CalculatedNode;
import shakeanapple.backtracker.core.ltl.evaluation.model.CalculationResult;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedNode;
import shakeanapple.backtracker.core.ltl.formula.model.tree.FormulaNode;

import java.util.ArrayList;
import java.util.List;

public class CalculatedInPlaceNode implements ICalculatedNode {
    private final FormulaNode node;
    private final List<CalculatedInPlaceNode> children;

    private CalculationResult res;

    public CalculatedInPlaceNode(FormulaNode node, CalculationResult res) {
        this.node = node;
        this.res = res;
        this.children = new ArrayList<>();
    }

    public void addChild(CalculatedInPlaceNode child){
        this.children.add(child);
    }

    public void addChildren(List<CalculatedInPlaceNode> children){
        this.children.addAll(children);
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
}
