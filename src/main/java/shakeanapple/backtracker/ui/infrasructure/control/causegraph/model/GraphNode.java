package shakeanapple.backtracker.ui.infrasructure.control.causegraph.model;

import java.util.List;

public interface GraphNode {
    String getLabel();
    String getGateFullName();
    List<GraphNode> getChildren();
    void addChild(GraphNode child);
    boolean isRoot();
}
