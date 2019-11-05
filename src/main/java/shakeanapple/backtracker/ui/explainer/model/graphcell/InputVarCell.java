package shakeanapple.backtracker.ui.explainer.model.graphcell;

import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.ui.infrasructure.FunctionTwo;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Cell;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InputVarCell extends ExplainerCell {
    private String name;

    private FunctionTwo<Button, Long, Boolean> pinPressHandler;

    private static final double PIN_HEIGHT = 30;

    private OutputPin outputPin;

    public InputVarCell(long id, String name, FunctionTwo<Button, Long, Boolean> pinPressHandler) {
        super(id);
        this.name = name;
        this.pinPressHandler = pinPressHandler;
        Rectangle view = new Rectangle(10, 10);

        this.outputPin = createPin(id, name);
        outputPin.layoutXProperty().bind(view.xProperty().add(10));
        outputPin.layoutYProperty().bind(view.yProperty());

        setView(view, Arrays.asList(outputPin));
    }

    private OutputPin createPin(long id, String name) {
        OutputPin outputPin = new OutputPin(this, name, 0);
        outputPin.minHeight(PIN_HEIGHT);
        outputPin.minWidth(PIN_HEIGHT);
        outputPin.onActionProperty().setValue(
                actionEvent -> pinPressHandler.apply(outputPin, id));
        return outputPin;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Map<String, InputPin> getInputPins() {
        return new HashMap<>();
    }

    @Override
    public Map<String, OutputPin> getOutputPins() {
        HashMap<String, OutputPin> out = new HashMap<>();
        out.put(this.outputPin.getName(), this.outputPin);
        return out;
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
