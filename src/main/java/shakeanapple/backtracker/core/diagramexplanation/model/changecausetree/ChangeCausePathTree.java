package shakeanapple.backtracker.core.diagramexplanation.model.changecausetree;

import java.util.ArrayList;
import java.util.List;

public class ChangeCausePathTree {
    List<ChangeCauseNode> roots = new ArrayList<>();

    public ChangeCausePathTree(List<ChangeCauseNode> roots) {
        this.roots = roots;
    }

    public ChangeCausePathTree(ChangeCauseNode root) {
        this.roots = new ArrayList<>(){{add(root);}};
    }

    public ChangeCausePathTree() {
    }

    public void addRoot(ChangeCauseNode gate){
        this.roots.add(gate);
    }

    public List<ChangeCauseNode> getRoots() {
        return this.roots;
    }
}
