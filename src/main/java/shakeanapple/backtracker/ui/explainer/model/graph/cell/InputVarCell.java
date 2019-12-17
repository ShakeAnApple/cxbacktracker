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

public class InputVarCell extends ExplainerCell {
    private String name;

    private Function<Pin, Boolean> pinPressHandler;

    private static final double PIN_HEIGHT = 20;

    private OutputPin outputPin;

    public InputVarCell(long id, String name, Function<Pin, Boolean> pinPressHandler) {
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

        this.outputPin = createPin(id, name);
        outputPin.layoutXProperty().bind(view.xProperty().add(view.widthProperty()));
        outputPin.layoutYProperty().bind(view.yProperty());

        setView(view, Arrays.asList(outputPin, label));
    }

    private OutputPin createPin(long id, String name) {
        OutputPin outputPin = new OutputPin(this, name, 0);
        outputPin.setMinHeight(PIN_HEIGHT);
        outputPin.setMinWidth(PIN_HEIGHT);
        outputPin.onActionProperty().setValue(
                actionEvent -> pinPressHandler.apply(this.outputPin));
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

    public OutputPin getOutputPin() {
        return this.outputPin;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
