package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Connection;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.DiagramStyles;


public class ConnectionView extends NodeView {
    private Connection connection;

    private PinView from;
    private PinView to;

    public ConnectionView(Group parent, Connection connection, PinView from, PinView to) {
        super(parent);
        this.connection = connection;

        this.from = from;
        this.to = to;

        Line line = new Line();

        this.connection.isCauseEdgeProperty().addListener((observable, oldvalue, newvalue) -> {
            if (newvalue) {
                line.setStroke(DiagramStyles.CONNECTION_COLOR_CAUSE);
            } else {
                line.setStroke(DiagramStyles.CONNECTION_COLOR_DEFAULT);
            }
        });

        line.setStroke(DiagramStyles.CONNECTION_COLOR_DEFAULT);
        line.setStrokeWidth(2);

        line.startXProperty().bind(this.from.getView().layoutXProperty().add(this.from.getView().widthProperty()));
        line.startYProperty().bind(this.from.getView().layoutYProperty().add(this.from.getView().heightProperty().divide(2)));

        line.endXProperty().bind(this.to.getView().layoutXProperty());
        line.endYProperty().bind(this.to.getView().layoutYProperty().add(this.from.getView().heightProperty().divide(2)));

        parent.getChildren().add(line);
    }
}
