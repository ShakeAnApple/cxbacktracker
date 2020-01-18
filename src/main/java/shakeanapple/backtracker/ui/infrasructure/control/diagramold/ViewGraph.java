package shakeanapple.backtracker.ui.infrasructure.control.diagramold;

import shakeanapple.backtracker.ui.infrasructure.control.diagramold.model.Cell;
import shakeanapple.backtracker.ui.infrasructure.control.diagramold.model.DiagramConnection;

import java.util.ArrayList;
import java.util.List;

public class ViewGraph {
    private final List<Cell> cells;
    private final List<DiagramConnection> connections;

    public ViewGraph(){
        this.cells = new ArrayList<>();
        this.connections = new ArrayList<>();
    }

    public ViewGraph(List<Cell> cells, List<DiagramConnection> connections) {
        this.cells = cells;
        this.connections = connections;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public List<DiagramConnection> getConnections() {
        return connections;
    }

    public void addCell(Cell cell){
        this.cells.add(cell);
    }
}
