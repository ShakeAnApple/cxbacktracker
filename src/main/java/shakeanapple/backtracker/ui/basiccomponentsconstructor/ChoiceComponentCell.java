package shakeanapple.backtracker.ui.basiccomponentsconstructor;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.*;
import shakeanapple.backtracker.ui.infrasructure.control.diagramold.model.Cell;
import shakeanapple.backtracker.ui.infrasructure.FunctionTwo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ChoiceComponentCell extends Cell {
    private String name;

    private ChoiceComponent component;
    private FunctionTwo<Button, Long, Boolean> pinPressHandler;
    private Function<Long, Boolean> isInvertedCheckboxPressHandler;

    private static final double PIN_HEIGHT = 20;
    private static final double PIN_SPACE_HEIGHT = 40;
    private static final double RECT_WIDTH = 70;

    public ChoiceComponentCell(long id, ChoiceComponent component, FunctionTwo<Button, Long, Boolean> pinPressHandler, Function<Long, Boolean> isInvertedCheckboxPressHandler) {
        super(id);
        this.name = component.getType().name();
        this.component = component;
        this.pinPressHandler = pinPressHandler;
        this.isInvertedCheckboxPressHandler = isInvertedCheckboxPressHandler;


        List<Button> inputPins = createInputPins(component.getChoices());
        List<CheckBox> inputInvertedCheckBoxes = createInputInvertedCheckBoxes(component.getChoices());
        Button outputPin = createOutputPins(component.getOutput());

        double rectHeight = inputPins.size() * PIN_HEIGHT + PIN_SPACE_HEIGHT;

        Rectangle view = new Rectangle(RECT_WIDTH, rectHeight);

        bindInputPins(view, inputPins, inputInvertedCheckBoxes);
        bindOutputPin(view, outputPin);

        Label label = new Label(name);

        label.layoutXProperty().bind(view.xProperty().add(view.getWidth() / 2).subtract(label.widthProperty().divide(2)));
        label.layoutYProperty().bind(view.yProperty().add(view.getHeight() / 2).subtract(label.heightProperty().divide(2)));

        view.setStroke(Color.BLACK);
        view.setFill(Color.LAVENDER);

        List<Node> nodes = new ArrayList<>(inputPins);
        nodes.addAll(inputInvertedCheckBoxes);
        nodes.add(outputPin);
        nodes.add(label);
        setView(view, nodes);
    }

    private List<CheckBox> createInputInvertedCheckBoxes(List<Choice> choices) {
        List<CheckBox> responseChBxs = new ArrayList<>();
        List<CheckBox> conditionChBxs = new ArrayList<>();
        for (Choice choice : choices) {
            CheckBox condition = new CheckBox();
            condition.minHeight(PIN_HEIGHT);
            condition.minWidth(PIN_HEIGHT);
            condition.onActionProperty().setValue(
                    actionEvent -> isInvertedCheckboxPressHandler.apply(choice.getCondition().getId()));
            conditionChBxs.add(condition);

            CheckBox response = new CheckBox();
            response.minHeight(PIN_HEIGHT);
            response.minWidth(PIN_HEIGHT);
            response.onActionProperty().setValue(
                    actionEvent -> isInvertedCheckboxPressHandler.apply(choice.getOutput().getId()));
            responseChBxs.add(response);
        }
        responseChBxs.addAll(conditionChBxs);
        return responseChBxs;
    }

    private void bindInputPins(Rectangle view, List<Button> pins, List<CheckBox> isInvertedCheckBoxes) {
        int order = 0;
        int conditionsCount = pins.size() / 2;
        for (int i = 0; i < pins.size(); i++) {
            Button pin = pins.get(i);
            CheckBox cb = isInvertedCheckBoxes.get(i);
            if (i < conditionsCount) {
                pin.layoutYProperty().bind(view.xProperty().divide(pins.size()).add(i * PIN_HEIGHT));
                cb.layoutYProperty().bind(view.xProperty().divide(pins.size()).add(i * PIN_HEIGHT));
            } else {
                pin.layoutYProperty().bind(view.xProperty().divide(pins.size()).add(i * PIN_HEIGHT + PIN_SPACE_HEIGHT));
                cb.layoutYProperty().bind(view.xProperty().divide(pins.size()).add(i * PIN_HEIGHT + PIN_SPACE_HEIGHT));
            }
            pin.layoutXProperty().bind(view.xProperty().subtract(PIN_HEIGHT * 2));
            cb.layoutXProperty().bind(view.xProperty().subtract(PIN_HEIGHT));
        }
    }

    private void bindOutputPin(Rectangle view, Button pin) {
        pin.layoutYProperty().bind(view.xProperty());
        pin.layoutXProperty().bind(view.xProperty().add(view.getWidth()));
    }

    private List<Button> createInputPins(List<Choice> inputs) {
        List<Button> responseButtons = new ArrayList<>();
        List<Button> conditionButtons = new ArrayList<>();
        for (Choice choice : inputs) {
            Button condition = new Button(choice.getCondition().getName());
            condition.minHeight(PIN_HEIGHT);
            condition.minWidth(PIN_HEIGHT);
            condition.onActionProperty().setValue(
                    actionEvent -> pinPressHandler.apply(condition, choice.getCondition().getId()));
            conditionButtons.add(condition);

            Button response = new Button(choice.getOutput().getName());
            response.minHeight(PIN_HEIGHT);
            response.minWidth(PIN_HEIGHT);
            response.onActionProperty().setValue(
                    actionEvent -> pinPressHandler.apply(response, choice.getOutput().getId()));
            responseButtons.add(response);
        }
        responseButtons.addAll(conditionButtons);
        return responseButtons;
    }

    private Button createOutputPins(OutputVariable out) {
        ChoiceComponent curComponent = this.component;
        Button b = new Button(out.getName());
        b.minHeight(PIN_HEIGHT);
        b.minWidth(PIN_HEIGHT);
        b.onActionProperty().setValue(
                actionEvent -> pinPressHandler.apply(b, out.getId()));
        return b;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
