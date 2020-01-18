package shakeanapple.backtracker.ui.basiccomponentsconstructor;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.BasicComponent;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.InputVariable;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.OutputVariable;
import shakeanapple.backtracker.ui.infrasructure.control.diagramold.model.Cell;
import shakeanapple.backtracker.ui.infrasructure.FunctionTwo;
//import shakeanapple.backtracker.ui.infrasructure.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BasicComponentCell extends Cell {
    private String name;

    private FunctionTwo<Button, Long, Boolean> pinPressHandler;
    private Function<Long, Boolean> isInvertedCheckboxPressHandler;

    private static final double PIN_HEIGHT = 20;
    private static final double RECT_WIDTH = 70;

    public BasicComponentCell(long id, BasicComponent component, FunctionTwo<Button, Long, Boolean> pinPressHandler, Function<Long, Boolean> isInvertedCheckboxPressHandler) {
        super(id);
        this.name = component.getType().name();
        this.pinPressHandler = pinPressHandler;
        this.isInvertedCheckboxPressHandler = isInvertedCheckboxPressHandler;

        List<Button> inputPins = createInputPins(component.getInputs());
        List<CheckBox> inputInvertedCheckBoxes = createInputInvertedCheckBoxes(component.getInputs());
        List<Button> outputPins = createOutputPins(component.getOutputs());

        double rectHeight = Math.max(inputPins.size() * PIN_HEIGHT, outputPins.size() * PIN_HEIGHT);

        Rectangle view = new Rectangle(RECT_WIDTH, rectHeight);

        bindInputPins(view, inputPins, inputInvertedCheckBoxes);
        bindOutputPins(view, outputPins);

        Label label = new Label(name);

        label.layoutXProperty().bind(view.xProperty().add(view.getWidth() / 2).subtract(label.widthProperty().divide(2)));
        label.layoutYProperty().bind(view.yProperty().add(view.getHeight() / 2).subtract(label.heightProperty().divide(2)));

        view.setStroke(Color.BLACK);
        view.setFill(Color.LAVENDER);

        List<Node> nodes = new ArrayList<>(inputPins);
        nodes.addAll(inputInvertedCheckBoxes);
        nodes.addAll(outputPins);
        nodes.add(label);
        setView(view, nodes);
    }

    private List<CheckBox> createInputInvertedCheckBoxes(List<InputVariable> inputs) {
        List<CheckBox> chbxs = new ArrayList<>();
        for (InputVariable in : inputs) {
            CheckBox cb = new CheckBox();
            cb.minHeight(PIN_HEIGHT);
            cb.minWidth(PIN_HEIGHT);
            cb.onActionProperty().setValue(
                    actionEvent -> isInvertedCheckboxPressHandler.apply(in.getId()));
            chbxs.add(cb);
        }
        return chbxs;
    }

    private void bindOutputPins(Rectangle view, List<Button> pins) {
        int order = 0;
        for (Button pin: pins){
            pin.layoutYProperty().bind(view.xProperty().divide(pins.size()).add(order * PIN_HEIGHT));
            pin.layoutXProperty().bind(view.xProperty().add(view.getWidth()));
            order ++;
        }
    }

    private void bindInputPins(Rectangle view, List<Button> pins, List<CheckBox> checkBoxes) {
        for (int i = 0; i < pins.size(); i++) {
            Button pin = pins.get(i);
            CheckBox cb = checkBoxes.get(i);
            pin.layoutYProperty().bind(view.xProperty().divide(pins.size()).add(i * PIN_HEIGHT));
            cb.layoutYProperty().bind(view.xProperty().divide(pins.size()).add(i * PIN_HEIGHT));

            pin.layoutXProperty().bind(view.xProperty().subtract(PIN_HEIGHT * 2));
            cb.layoutXProperty().bind(view.xProperty().subtract(PIN_HEIGHT));
        }
    }

    private List<Button> createInputPins(List<InputVariable> inputs) {
        List<Button> buttons = new ArrayList<>();
        for (InputVariable in : inputs) {
            Button b = new Button(in.getName());
            b.minHeight(PIN_HEIGHT);
            b.minWidth(PIN_HEIGHT);
            b.onActionProperty().setValue(
                    actionEvent -> pinPressHandler.apply(b, in.getId()));
            buttons.add(b);
        }
        return buttons;
    }

    private List<Button> createOutputPins(List<OutputVariable> outputs) {
        List<Button> buttons = new ArrayList<>();
        for (OutputVariable out : outputs) {
            Button b = new Button(out.getName());
            b.minHeight(PIN_HEIGHT);
            b.minWidth(PIN_HEIGHT);
            b.onActionProperty().setValue(
                    actionEvent -> pinPressHandler.apply(b, out.getId()));
            buttons.add(b);
        }
        return buttons;
    }

    @Override
    public String toString() {
        return this.name;
    }

//    @Override
//    public void bindEdgeStart(Line line) {
//        double sourceHeightPos = (super.getBoundsInParent().getHeight() / super.getOutcomingEdgesCount()) * (super.getBoundOutcomingEdgesCount() + 1);
//
//        line.startXProperty()
//                .bind(super.layoutXProperty().add(super.getBoundsInParent().getWidth()));
//        line.startYProperty()
//                .bind(super.layoutYProperty().add(sourceHeightPos));
//    }
//
//    @Override
//    public void bindEdgeEnd(Line line) {
//        double targetHeightPos = (super.getBoundsInParent().getHeight() / super.getIncomingEdgesCount()) * (super.getBoundIncomingEdgesCount() + 1);
//
//        line.endXProperty()
//                .bind(super.layoutXProperty());
//        line.endYProperty()
//                .bind(super.layoutYProperty().add(targetHeightPos));
//    }
}
