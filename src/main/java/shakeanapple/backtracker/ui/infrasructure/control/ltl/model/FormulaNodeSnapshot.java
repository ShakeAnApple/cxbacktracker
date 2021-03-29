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

    private int groupId = -1;


    public FormulaNodeSnapshot(ICalculatedNode node, boolean isCause) {
        this(node);
        this.isCause = isCause;
    }

    public FormulaNodeSnapshot(ICalculatedNode node) {
        this.result = node.getResult().toString();
        this.resultKind = node.getResult() instanceof ArithmeticResult ? NodeResultKind.INTEGER : NodeResultKind.BOOLEAN;
        this.nodeName = node.getNode().getName();
        this.nodeKind = node.getChildren().size() == 0 ? NodeKind.VAR : NodeKind.OP;

        this.stepNum = node.getResult().forStep();
        this.groupId = node.getNode().getId();
    }

    public FormulaNodeSnapshot(String parenthesis, ICalculatedNode node){
        this.result = node.getResult().toString();
        this.resultKind = node.getResult() instanceof ArithmeticResult ? NodeResultKind.INTEGER : NodeResultKind.BOOLEAN;
        this.nodeName = parenthesis;
        this.nodeKind = node.getChildren().size() == 0 ? NodeKind.VAR : NodeKind.OP;

        this.stepNum = node.getResult().forStep();
        this.groupId = node.getNode().getId();
    }

    public boolean isCause() {
        return this.isCause;
    }

    public void isCause(boolean isCause) {
        this.isCause = isCause;
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

    public int getGroupId() {
        return this.groupId;
    }
}
