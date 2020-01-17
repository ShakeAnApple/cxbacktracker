package shakeanapple.backtracker.ui.explainer.model.graph.cell;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.FunctionBlockSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BasicComponentCell extends ExplainerCell {
    private String name;
    private String type;

    private Function<Pin, Boolean> pinPressHandler;

    private static final double PIN_HEIGHT = 26;
    private static final double RECT_WIDTH = 70;

    private Map<String, InputPin> inputPins;
    private Map<String, OutputPin> outputPins;

    public BasicComponentCell(long id, FunctionBlockSnapshot component, Function<Pin, Boolean> pinPressHandler) {
        super(id);
        this.name = component.getName();
        this.type = component.getType();
        this.pinPressHandler = pinPressHandler;

        this.inputPins = createInputPins(component.getFbInterface().getInputs()).stream().collect(Collectors.toMap(Pin::getName, in -> in));
        this.outputPins = createOutputPins(component.getFbInterface().getOutputs()).stream().collect(Collectors.toMap(Pin::getName, in -> in));

        double rectHeight = Math.max(inputPins.size() * PIN_HEIGHT, outputPins.size() * PIN_HEIGHT);


        Rectangle view = new Rectangle(RECT_WIDTH, rectHeight);

//        Label label = new Label(this.name + System.lineSeparator() + this.type);
//        StackPane view = new StackPane(label);
//        Rectangle rect = new Rectangle();
//        rect.widthProperty().bind(label.widthProperty().add(10));
//        rect.heightProperty().bind(label.heightProperty().add(10));
//        view.setStyle("-fx-background-color: coral;");


        bindInputPins(view, new ArrayList<>(inputPins.values()));
        bindOutputPins(view, new ArrayList<>(outputPins.values()));

        Label label = new Label(this.name + System.lineSeparator() + this.type);

        view.widthProperty().bind(label.widthProperty().add(4));

//        label.layoutXProperty().bind(view.xProperty().add(view.getWidth() / 2).subtract(label.widthProperty().divide(2)));
        label.layoutXProperty().bind(view.xProperty().add(2));
        label.layoutYProperty().bind(view.yProperty().add(view.getHeight() / 2).subtract(label.heightProperty().divide(2)));

        view.setStroke(Color.BLACK);
        view.setFill(Color.LAVENDER);

        List<Node> nodes = new ArrayList<>(inputPins.values());
        nodes.addAll(outputPins.values());

        nodes.add(label);
        setView(view, nodes);

//        super.setBorder(new Border(new BorderStroke(Color.BLACK,
//                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
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
            pin.layoutYProperty().bind(view.layoutYProperty().divide(pins.size()).add(order * PIN_HEIGHT));
            pin.layoutXProperty().bind(view.layoutXProperty().add(view.widthProperty()));
            order ++;
        }
    }

    private void bindInputPins(Rectangle view, List<Pin> pins) {
        for (int i = 0; i < pins.size(); i++) {
            Pin pin = pins.get(i);
            pin.layoutYProperty().bind(view.layoutYProperty().divide(pins.size()).add(i * PIN_HEIGHT));
            pin.layoutXProperty().bind(view.layoutXProperty().subtract(PIN_HEIGHT));
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
    public String toString() {
        return this.name;
    }
}
