package shakeanapple.backtracker.ui.explainer.model.graph.cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Edge;

public class OutputPin extends Pin {


    public OutputPin(ExplainerCell owner, String name, int order) {
        super(owner, name, order);
    }


    public void isSource(Edge edge) {
        Line line = new Line();
        line.setStroke(Color.GRAY);
        line.setStrokeWidth(2);

        line.startXProperty()
                .bind(this.layoutXProperty().add(this.widthProperty()));
        line.startYProperty()
                .bind(this.layoutYProperty().add(this.heightProperty().divide(2)));

        // TODO grid cell size should be here
        line.endXProperty()
                .bind(this.layoutXProperty().add(this.widthProperty()).add(100));
        line.endYProperty()
                .bind(this.layoutYProperty().add(this.heightProperty().divide(2)));

        this.getOwner().getChildren().add(line);

        edge.bindStartX(this.layoutXProperty().add(this.widthProperty()));
        edge.bindStartY(this.layoutYProperty().add(this.heightProperty().divide(2)));
//        super.getOwner().getChildren().add(line);
    }

    public void bindStartEdge(Edge edge) {
        edge.bindStartX(this.layoutXProperty().add(this.widthProperty()));
        edge.bindStartY(this.layoutYProperty().add(this.heightProperty().divide(2)));
    }
}
