package shakeanapple.backtracker.ui.explainer.model.graphcell;

import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.ui.infrasructure.FunctionTwo;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Cell;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OutputVarCell extends ExplainerCell {
    private String name;

    private InputPin inputPin;

    private FunctionTwo<Button, Long, Boolean> pinPressHandler;

    private static final double PIN_HEIGHT = 30;

    public OutputVarCell(long id, String name, FunctionTwo<Button, Long, Boolean> pinPressHandler) {
        super(id);
        this.name = name;
        this.pinPressHandler = pinPressHandler;
        Rectangle view = new Rectangle(10, 10);

        this.inputPin = createPin(id, name);
        this.inputPin.layoutXProperty().bind(view.xProperty().add(10));
        this.inputPin.layoutYProperty().bind(view.yProperty());

        setView(view, Arrays.asList(inputPin));
    }

    private InputPin createPin(long id, String name) {
        InputPin pin = new InputPin(this, name, 0);
        pin.minHeight(PIN_HEIGHT);
        pin.minWidth(PIN_HEIGHT);
        pin.onActionProperty().setValue(
                actionEvent -> pinPressHandler.apply(pin, id));
        return pin;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Map<String, InputPin> getInputPins() {
        HashMap<String, InputPin> in = new HashMap<>();
        in.put(this.inputPin.getName(), this.inputPin);
        return in;
    }

    @Override
    public Map<String, OutputPin> getOutputPins() {
        return new HashMap<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }
}

