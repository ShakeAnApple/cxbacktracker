package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import shakeanapple.backtracker.ui.explainer.view.Grid;
import shakeanapple.backtracker.ui.explainer.view.Offset;

public class Panel {

    private Graph graph;
    private Grid grid;

    private ZoomableScrollPane scrollPane;

    private MouseGestures mouseGestures;

    /**
     * the pane wrapper is necessary or else the scrollpane would always align
     * the top-most and left-most child to the top and left eg when you drag the
     * top child down, the entire scrollpane would move down
     */
    private Pane cellLayer;

    public Panel() {

        this.graph = new Graph();

        Group canvas = new Group();
        this.cellLayer = new Pane();

        canvas.getChildren().add(this.cellLayer);
        this.scrollPane = new ZoomableScrollPane(canvas);

        this.mouseGestures = new MouseGestures(this);

        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setFitToHeight(true);

        this.grid = new Grid(10);
    }

    public ScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public Graph getGraph() {
        return this.graph;
    }

    public Offset adjustCoords(Node node){
        return this.grid.adjustCoords(node, this.scrollPane.getScaleValue());
    }

    public void allocateNode(Node node){
        this.grid.placeNode(node);
    }

    public void removeNodeFromGrid(Node node){
        this.grid.removeNode(node);
    }

    public double getWidth(){
        return this.scrollPane.getWidth();
    }

    public double getHeight(){
        return this.scrollPane.getHeight();
    }


//    public void beginUpdate() {
//    }

    public void update() {
        this.graph.bindEdges();

        // add components to graph pane
        this.cellLayer.getChildren().addAll(this.graph.getAddedCells());
        this.cellLayer.getChildren().addAll(this.graph.getAddedEdges());

        // remove components from graph pane
        this.cellLayer.getChildren().removeAll(this.graph.getRemovedCells());
        this.cellLayer.getChildren().removeAll(this.graph.getRemovedEdges());

        // enable dragging of cells
        for (Cell cell : this.graph.getAddedCells()) {
            this.mouseGestures.makeDraggable(cell);
        }

        // every cell must have a parent, if it doesn't, then the graphParent is
        // the parent
        this.getGraph().attachOrphansToGraphParent(this.graph.getAddedCells());

        // remove reference to graphParent
        this.getGraph().disconnectFromGraphParent(this.graph.getRemovedCells());

        // merge added & removed cells with all cells
        this.getGraph().merge();

    }

    public double getScale() {
        return this.scrollPane.getScaleValue();
    }

    public void clear() {
        this.graph.clear();
        this.cellLayer.getChildren().clear();
    }
}
