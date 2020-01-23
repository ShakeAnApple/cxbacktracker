package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Connection;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.DiagramStyles;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.layout.ConnectionLineInterval;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.layout.LineType;

import java.util.ArrayList;
import java.util.List;


public class ConnectionView extends NodeView {
    private Connection connection;

    private PinView from;
    private PinView to;

    private boolean isSelected = false;

    private DoubleBinding startX;
    private DoubleBinding startY;
    private DoubleBinding endX;
    private DoubleBinding endY;

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

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        this.startX = this.from.getView().layoutXProperty().add(this.from.getView().widthProperty());
        this.startY = this.from.getView().layoutYProperty().add(this.from.getView().heightProperty().divide(2));

        this.endX = new DoubleBinding() {
            {
                super.bind(to.getView().layoutXProperty());
            }

            @Override
            protected double computeValue() {
                return to.getView().layoutXProperty().get();
            }
        };
        this.endY = this.to.getView().layoutYProperty().add(this.to.getView().heightProperty().divide(2));

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        MoveTo moveTo = new MoveTo();
//        moveTo.xProperty().bind(this.from.getView().layoutXProperty().add(this.from.getView().widthProperty()));
//        moveTo.yProperty().bind(this.from.getView().layoutYProperty().add(this.from.getView().heightProperty().divide(2)));
//
//        LineTo prefixLine = new LineTo();
//
//        prefixLine.xProperty().bind(this.from.getView().layoutXProperty().add(this.from.getView().widthProperty()).add(10));
//        prefixLine.yProperty().bind(this.from.getView().layoutYProperty().add(this.from.getView().heightProperty().divide(2)));
//
//        LineTo lineTo = new LineTo();
//        lineTo.xProperty().bind(prefixLine.xProperty());
//        lineTo.yProperty().bind(this.to.getView().layoutYProperty().add(this.to.getView().heightProperty().divide(2)));
//
//        LineTo lineTo2 = new LineTo();
//        lineTo2.xProperty().bind(this.to.getView().layoutXProperty());
//        lineTo2.yProperty().bind(lineTo.yProperty());
//
//        Path path = new Path(moveTo, prefixLine, lineTo, lineTo2);
//        path.setStroke(DiagramStyles.CONNECTION_COLOR_DEFAULT);
//        path.setStrokeWidth(DiagramStyles.CONNECTION_STROKE_WIDTH_DEFAULT);
//
//        this.connection.isCauseEdgeProperty().addListener((observable, oldvalue, newvalue) -> {
//                    if (newvalue) {
//                        path.setStroke(DiagramStyles.CONNECTION_COLOR_CAUSE);
//                    } else {
//                        path.setStroke(DiagramStyles.CONNECTION_COLOR_DEFAULT);
//                    }
//                }
//        );
//
//        path.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
//            if (this.isSelected) {
//                path.setStrokeWidth(DiagramStyles.CONNECTION_STROKE_WIDTH_DEFAULT);
//                this.isSelected = false;
//            } else {
//                path.setStrokeWidth(DiagramStyles.CONNECTION_STROKE_WIDTH_SELECTED);
//                this.isSelected = true;
//            }
//        });
//
//        parent.getChildren().add(path);
    }


    public Number getStartX() {
        return startX.get();
    }

    public Number getStartY() {
        return startY.get();
    }

    public Number getEndX() {
        return endX.get();
    }

    public Number getEndY() {
        return endY.get();
    }

    public PinView getFrom() {
        return this.from;
    }

    public PinView getTo() {
        return this.to;
    }

    public void setView(List<ConnectionLineInterval> connectionLines) {
        MoveTo moveTo = new MoveTo();
        moveTo.xProperty().bind(this.startX);
        moveTo.yProperty().bind(this.startY);

        List<LineTo> pathsLines = new ArrayList<>();
        LineTo prevLine = new LineTo();

        prevLine.xProperty().bind(moveTo.xProperty().add(10));
        prevLine.yProperty().bind(moveTo.yProperty());
        pathsLines.add(prevLine);
        for (ConnectionLineInterval connInterv: connectionLines){
            LineTo lineTo = new LineTo();

            if (connInterv.getType() == LineType.HORIZONTAL){
                lineTo.xProperty().bind(prevLine.xProperty().add(connInterv.getHorizontalInterval().length()));
                lineTo.yProperty().bind(prevLine.yProperty());
            } else{
                lineTo.xProperty().bind(prevLine.xProperty());
                lineTo.yProperty().bind(prevLine.yProperty().add(connInterv.getVerticalInterval().length()));
            }

            pathsLines.add(lineTo);
            prevLine = lineTo;
        }

        pathsLines.get(pathsLines.size() - 2).yProperty().unbind();
        pathsLines.get(pathsLines.size() - 2).yProperty().bind(this.endY);

        prevLine.xProperty().unbind();
        prevLine.xProperty().bind(this.endX);
        prevLine.yProperty().unbind();
        prevLine.yProperty().bind(this.endY);


        List<PathElement> els = new ArrayList<>();
        els.add(moveTo);
        els.addAll(pathsLines);
        Path path = new Path(els);

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

        path.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (this.isSelected) {
                path.setStrokeWidth(DiagramStyles.CONNECTION_STROKE_WIDTH_DEFAULT);
                this.isSelected = false;
            } else {
                path.setStrokeWidth(DiagramStyles.CONNECTION_STROKE_WIDTH_SELECTED);
                this.isSelected = true;
            }
        });

        super.getParent().getChildren().add(path);
    }
}
