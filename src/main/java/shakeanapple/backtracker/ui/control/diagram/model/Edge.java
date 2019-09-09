package shakeanapple.backtracker.ui.control.diagram.model;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge extends Group {

    protected Cell source;
    protected Cell target;

    private Line line;
    private Label label;

    private boolean isBound;

    public Edge(Cell source, Cell target, String text, Color color) {

        this.source = source;
        this.target = target;

        source.addCellChild(target);
        source.addEdgeFrom(this);

        target.addCellParent(source);
        target.addEdgeTo(this);

        this.line = new Line();
        this.line.setStroke(color);
        this.line.setStrokeWidth(2);

        this.label = new Label(text);

        getChildren().add(line);
        getChildren().add(label);

    }

    public Cell getSource() {
        return source;
    }

    public Cell getTarget() {
        return target;
    }

    public void bind(){
        if (this.isBound){
            return;
        }

        double sourceHeightPos = (this.source.getBoundsInParent().getHeight() / this.source.getOutcomingEdgesCount()) * (this.source.getBoundOutcomingEdgesCount() + 1);
        double targetHeightPos = (this.target.getBoundsInParent().getHeight() / this.target.getIncomingEdgesCount()) * (this.target.getBoundIncomingEdgesCount() + 1);

        this.line.startXProperty()
                .bind(source.layoutXProperty().add(source.getBoundsInParent().getWidth()));
        this.line.startYProperty()
                .bind(source.layoutYProperty().add(sourceHeightPos));

        this.line.endXProperty()
                .bind(target.layoutXProperty());
        this.line.endYProperty()
                .bind(target.layoutYProperty().add(targetHeightPos));


        this.label.layoutXProperty().bind(this.line.endXProperty().subtract(this.line.endXProperty().subtract(this.line.startXProperty()).divide(2)));
        this.label.layoutYProperty().bind(this.line.endYProperty().subtract(this.line.endYProperty().subtract(this.line.startYProperty()).divide(2)));


        this.isBound = true;
    }

    public boolean isBound() {
        return this.isBound;
    }
}
