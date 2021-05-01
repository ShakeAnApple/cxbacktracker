package shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph;

import shakeanapple.backtracker.core.diagramexplanation.model.Gate;

import java.util.*;

public class CausePathFinalGraph {
    private Map<String, CauseFinalNode> leaves;
    private Map<String, CauseFinalNode> nodes;

    public CausePathFinalGraph() {
        this.leaves = new HashMap<>();
        this.nodes = new HashMap<>();
    }

    public CauseFinalNode getRoot() {
        return this.nodes.values().stream().filter(CauseFinalNode::isRoot).findFirst().orElse(null);
    }

    public void addNode(CauseFinalNode causeNode) {
        this.nodes.put(causeNode.toString(), causeNode);
        this.leaves.put(causeNode.toString(), causeNode);
    }

    void removeLeaf(CauseFinalNode causeNode) {
        this.leaves.remove(causeNode.toString());
    }

    public Collection<CauseFinalNode> getLeaves() {
        return this.leaves.values();
    }

    public List<CauseFinalNode> getNodes() {
        return new ArrayList<>(this.nodes.values());
    }

    public CauseFinalNode causeNodeFor(Gate gate, int timestamp, boolean isRoot) {
        return this.nodes.getOrDefault(gate.getFullName() + (isRoot ? "(root)" : "") +  ":" + timestamp + ":" + gate.getOwner().history().getVariableValueForStep(gate.getName(), timestamp), null);
    }

    public void addLeaf(CauseFinalNode node) {
        this.leaves.put(node.toString(), node);
    }
}
