package shakeanapple.backtracker.ui.explainer.model.graphcell;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.FunctionBlockSnapshot;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.BasicComponent;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.InputVariable;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.OutputVariable;
import shakeanapple.backtracker.ui.infrasructure.FunctionTwo;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BasicComponentCell extends ExplainerCell {
    private String name;
    private String type;

    private Function<Pin, Boolean> pinPressHandler;

    private static final double PIN_HEIGHT = 20;
    private static final double RECT_WIDTH = 70;

    private Map<String, InputPin> inputPins;
    private Map<String, OutputPin> outputPins;

    private Rectangle rect;

    public BasicComponentCell(long id, FunctionBlockSnapshot component, Function<Pin, Boolean> pinPressHandler) {
        super(id);
        this.name = component.getName();
        this.type = component.getType();
        this.pinPressHandler = pinPressHandler;

        this.inputPins = createInputPins(component.getFbInterface().getInputs()).stream().collect(Collectors.toMap(Pin::getName, in -> in));
        this.outputPins = createOutputPins(component.getFbInterface().getOutputs()).stream().collect(Collectors.toMap(Pin::getName, in -> in));

        double rectHeight = Math.max(inputPins.size() * PIN_HEIGHT, outputPins.size() * PIN_HEIGHT);

        Rectangle view = new Rectangle(RECT_WIDTH, rectHeight);
        this.rect = view;

        bindInputPins(view, new ArrayList<>(inputPins.values()));
        bindOutputPins(view, new ArrayList<>(outputPins.values()));

        Label label = new Label(this.name + System.lineSeparator() + this.type);

        label.layoutXProperty().bind(view.xProperty().add(view.getWidth() / 2).subtract(label.widthProperty().divide(2)));
        label.layoutYProperty().bind(view.yProperty().add(view.getHeight() / 2).subtract(label.heightProperty().divide(2)));

        view.setStroke(Color.BLACK);
        view.setFill(Color.LAVENDER);

        List<Node> nodes = new ArrayList<>(inputPins.values());
        nodes.addAll(outputPins.values());
        nodes.add(label);
        setView(view, nodes);
    }

    public Map<String, InputPin> getInputPins() {
        return this.inputPins;
    }

    public Map<String, OutputPin> getOutputPins() {
        return this.outputPins;
    }

    private void bindOutputPins(Rectangle view, List<Pin> pins) {
        int order = 0;
        for (Pin pin: pins){
            pin.layoutYProperty().bind(view.yProperty().divide(pins.size()).add(order * PIN_HEIGHT));
            pin.layoutXProperty().bind(view.xProperty().add(view.getWidth()));
            order ++;
        }
    }

    private void bindInputPins(Rectangle view, List<Pin> pins) {
        for (int i = 0; i < pins.size(); i++) {
            Pin pin = pins.get(i);
            pin.layoutYProperty().bind(view.yProperty().divide(pins.size()).add(i * PIN_HEIGHT));
            pin.layoutXProperty().bind(view.xProperty().subtract(PIN_HEIGHT));
        }
    }

    private List<InputPin> createInputPins(List<String> inputs) {
        List<InputPin> pins = new ArrayList<>();
        int order = 0;
        for (String in : inputs) {
            InputPin p = new InputPin(this, in, order);
            p.setMinHeight(PIN_HEIGHT);
            p.setMinWidth(PIN_HEIGHT);
            p.onActionProperty().setValue(
                    actionEvent -> pinPressHandler.apply(p));
            pins.add(p);
            order ++;
        }
        return pins;
    }

    private List<OutputPin> createOutputPins(List<String> outputs) {
        List<OutputPin> pins = new ArrayList<>();
        int order = 0;
        for (String out : outputs) {
            OutputPin p = new OutputPin(this, out, order);
            p.setMinHeight(PIN_HEIGHT);
            p.setMinWidth(PIN_HEIGHT);
            p.onActionProperty().setValue(
                    actionEvent -> pinPressHandler.apply(p));
            pins.add(p);
            order ++;
        }
        return pins;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public Rectangle getRect() {
        return this.rect;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
