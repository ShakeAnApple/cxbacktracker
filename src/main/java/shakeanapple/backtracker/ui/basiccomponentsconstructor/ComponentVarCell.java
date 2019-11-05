package shakeanapple.backtracker.ui.basiccomponentsconstructor;

import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Cell;
import shakeanapple.backtracker.ui.infrasructure.FunctionTwo;

import java.util.Arrays;

public class ComponentVarCell extends Cell {
    private String name;

    private FunctionTwo<Button, Long, Boolean> pinPressHandler;

    private static final double PIN_HEIGHT = 30;

    private Button pin;

    public ComponentVarCell(long id, String name, FunctionTwo<Button, Long, Boolean> pinPressHandler) {
        super(id);
        this.name = name;
        this.pinPressHandler = pinPressHandler;
        Rectangle view = new Rectangle(10, 10);

        this.pin = createPin(id, name);
        this.pin.layoutXProperty().bind(view.xProperty().add(10));
        this.pin.layoutYProperty().bind(view.yProperty());

        setView(view, Arrays.asList(this.pin));
    }

    private Button createPin(long id, String name) {
        Button b = new Button(name);
        b.minHeight(PIN_HEIGHT);
        b.minWidth(PIN_HEIGHT);
        b.onActionProperty().setValue(
                actionEvent -> pinPressHandler.apply(b, id));
        return b;
    }

    @Override
    public String toString() {
        return this.name;
    }

//    @Override
//    public void bindEdgeStart(Line line) {
//        line.startXProperty()
//                .bind(this.pin.layoutXProperty().add(this.pin.widthProperty()));
//        line.startYProperty()
//                .bind(this.pin.layoutYProperty().add(this.pin.heightProperty().divide(2)));
//    }
//
//    @Override
//    public void bindEdgeEnd(Line line) {
//        line.startXProperty()
//                .bind(this.pin.layoutXProperty());
//        line.startYProperty()
//                .bind(this.pin.layoutYProperty().add(this.pin.heightProperty().divide(2)));
//    }
}
