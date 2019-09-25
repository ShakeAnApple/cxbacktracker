package shakeanapple.backtracker.ui.control.diagram.model;

import shakeanapple.backtracker.ui.control.diagram.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    private Cell graphParent;

    private List<Cell> allCells;
    private List<Cell> addedCells;
    private List<Cell> removedCells;

    private List<Edge> allEdges;
    private List<Edge> addedEdges;
    private List<Edge> removedEdges;

    private Map<Long, Cell> cellMap; // <id,cell>


    public Model() {

        graphParent = new Cell(0);

        // clear model, create lists
        clear();
    }

    public void clear() {

        allCells = new ArrayList<>();
        addedCells = new ArrayList<>();
        removedCells = new ArrayList<>();

        allEdges = new ArrayList<>();
        addedEdges = new ArrayList<>();
        removedEdges = new ArrayList<>();

        cellMap = new HashMap<>(); // <id,cell>

    }

    public void clearAddedLists() {
        addedCells.clear();
        addedEdges.clear();
    }

    public List<Cell> getAddedCells() {
        return addedCells;
    }

    public List<Cell> getRemovedCells() {
        return removedCells;
    }

    public List<Cell> getAllCells() {
        return allCells;
    }

    public List<Edge> getAddedEdges() {
        return addedEdges;
    }

    public List<Edge> getRemovedEdges() {
        return removedEdges;
    }

    public List<Edge> getAllEdges() {
        return allEdges;
    }

    public void addCell(long id, String name,  CellType type) {

        switch (type) {

            case RECTANGLE:
                RectangleCell rectangleCell = new RectangleCell(id, name);
                addCell(rectangleCell);
                break;

            default:
                throw new UnsupportedOperationException("Unsupported type: " + type);
        }
    }

    public void addCell(Cell cell) {
        if (this.allCells.stream().anyMatch(c -> c.getCellId() == cell.getCellId())){
            return;
        }

        addedCells.add(cell);

        cellMap.put(cell.getCellId(), cell);
    }

    public void addEdge(Connection conn) {

        Cell sourceCell = cellMap.get(conn.getFromId());
        Cell targetCell = cellMap.get(conn.getToId());

        Edge edge = new Edge(sourceCell, targetCell, conn.getLabel(), conn.getColor());
        this.addedEdges.add(edge);
    }

    public void bindEdges(){
        for (Edge edge: this.addedEdges){
            edge.bind();
        }
    }


    /**
     * Attach all cells which don't have a parent to graphParent
     *
     * @param cellList
     */
    public void attachOrphansToGraphParent(List<Cell> cellList) {

        for (Cell cell : cellList) {
            if (cell.getCellParents().size() == 0) {
                graphParent.addCellChild(cell);
            }
        }

    }

    /**
     * Remove the graphParent reference if it is set
     *
     * @param cellList
     */
    public void disconnectFromGraphParent(List<Cell> cellList) {

        for (Cell cell : cellList) {
            graphParent.removeCellChild(cell);
        }
    }

    public void merge() {

        // cells
        allCells.addAll(addedCells);
        allCells.removeAll(removedCells);

        addedCells.clear();
        removedCells.clear();

        // edges
        allEdges.addAll(addedEdges);
        allEdges.removeAll(removedEdges);

        addedEdges.clear();
        removedEdges.clear();

    }
}
