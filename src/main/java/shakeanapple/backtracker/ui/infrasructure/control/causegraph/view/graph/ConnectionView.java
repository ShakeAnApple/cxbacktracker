package shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.model.Connection;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.DiagramStyles;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.layout.ConnectionLineInterval;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.layout.LineType;


import java.util.ArrayList;
import java.util.List;


public class ConnectionView extends NodeView {
    private Connection connection;

    private GraphNodeView from;
    private GraphNodeView to;

    private boolean isSelected = false;

    private DoubleBinding startX;
    private DoubleBinding startY;
    private DoubleBinding endX;
    private DoubleBinding endY;

    private Path path;

    public ConnectionView(GraphView parent, Connection connection, GraphNodeView from, GraphNodeView to) {
        super(parent);
        this.connection = connection;

        this.from = from;
        this.to = to;

//        this.endX = this.to.getView().layoutXProperty().add(this.to.getWidth());
        this.endY = this.to.getView().layoutYProperty().add(this.to.getView().heightProperty().divide(2));
        this.endX = new DoubleBinding() {
            {
                super.bind(to.getView().layoutXProperty());
            }

            @Override
            protected double computeValue() {
                return to.getView().layoutXProperty().add(to.getWidth()).get();
            }
        };

        this.startX = new DoubleBinding() {
            {
                super.bind(from.getView().layoutXProperty());
            }

            @Override
            protected double computeValue() {
                return from.getView().layoutXProperty().get();
            }
        };
        this.startY = this.from.getView().layoutYProperty().add(this.from.getView().heightProperty().divide(2));
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

    public GraphNodeView getFrom() {
        return this.from;
    }

    public GraphNodeView getTo() {
        return this.to;
    }

    public void setView(List<ConnectionLineInterval> connectionLines) {
        MoveTo moveTo = new MoveTo();
        moveTo.xProperty().bind(this.startX);
        moveTo.yProperty().bind(this.startY);

        List<LineTo> pathsLines = new ArrayList<>();
        LineTo prevLine = new LineTo();

        prevLine.xProperty().bind(moveTo.xProperty());
        prevLine.yProperty().bind(moveTo.yProperty());
        pathsLines.add(prevLine);
        for (ConnectionLineInterval connInterv: connectionLines){
            LineTo lineTo = new LineTo();

            if (connInterv.getType() == LineType.HORIZONTAL){
                lineTo.xProperty().bind(prevLine.xProperty().subtract(connInterv.getHorizontalInterval().length()));
                lineTo.yProperty().bind(prevLine.yProperty());
            } else{
                lineTo.xProperty().bind(prevLine.xProperty());
                lineTo.yProperty().bind(prevLine.yProperty().subtract(connInterv.getVerticalInterval().length()));
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
        this.path = new Path(els);

        path.setStroke(DiagramStyles.CONNECTION_COLOR_DEFAULT);
        path.setStrokeWidth(DiagramStyles.CONNECTION_STROKE_WIDTH_DEFAULT);

        //for future
//        this.connection.isCauseEdgeProperty().addListener((observable, oldvalue, newvalue) -> {
//                    if (newvalue) {
//                        path.setStroke(DiagramStyles.CONNECTION_COLOR_CAUSE);
//                    } else {
//                        path.setStroke(DiagramStyles.CONNECTION_COLOR_DEFAULT);
//                    }
//                }
//        );

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

    public void isSelected(Boolean selected){
        if (selected){
            path.setStroke(shakeanapple.backtracker.ui.infrasructure.control.diagram.view.DiagramStyles.CONNECTION_COLOR_CAUSE);
        }
        else{
            path.setStroke(shakeanapple.backtracker.ui.infrasructure.control.diagram.view.DiagramStyles.CONNECTION_COLOR_DEFAULT);
        }
    }

    @Override
    public String toString() {
        return (this.from.getName() + this.to.getName());
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ConnectionView && this.toString().equals(obj.toString());
    }
}
