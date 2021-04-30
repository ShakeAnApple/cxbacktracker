package shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree;

import java.util.Collection;

public class ChangeStayedExplanationItem {
    private ChangedStayedCausePathTree tree;
    private Collection<ChangedStayedCauseNode> freshNodes;
    private boolean isTerminating;

    public ChangeStayedExplanationItem(ChangedStayedCausePathTree tree, Collection<ChangedStayedCauseNode> freshNodes) {
        this.tree = tree;
        this.freshNodes = freshNodes;
    }

    public boolean isTerminating() {
        return this.isTerminating;
    }

    public void isTerminating(boolean isTerminating) {
        this.isTerminating = isTerminating;
    }

    public ChangedStayedCausePathTree getTree() {
        return this.tree;
    }

    public Collection<ChangedStayedCauseNode> getFreshNodes() {
        return this.freshNodes;
    }
}
