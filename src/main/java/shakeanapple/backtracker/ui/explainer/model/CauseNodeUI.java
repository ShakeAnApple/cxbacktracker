package shakeanapple.backtracker.ui.explainer.model;

import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;

import java.util.ArrayList;
import java.util.List;

public class CauseNodeUI {
    private Cause cause;
    private List<CauseNodeUI> children;

    public CauseNodeUI(Cause cause, List<CauseNodeUI> children) {
        this.cause = cause;
        this.children = children;
    }

    public Cause getCause() {
        return this.cause;
    }

    public List<CauseNodeUI> getChildren() {
        return this.children;
    }

    public static CauseNodeUI parse(CauseNode causeNode){
        Cause cause = new Cause(causeNode.getTimestamp(), causeNode.getGate().getName(),
                causeNode.getGate().getOwner().getName(), causeNode.getValue());

        List<CauseNodeUI> children = new ArrayList<>();
        for (CauseNode child: causeNode.getChildren()) {
            children.add(CauseNodeUI.parse(child));
        }

        return new CauseNodeUI(cause, children);
    }

    private String constructConnectionId(String fromBlock, String fromVarName, String toBlock, String  toVarName){
        return (fromBlock == null || fromBlock.equals("root") ? "" : fromBlock) + fromVarName + (toBlock == null || toBlock.equals("root") ? "" : toBlock) + toVarName;
    }

    public List<String> inferConnectionsIds() {
        List<String> res = new ArrayList<>();
        for (CauseNodeUI child: this.children) {
            String connId =
                    this.constructConnectionId(child.cause.getBlockName(), child.cause.getVarName(), this.cause.getBlockName(), this.cause.getVarName());
            res.add(connId);
            res.addAll(child.inferConnectionsIds());
        }
        return res;
    }
}
