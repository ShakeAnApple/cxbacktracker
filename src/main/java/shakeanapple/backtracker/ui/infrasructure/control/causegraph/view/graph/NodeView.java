package shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph;

import javafx.scene.Group;
import javafx.scene.Node;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.IdGenerator;

public abstract class NodeView {
    private GraphView parent;
    private long id;

    public NodeView(GraphView parent){
        this.parent = parent;
        id = IdGenerator.next();
    }

    public long getViewId() {
        return id;
    }

    public GraphView getParent() {
        return this.parent;
    }

}
