package shakeanapple.backtracker.ui.infrasructure.control.causegraph.model;

import java.util.List;

public class CauseGraph {
    private List<GraphNode> cells;
    private List<Connection> connections;
    private GraphNode root;

    public CauseGraph(List<GraphNode> cells, List<Connection> connections, GraphNode root) {
        this.cells = cells;
        this.connections = connections;
        this.root = root;
    }

    public List<GraphNode> getCells() {
        return cells;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public GraphNode getRoot() {
        return this.root;
    }
}
