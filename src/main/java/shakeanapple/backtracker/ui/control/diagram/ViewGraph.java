package shakeanapple.backtracker.ui.control.diagram;

import shakeanapple.backtracker.ui.control.diagram.model.Cell;

import java.util.List;

public class ViewGraph {
    private final List<Cell> cells;
    private final List<Connection> connections;


    public ViewGraph(List<Cell> cells, List<Connection> connections) {
        this.cells = cells;
        this.connections = connections;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public List<Connection> getConnections() {
        return connections;
    }
}
