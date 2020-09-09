package shakeanapple.backtracker.ui.infrasructure.control.ltl.model;

import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedNode;
import shakeanapple.backtracker.core.ltl.explanation.model.ExplanationResult;

import java.util.LinkedList;

public class FormulaCalculatedFlatSnapshot {
    private LinkedList<FormulaNodeSnapshot> nodes = new LinkedList<>();

    public FormulaCalculatedFlatSnapshot(ICalculatedFormula calcFormula, ExplanationResult explanationResult) {
        LinkedList<ICalculatedNode> calcNodes = this.flattenHierarchy(calcFormula.getRoot());

        for (ICalculatedNode node: calcNodes){
            boolean isNodeCause = explanationResult.getCauses().stream()
                    .anyMatch(c -> c.getVarName().equals(node.getNode().getName()) && c.getStepNum() == node.getResult().forStep());
            this.nodes.addLast(new FormulaNodeSnapshot(node, isNodeCause));
        }
    }

    private LinkedList<ICalculatedNode> flattenHierarchy(ICalculatedNode current) {
        LinkedList<ICalculatedNode> res = new LinkedList<>();
        if (current.getChildren().size() == 0){ // variable or const
            res.addLast(current);
        } else if (current.getChildren().size() == 1 ){ // unary op
            res.addLast(current);
            res.addAll(this.flattenHierarchy(current.getChildren().get(0)));
        } else if (current.getChildren().size() == 2){ // binary op
            res.addAll(this.flattenHierarchy(current.getChildren().get(0)));
            res.addLast(current);
            res.addAll(this.flattenHierarchy(current.getChildren().get(1)));
        } else{
            throw new RuntimeException("Only vars, consts, unary and binary operators are supported in an LTL tree");
        }
        return res;
    }

    public LinkedList<FormulaNodeSnapshot> getNodes() {
        return this.nodes;
    }
}
