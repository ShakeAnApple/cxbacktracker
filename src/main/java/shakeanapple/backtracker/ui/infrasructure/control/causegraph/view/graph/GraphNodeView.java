package shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph;

import javafx.scene.Node;

import java.util.List;

public interface GraphNodeView {
    Node getView();

    List<GraphNodeView> getChildren();

    double getWidth();

    String getName();

    double getHeight();

    void addChild(GraphNodeView child);
}
