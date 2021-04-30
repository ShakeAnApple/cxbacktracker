package shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree;

import java.util.ArrayList;
import java.util.List;

public class ChangedStayedCausePathTree {
    List<ChangedStayedCauseNode> roots = new ArrayList<>();

    public ChangedStayedCausePathTree(List<ChangedStayedCauseNode> roots) {
        this.roots = roots;
    }

    public ChangedStayedCausePathTree(ChangedStayedCauseNode root) {
        this.roots = new ArrayList<>(){{add(root);}};
    }

    public ChangedStayedCausePathTree() {
    }

    public void addRoot(ChangedStayedCauseNode gate){
        this.roots.add(gate);
    }

    public List<ChangedStayedCauseNode> getRoots() {
        return this.roots;
    }
}
