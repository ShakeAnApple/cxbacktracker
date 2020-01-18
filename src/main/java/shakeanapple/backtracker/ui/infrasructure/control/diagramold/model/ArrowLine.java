package shakeanapple.backtracker.ui.infrasructure.control.diagramold.model;

import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class ArrowLine extends Group {

    Polygon triangle;
    Line line;

    ArrowLine() {
        this.line = new Line();
        triangle = new Polygon();


        super.getChildren().add(line);
    }

    public Line getLine(){
        return this.line;
    }

    public void updateArrow(){
        double dx = line.getEndX() - line.getStartX();
        double dy = line.getEndY() - line.getStartY();
        double angle = Math.atan2(dy, dx);
        triangle = new Polygon(line.getEndX(), line.getEndY(), line.getEndX() - 8, line.getEndY() + 4, line.getEndX() - 8, line.getEndY() - 4);
        triangle.setRotate(Math.toDegrees(angle));
        triangle.rotateProperty().bind(Bindings.createDoubleBinding(() -> {
            double x = line.getEndX() - line.getStartX();
            double y = line.getEndY() - line.getStartY();
            double a = Math.atan2(y, x);
            return Math.toDegrees(a);
        }));
        super.getChildren().add(triangle);

    }

}
