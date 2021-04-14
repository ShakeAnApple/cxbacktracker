package shakeanapple.backtracker.core.diagramexplanation.model.changecausetree;

import java.util.Collection;

public class ChangeExplanationItem {
    private ChangeCausePathTree tree;
    private Collection<ChangeCauseNode> freshNodes;
    private boolean isTerminating;

    public ChangeExplanationItem(ChangeCausePathTree tree, Collection<ChangeCauseNode> freshNodes) {
        this.tree = tree;
        this.freshNodes = freshNodes;
    }

    public boolean isTerminating() {
        return this.isTerminating;
    }

    public void isTerminating(boolean isTerminating) {
        this.isTerminating = isTerminating;
    }

    public ChangeCausePathTree getTree() {
        return this.tree;
    }

    public Collection<ChangeCauseNode> getFreshNodes() {
        return this.freshNodes;
    }
}
