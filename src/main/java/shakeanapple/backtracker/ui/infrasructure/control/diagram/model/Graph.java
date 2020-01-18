package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import java.util.List;

public class Graph {
    private List<DiagramCell> cells;
    private List<Connection> connections;

    public Graph(List<DiagramCell> cells, List<Connection> connections) {
        this.cells = cells;
        this.connections = connections;
    }

    public List<DiagramCell> getCells() {
        return cells;
    }

    public List<Connection> getConnections() {
        return connections;
    }
}
