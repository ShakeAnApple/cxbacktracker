package shakeanapple.backtracker.core.diagramexplanation.model.causetree;

import java.util.*;
import java.util.function.Function;

public class ExplanationItem {
    private CausePathTree tree;
    private Collection<CauseNode> freshNodes;

    private Map<String, Function<List<CauseNode>, Boolean>> addChildrenToFreshNodesActions = new HashMap<>();

    public ExplanationItem(CausePathTree tree, Collection<CauseNode> freshNodes) {
        this.tree = tree;
        this.freshNodes = freshNodes;
    }

    public void recordAddChildrenActionForNode(CauseNode node, Function<List<CauseNode>, Boolean> addChildrenMethod) {
        this.addChildrenToFreshNodesActions.put(node.toString(), addChildrenMethod);
    }

    public void addChildrenToNode(CauseNode node, List<CauseNode> children){
        this.addChildrenToFreshNodesActions.get(node.toString()).apply(children);
    }

    public CausePathTree getTree() {
        return this.tree;
    }

    public Collection<CauseNode> getFreshNodes() {
        return this.freshNodes;
    }
}
