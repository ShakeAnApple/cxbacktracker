package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph;

import javafx.scene.Group;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.IdGenerator;

public abstract class NodeView {
    private Group parent;
    private long id;

    public NodeView(Group parent){
        this.parent = parent;
//        this.parent.getChildren().add(this);
        id = IdGenerator.next();
    }

    public long getViewId() {
        return id;
    }
}
