package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Connection;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.DiagramStyles;


public class ConnectionView extends NodeView {
    private Connection connection;

    private PinView from;
    private PinView to;

    private boolean isSelected = false;

    public ConnectionView(Group parent, Connection connection, PinView from, PinView to) {
        super(parent);
        this.connection = connection;

        this.from = from;
        this.to = to;

//        Line line = new Line();
//
//        this.connection.isCauseEdgeProperty().addListener((observable, oldvalue, newvalue) -> {
//            if (newvalue) {
//                line.setStroke(DiagramStyles.CONNECTION_COLOR_CAUSE);
//            } else {
//                line.setStroke(DiagramStyles.CONNECTION_COLOR_DEFAULT);
//            }
//        });
//
//        line.setStroke(DiagramStyles.CONNECTION_COLOR_DEFAULT);
//        line.setStrokeWidth(2);
//
//        line.startXProperty().bind(this.from.getView().layoutXProperty().add(this.from.getView().widthProperty()));
//        line.startYProperty().bind(this.from.getView().layoutYProperty().add(this.from.getView().heightProperty().divide(2)));
//
//        line.endXProperty().bind(this.to.getView().layoutXProperty());
//        line.endYProperty().bind(this.to.getView().layoutYProperty().add(this.from.getView().heightProperty().divide(2)));
//
//        parent.getChildren().add(line);

        MoveTo moveTo = new MoveTo();
        moveTo.xProperty().bind(this.from.getView().layoutXProperty().add(this.from.getView().widthProperty()));
        moveTo.yProperty().bind(this.from.getView().layoutYProperty().add(this.from.getView().heightProperty().divide(2)));

        LineTo prefixLine = new LineTo();

        prefixLine.xProperty().bind(this.from.getView().layoutXProperty().add(this.from.getView().widthProperty()).add(10));
        prefixLine.yProperty().bind(this.from.getView().layoutYProperty().add(this.from.getView().heightProperty().divide(2)));

        LineTo lineTo = new LineTo();
        lineTo.xProperty().bind(prefixLine.xProperty());
        lineTo.yProperty().bind(this.to.getView().layoutYProperty().add(this.to.getView().heightProperty().divide(2)));

        LineTo lineTo2 = new LineTo();
        lineTo2.xProperty().bind(this.to.getView().layoutXProperty());
        lineTo2.yProperty().bind(lineTo.yProperty());

        Path path = new Path(moveTo, prefixLine, lineTo, lineTo2);
        path.setStroke(DiagramStyles.CONNECTION_COLOR_DEFAULT);
        path.setStrokeWidth(DiagramStyles.CONNECTION_STROKE_WIDTH_DEFAULT);

        this.connection.isCauseEdgeProperty().addListener((observable, oldvalue, newvalue) -> {
                    if (newvalue) {
                        path.setStroke(DiagramStyles.CONNECTION_COLOR_CAUSE);
                    } else {
                        path.setStroke(DiagramStyles.CONNECTION_COLOR_DEFAULT);
                    }
                }
        );

        path.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) ->{
            if (this.isSelected){
                path.setStrokeWidth(DiagramStyles.CONNECTION_STROKE_WIDTH_DEFAULT);
                this.isSelected = false;
            } else{
                path.setStrokeWidth(DiagramStyles.CONNECTION_STROKE_WIDTH_SELECTED);
                this.isSelected = true;
            }
        });

        parent.getChildren().add(path);
    }
}
