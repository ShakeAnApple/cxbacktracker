package shakeanapple.backtracker.ui.explainer.model.graph;

import javafx.scene.paint.Color;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.ui.explainer.model.graph.cell.InputPin;
import shakeanapple.backtracker.ui.explainer.model.graph.cell.OutputPin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.DiagramConnection;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Edge;

public class Connection implements DiagramConnection {
    private final String id;
    private final OutputPin from;
    private final InputPin to;
    private ValueHolder value;

    private boolean isCause;

    private boolean isInverted;

    private Edge edge;

    public Connection(OutputPin from, InputPin to, ValueHolder value, boolean isInverted) {
        this.from = from;
        this.to = to;
        this.isInverted = isInverted;

        if (this.isInverted) {
            this.to.invert();
            // TODO don't remember should I?
           // this.value = value.invert();
        }
        this.value = value;

        this.to.isTarget();

        String id = this.from.getOwner().getName();
        if (!this.from.getOwner().getName().equals(this.from.getName())){
            id += this.from.getName();
        }
        id += this.to.getOwner().getName();
        if (!this.to.getOwner().getName().equals(this.to.getName())){
            id += this.to.getName();
        }
        this.id = id;
    }

    public OutputPin getFrom() {
        return this.from;
    }

    public InputPin getTo() {
        return this.to;
    }

    public ValueHolder getValue() {
        return this.value;
    }

    public boolean isInverted() {
        return this.isInverted;
    }

    public void updateValue(ValueHolder value) {
        this.value = value;

        if (this.isInverted) {
            this.edge.updateValue(this.value, this.value.invert());
//            this.value = value.invert();
        } else {
            this.edge.updateValue(this.value, this.value);
//            this.value = value;
        }
//        this.edge.updateValue(this.value);
    }

    @Override
    public void isCauseEdge(boolean isCause) {
        this.isCause = isCause;
        if (this.isCause) {
            this.edge.color(Color.RED);
        } else{
            this.edge.color(null);
        }
    }

    public String getId() {
        return this.id;
    }

    //TODO change color (split connection into boolean and integer)
    @Override
    public Edge inferEdge() {
//        Edge edge = new Edge(this.from.getOwner(), this.to.getOwner(), this.value.toString(), Color.GRAY);
        this.edge = new Edge(this.from, this.to, this.value.toString(), this.isInverted ? this.value.invert().toString() : this.value.toString(), Color.GRAY);
        this.from.getOwner().addCellChild(this.to.getOwner());
        this.from.getOwner().addEdgeFrom(edge);

        this.to.getOwner().addCellParent(this.from.getOwner());
        this.to.getOwner().addEdgeTo(edge);

//        this.edge.bindStartX(this.from.getOwner().layoutXProperty().add(this.from.getOwner().getBoundsInParent().getWidth()));

//        this.edge.bindStartX(this.from.getOwner().layoutXProperty().add(this.from.getOwner().widthProperty()).subtract(this.from.getMinWidth()));
//        this.edge.bindStartY(this.from.getOwner().layoutYProperty().add(this.from.getMinHeight() / 2 + this.from.getMinHeight() * this.from.getOrder()));

        this.edge.bindStartX(this.from.getOwner().layoutXProperty().add(this.from.layoutXProperty()).add(this.from.widthProperty()));
        this.edge.bindStartY(this.from.getOwner().layoutYProperty().add(this.from.layoutYProperty()).add(this.from.heightProperty().divide(2)));
//        this.edge.bindStartX(this.from.getOwner().widthProperty());


//        this.edge.bindEndX(this.to.getOwner().layoutXProperty().subtract(this.from.getMinWidth()));
//        this.edge.bindEndY(this.to.getOwner().layoutYProperty().add(this.to.getMinHeight() / 2 + this.to.getMinHeight() * this.to.getOrder()));

        this.edge.bindEndX(this.to.getOwner().layoutXProperty().add(this.to.getLayoutX()));
        this.edge.bindEndY(this.to.getOwner().layoutYProperty().add(this.to.getLayoutY()).add(this.to.heightProperty().divide(2)));

//        edge.bindStartX(this.from.layoutXProperty().add(this.from.getWidth()));
//        edge.bindStartY(this.from.layoutYProperty().add(this.from.getHeight() / 2));
//        edge.bindEndX(this.to.layoutXProperty());
//        edge.bindEndY(this.to.layoutYProperty().add(this.to.getHeight() / 2));
        return this.edge;
    }
}
