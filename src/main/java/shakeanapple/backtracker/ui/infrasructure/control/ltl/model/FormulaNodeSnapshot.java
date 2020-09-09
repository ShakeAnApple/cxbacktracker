package shakeanapple.backtracker.ui.infrasructure.control.ltl.model;

import shakeanapple.backtracker.core.ltl.evaluation.model.ArithmeticResult;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedNode;

public class FormulaNodeSnapshot {
    private NodeKind nodeKind;
    private String nodeName;

    private NodeResultKind resultKind;
    private String result;

    private int stepNum;

    private boolean isCause;

    public FormulaNodeSnapshot(ICalculatedNode node, boolean isCause) {
        this.result = node.getResult().toString();
        this.resultKind = node.getResult() instanceof ArithmeticResult ? NodeResultKind.INTEGER : NodeResultKind.BOOLEAN;
        this.nodeName = node.getNode().getName();
        this.nodeKind = node.getChildren().size() == 0 ? NodeKind.VAR : NodeKind.OP;

        this.stepNum = node.getResult().forStep();
        this.isCause = isCause;
    }

    public boolean isCause() {
        return this.isCause;
    }

    public NodeKind getNodeKind() {
        return this.nodeKind;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public NodeResultKind getResultKind() {
        return this.resultKind;
    }

    public String getResult() {
        return this.result;
    }

    public int getStepNum() {
        return this.stepNum;
    }
}
