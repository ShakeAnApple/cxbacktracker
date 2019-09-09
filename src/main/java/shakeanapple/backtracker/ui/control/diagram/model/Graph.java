package shakeanapple.backtracker.ui.control.diagram.model;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class Graph {

    private Model model;

    private Group canvas;

    private ZoomableScrollPane scrollPane;

    MouseGestures mouseGestures;

    /**
     * the pane wrapper is necessary or else the scrollpane would always align
     * the top-most and left-most child to the top and left eg when you drag the
     * top child down, the entire scrollpane would move down
     */
    CellLayer cellLayer;

    public Graph() {

        this.model = new Model();

        this.canvas = new Group();
        this.cellLayer = new CellLayer();

        this.canvas.getChildren().add(this.cellLayer);

        this.mouseGestures = new MouseGestures(this);

        this.scrollPane = new ZoomableScrollPane(canvas);

        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setFitToHeight(true);

    }

    public ScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public Pane getCellLayer() {
        return this.cellLayer;
    }

    public Model getModel() {
        return this.model;
    }

//    public void beginUpdate() {
//    }

    public void update() {
        this.model.bindEdges();

        // add components to graph pane
        getCellLayer().getChildren().addAll(this.model.getAddedEdges());
        getCellLayer().getChildren().addAll(this.model.getAddedCells());

        // remove components from graph pane
        getCellLayer().getChildren().removeAll(this.model.getRemovedCells());
        getCellLayer().getChildren().removeAll(this.model.getRemovedEdges());

        // enable dragging of cells
        for (Cell cell : this.model.getAddedCells()) {
            this.mouseGestures.makeDraggable(cell);
        }

        // every cell must have a parent, if it doesn't, then the graphParent is
        // the parent
        getModel().attachOrphansToGraphParent(this.model.getAddedCells());

        // remove reference to graphParent
        getModel().disconnectFromGraphParent(this.model.getRemovedCells());

        // merge added & removed cells with all cells
        getModel().merge();

    }

    public double getScale() {
        return this.scrollPane.getScaleValue();
    }
}
