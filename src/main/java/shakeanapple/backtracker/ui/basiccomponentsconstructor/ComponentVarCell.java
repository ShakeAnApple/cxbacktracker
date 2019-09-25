package shakeanapple.backtracker.ui.basiccomponentsconstructor;

import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.ui.control.diagram.model.Cell;
import shakeanapple.backtracker.ui.infrasructure.FunctionTwo;

import java.util.ArrayList;
import java.util.Arrays;

public class ComponentVarCell extends Cell {
    private String name;

    private FunctionTwo<Button, Long, Boolean> pinPressHandler;

    private static final double PIN_HEIGHT = 30;

    public ComponentVarCell(long id, String name, FunctionTwo<Button, Long, Boolean> pinPressHandler) {
        super(id);
        this.name = name;
        this.pinPressHandler = pinPressHandler;
        Rectangle view = new Rectangle(10, 10);

        Button button = createPin(id, name);
        button.layoutXProperty().bind(view.xProperty().add(10));
        button.layoutYProperty().bind(view.yProperty());

        setView(view, Arrays.asList(button));
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
}
