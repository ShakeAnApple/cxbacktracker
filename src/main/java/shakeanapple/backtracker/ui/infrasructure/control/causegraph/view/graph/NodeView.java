package shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph;

import javafx.scene.Group;
import javafx.scene.Node;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.IdGenerator;

public abstract class NodeView {
    private Group parent;
    private long id;

    public NodeView(Group parent){
        this.parent = parent;
        id = IdGenerator.next();
    }

    public long getViewId() {
        return id;
    }

    public Group getParent() {
        return this.parent;
    }

}
