package shakeanapple.backtracker.core.diagramexplanation.model.causetree;

import java.util.*;
import java.util.function.Function;

public class ExplanationItem {
    private CausePathTree tree;
    private Collection<CauseNode> freshNodes;

    public ExplanationItem(CausePathTree tree, Collection<CauseNode> freshNodes) {
        this.tree = tree;
        this.freshNodes = freshNodes;
    }

    public CausePathTree getTree() {
        return this.tree;
    }

    public Collection<CauseNode> getFreshNodes() {
        return this.freshNodes;
    }
}
