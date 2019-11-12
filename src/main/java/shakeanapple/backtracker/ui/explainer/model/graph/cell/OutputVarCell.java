package shakeanapple.backtracker.ui.explainer.model.graph.cell;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.ui.infrasructure.FunctionTwo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class OutputVarCell extends ExplainerCell {
    private String name;

    private InputPin inputPin;

    private Function<Pin, Boolean> pinPressHandler;

    private static final double PIN_HEIGHT = 20;

    public OutputVarCell(long id, String name, Function<Pin, Boolean> pinPressHandler) {
        super(id);
        this.name = name;
        this.pinPressHandler = pinPressHandler;

        Rectangle view = new Rectangle(70, PIN_HEIGHT);
        view.setStroke(Color.BLACK);
        view.setFill(Color.LAVENDER);

        Label label = new Label(this.name);

        view.widthProperty().bind(label.widthProperty());

        label.layoutXProperty().bind(view.xProperty());
        label.layoutYProperty().bind(view.yProperty().add(view.getHeight() / 2).subtract(label.heightProperty().divide(2)));

        this.inputPin = createPin(id, name);
        this.inputPin.layoutXProperty().bind(view.xProperty().subtract(PIN_HEIGHT));
        this.inputPin.layoutYProperty().bind(view.yProperty());

        setView(view, Arrays.asList(inputPin, label));
    }

    private InputPin createPin(long id, String name) {
        InputPin pin = new InputPin(this, name, 0);
        pin.setMinHeight(PIN_HEIGHT);
        pin.setMinWidth(PIN_HEIGHT);
        pin.onActionProperty().setValue(
                actionEvent -> pinPressHandler.apply(this.inputPin));
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
}

