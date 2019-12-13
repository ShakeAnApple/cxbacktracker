package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import shakeanapple.backtracker.common.variable.ValueHolder;

public class Edge extends Group {

    protected Connectable source;
    protected Connectable target;

//    protected Cell source;
//    protected Cell target;

    private Line line;
    private Label labelLeft;
    private Label labelRight;

    private boolean isBound;

    private Color defaultColor;

    public Edge(Connectable source, Connectable target, String textLeft, String textRight, Color color) {

        this.source = source;
        this.target = target;


//        source.addCellChild(target);
//        source.addEdgeFrom(this);
//
//        target.addCellParent(source);
//        target.addEdgeTo(this);

        this.defaultColor = color;
        this.line = new Line();
        this.line.setStroke(color);
        this.line.setStrokeWidth(2);

        this.labelLeft = new Label(textLeft);
        this.labelRight = new Label(textRight);

        super.getChildren().add(this.line);
        super.getChildren().add(this.labelLeft);
        super.getChildren().add(this.labelRight);

//        this.labelLeft.layoutXProperty().bind(this.line.endXProperty().subtract(this.line.endXProperty().subtract(this.line.startXProperty()).divide(2)));
        this.labelLeft.layoutXProperty().bind(this.line.startXProperty().subtract(5));
//        this.labelLeft.layoutYProperty().bind(this.line.endYProperty().subtract(this.line.endYProperty().subtract(this.line.startYProperty()).divide(2)));
        this.labelLeft.layoutYProperty().bind(this.line.startYProperty().subtract(5));

        this.labelRight.layoutXProperty().bind(this.line.endXProperty());
//        this.labelLeft.layoutYProperty().bind(this.line.endYProperty().subtract(this.line.endYProperty().subtract(this.line.startYProperty()).divide(2)));
        this.labelRight.layoutYProperty().bind(this.line.endYProperty().subtract(5));

    }

    public void color(Color color){
        if (color == null){
            this.line.setStroke(this.defaultColor);
        } else {
            this.line.setStroke(color);
        }
    }

//        public Edge(Cell source, Cell target, String text, Color color) {
//
//        this.source = source;
//        this.target = target;
//
//
////        source.addCellChild(target);
////        source.addEdgeFrom(this);
////
////        target.addCellParent(source);
////        target.addEdgeTo(this);
//
//        this.line = new Line();
//        this.line.setStroke(color);
//        this.line.setStrokeWidth(2);
//
//        this.label = new Label(text);
//
//        super.getChildren().add(this.line);
//        super.getChildren().add(this.label);
//
////                    double sourceHeightPos = (this.source.getBoundsInParent().getHeight() / this.source.getOutcomingEdgesCount()) * (this.source.getBoundOutcomingEdgesCount() + 1);
////        double targetHeightPos = (this.target.getBoundsInParent().getHeight() / this.target.getIncomingEdgesCount()) * (this.target.getBoundIncomingEdgesCount() + 1);
////
////        this.line.startXProperty()
////                .bind(source.layoutXProperty().add(source.getBoundsInParent().getWidth()));
////        this.line.startYProperty()
////                .bind(source.layoutYProperty().add(sourceHeightPos));
////
////        this.line.endXProperty()
////                .bind(target.layoutXProperty());
////        this.line.endYProperty()
////                .bind(target.layoutYProperty().add(targetHeightPos));
//
////            this.bindStartX(this.source.layoutXProperty().add(this.source.getBoundsInParent().getWidth()));
////            this.bindStartY(this.source.layoutYProperty().add(this.source.getBoundsInParent().getHeight() / 2));
////            this.bindEndX(this.target.layoutXProperty());
////            this.bindEndY(this.target.layoutYProperty().add(this.target.getBoundsInParent().getHeight() / 2));
//
////            this.bindStartX(this.source.layoutXProperty());
////            this.bindStartY(this.source.layoutYProperty());
////            this.bindEndX(this.target.layoutXProperty());
////            this.bindEndY(this.target.layoutYProperty());
//
//        this.label.layoutXProperty().bind(this.line.endXProperty().subtract(this.line.endXProperty().subtract(this.line.startXProperty()).divide(2)));
//        this.label.layoutYProperty().bind(this.line.endYProperty().subtract(this.line.endYProperty().subtract(this.line.startYProperty()).divide(2)));
//
//    }

//    public Connectable getSource() {
//        return source;
//    }
//
//    public Connectable getTarget() {
//        return target;
//    }

    public void bindStartX(ObservableValue<? extends Number> value){
        this.line.startXProperty()
                .bind(value);
    }

    public void bindStartY(ObservableValue<? extends Number> value){
        this.line.startYProperty()
                .bind(value);
    }

    public void bindEndX(ObservableValue<? extends Number> value){
        this.line.endXProperty()
                .bind(value);
    }

    public void bindEndY(ObservableValue<? extends Number> value){
        this.line.endYProperty()
                .bind(value);
    }

    public void bind(){
//                    this.bindStartX(this.source.layoutXProperty().add(this.source.getBoundsInParent().getWidth()));
//            this.bindStartY(this.source.layoutYProperty().add(this.source.getBoundsInParent().getHeight() / 2));
//            this.bindEndX(this.target.layoutXProperty());
//            this.bindEndY(this.target.layoutYProperty().add(this.target.getBoundsInParent().getHeight() / 2));
//        if (this.isBound) {
//            return;
//        }

//        double sourceHeightPos = (this.source.getBoundsInParent().getHeight() / this.source.getOutcomingEdgesCount()) * (this.source.getBoundOutcomingEdgesCount() + 1);
//        double targetHeightPos = (this.target.getBoundsInParent().getHeight() / this.target.getIncomingEdgesCount()) * (this.target.getBoundIncomingEdgesCount() + 1);
//
//        this.line.startXProperty()
//                .bind(source.layoutXProperty().add(source.getBoundsInParent().getWidth()));
//        this.line.startYProperty()
//                .bind(source.layoutYProperty().add(sourceHeightPos));
//
//        this.line.endXProperty()
//                .bind(target.layoutXProperty());
//        this.line.endYProperty()
//                .bind(target.layoutYProperty().add(targetHeightPos));


//        this.label.layoutXProperty().bind(this.line.endXProperty().subtract(this.line.endXProperty().subtract(this.line.startXProperty()).divide(2)));
//        this.label.layoutYProperty().bind(this.line.endYProperty().subtract(this.line.endYProperty().subtract(this.line.startYProperty()).divide(2)));


        this.isBound = true;
    }

    public boolean isBound() {
        return this.isBound;
    }

    public void updateValue(ValueHolder value) {
        this.labelLeft.setText(value.toString());
    }

    public void updateValue(ValueHolder valueLeft, ValueHolder valueRight) {
        this.labelLeft.setText(valueLeft.toString());
        this.labelRight.setText(valueRight.toString());
    }
}
