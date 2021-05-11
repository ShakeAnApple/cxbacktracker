package shakeanapple.backtracker.ui.infrasructure.control.causegraph.view;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
//import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph.DiagramCellView;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph.GraphNodeView;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph.GraphView;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph.NodeView;


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
        content.setStyle("-fx-background-color:white");

        super.addContent(content);
        super.addTransformContent(this.cellPane);

        this.mouseGestures = new MouseGestures(this);

        this.setFitToWidth(true);
        this.setFitToHeight(true);

        this.setStyle("-fx-background-color:white");

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
        for (GraphNodeView node : this.graphNew.getNodes()) {
            this.mouseGestures.makeDraggable(node.getView());
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
