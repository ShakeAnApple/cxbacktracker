package shakeanapple.backtracker.ui.basiccomponentsconstructor;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.BasicComponentAbstract;
import shakeanapple.backtracker.ui.control.diagram.model.Cell;

public class ComponentCell extends Cell {
    private String name;

    public ComponentCell(long id, BasicComponentAbstract component) {
        super(id);
        this.name = component.getType().name();

        Rectangle view = new Rectangle(20, 20);

        Label label = new Label(name);

        label.layoutXProperty().bind(view.xProperty().add(view.getWidth() / 2).subtract(label.widthProperty().divide(2)));
        label.layoutYProperty().bind(view.yProperty().add(view.getHeight() / 2).subtract(label.heightProperty().divide(2)));


        view.setStroke(Color.BLACK);
        view.setFill(Color.LAVENDER);

        setView(view, label);

    }

    @Override
    public String toString() {
        return this.name;
    }
}

