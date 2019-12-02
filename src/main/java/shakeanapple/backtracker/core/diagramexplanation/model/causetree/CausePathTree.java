package shakeanapple.backtracker.core.diagramexplanation.model.causetree;

import shakeanapple.backtracker.core.diagramexplanation.model.Gate;

import java.util.ArrayList;
import java.util.List;

public class CausePathTree {
    List<CauseNode> roots = new ArrayList<>();

    public CausePathTree(List<CauseNode> roots) {
        this.roots = roots;
    }

    public CausePathTree(CauseNode root) {
        this.roots = new ArrayList<>(){{add(root);}};
    }

    public CausePathTree() {
    }

    public void addRoot(CauseNode gate){
        this.roots.add(gate);
    }

    public List<CauseNode> getRoots() {
        return this.roots;
    }
}
