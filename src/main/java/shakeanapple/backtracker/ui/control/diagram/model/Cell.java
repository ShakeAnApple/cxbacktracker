package shakeanapple.backtracker.ui.control.diagram.model;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Cell extends Pane {

    private long cellId;

    private List<Cell> children = new ArrayList<>();
    private List<Cell> parents = new ArrayList<>();

    private List<Edge> outcomingEdges = new ArrayList<>();
    private List<Edge> incomingEdges = new ArrayList<>();

    private Node view;

    public Cell(long cellId) {
        this.cellId = cellId;
    }

    public void addCellChild(Cell cell) {
        children.add(cell);
    }

    public List<Cell> getCellChildren() {
        return children;
    }

    public void addCellParent(Cell cell) {
        parents.add(cell);
    }

    public List<Cell> getCellParents() {
        return parents;
    }

    public void removeCellChild(Cell cell) {
        children.remove(cell);
    }

    public void setView(Node view, Label label) {

        this.view = view;
        super.getChildren().add(view);
        super.getChildren().add(label);

    }

    public void setView(Node view, List<Node> children) {
        this.view = view;
        super.getChildren().add(view);
        for(Node ch: children) {
            super.getChildren().add(ch  );
        }
    }

    public Node getView() {
        return this.view;
    }

    public long getCellId() {
        return cellId;
    }

    public int getChildrenCount(){
        return this.children.size();
    }

    public void addEdgeFrom(Edge edge) {
        this.outcomingEdges.add(edge);
    }

    public void addEdgeTo(Edge edge) {
        this.incomingEdges.add(edge);
    }

    public long getBoundOutcomingEdgesCount(){
        return this.outcomingEdges.stream().filter(e -> e.isBound()).count();
    }

    public long getBoundIncomingEdgesCount(){
        return this.incomingEdges.stream().filter(e -> e.isBound()).count();
    }

    public int getOutcomingEdgesCount(){
        return this.outcomingEdges.size();
    }

    public int getIncomingEdgesCount(){
        return this.incomingEdges.size();
    }
}
