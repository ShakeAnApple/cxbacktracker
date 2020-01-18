package shakeanapple.backtracker.ui.infrasructure.control.diagram.view;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.CellView;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.DiagramCellView;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.GraphView;


public class Canvas extends ZoomableScrollPane {

    private GraphView graphNew;
    private MouseGestures mouseGestures;

    /**
     * the pane wrapper is necessary or else the scrollpane would always align
     * the top-most and left-most child to the top and left eg when you drag the
     * top child down, the entire scrollpane would move down
     */
    private Pane cellPane;

    public Canvas(Group content) {
        super();

        this.cellPane = new Pane();
        content.getChildren().add(this.cellPane);

        super.addContent(content);
        super.addTransformContent(this.cellPane);

        this.mouseGestures = new MouseGestures(this);

        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }

    public ScrollPane getScrollPane() {
        return this;
    }

    public GraphView getGraph() {
        return this.graphNew;
    }

    public void update(GraphView graphNew) {

        this.graphNew = graphNew;
        this.cellPane.getChildren().addAll(this.graphNew);

        // enable dragging of cells
        for (DiagramCellView cell : this.graphNew.getNodes()) {
            this.mouseGestures.makeDraggable(cell.getView());
        }
    }

    public double getScale() {
        return this.getScaleValue();
    }

    public void clear() {
        this.graphNew = null;
        this.cellPane.getChildren().clear();
    }
}
