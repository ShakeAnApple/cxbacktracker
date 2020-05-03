package shakeanapple.backtracker.ui.explainer.control.diagramexplainer.model;

import shakeanapple.backtracker.core.diagramexplanation.model.causetree.CauseNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public String constructConnectionId(String fromBlock, String fromVarName, String toBlock, String  toVarName){
        return (fromBlock == null || fromBlock.equals("root") ? "root" : fromBlock) + fromVarName + (toBlock == null || toBlock.equals("root") ? "root" : toBlock) + toVarName;
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

    public Map<String, Map<String, List<Cause>>> inferConnectionCauses() {
        return this.inferConnectionCausesImpl(new HashMap<>());
    }

    // TODO can be more lightweight
    private Map<String, Map<String, List<Cause>>> inferConnectionCausesImpl(Map<String, Map<String, List<Cause>>> res){
        for (CauseNodeUI child: this.children) {
            String connId =
                    this.constructConnectionId(child.cause.getBlockName(), child.cause.getVarName(), this.cause.getBlockName(), this.cause.getVarName());
            if (!res.containsKey(connId)){
                res.put(connId, new HashMap<>());
            }
            if (!res.get(connId).containsKey(this.cause.getBlockName() + this.cause.getVarName())){
                res.get(connId).put(this.cause.getBlockName() + this.cause.getVarName(), new ArrayList<>());
            }
            res.get(connId).get(this.cause.getBlockName() + this.cause.getVarName()).add(this.cause);

            if (!res.get(connId).containsKey(child.cause.getBlockName() + child.cause.getVarName())) {
                res.get(connId).put(child.cause.getBlockName() + child.cause.getVarName(), new ArrayList<>());
            }
            res.get(connId).get(child.cause.getBlockName() + child.cause.getVarName()).add(child.cause);

            child.inferConnectionCausesImpl(res);
        }
        return res;
    }

    public Map<String, List<Cause>> getCausesForPins(){
        Map<String, List<Cause>> pins = new HashMap<>();
        pins.put(this.cause.getBlockName() + this.cause.getVarName(), new ArrayList<>(){{add(cause);}});
        for (CauseNodeUI child: this.children){
            Map<String, List<Cause>> childPins = child.getCausesForPins();
            for (String pinName: childPins.keySet()){
                if (pins.containsKey(pinName)){
                    pins.get(pinName).addAll(childPins.get(pinName));
                } else{
                    pins.put(pinName, childPins.get(pinName));
                }
            }
        }
        return pins;
    }

    public Map<String, List<String>> getCausePins(){
        Map<String, List<String>> pins = new HashMap<>();
        pins.put(this.cause.getBlockName(), new ArrayList<>(){{add(cause.getVarName());}});
        for (CauseNodeUI child: this.children){
            Map<String, List<String>> childPins = child.getCausePins();
            for (String blockName: childPins.keySet()){
                if (pins.containsKey(blockName)){
                    pins.get(blockName).addAll(childPins.get(blockName));
                } else{
                    pins.put(blockName, childPins.get(blockName));
                }
            }
        }
        return pins;
    }

    public Map<String, List<Cause>> getCausesByBlocks() {
        Map<String, List<Cause>> causes = new HashMap<>();
        causes.put(this.cause.getBlockName(), new ArrayList<>(){{add(cause);}});
        for (CauseNodeUI child: this.children){
            Map<String, List<Cause>> childPins = child.getCausesByBlocks();
            for (String blockName: childPins.keySet()){
                if (causes.containsKey(blockName)){
                    causes.get(blockName).addAll(childPins.get(blockName));
                } else{
                    causes.put(blockName, childPins.get(blockName));
                }
            }
        }
        return causes;
    }
}
