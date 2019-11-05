package shakeanapple.backtracker.ui.explainer.model.graphcell;

//public class ChoiceComponentCell extends Cell {
//    private String name;
//
//    private FunctionTwo<Button, Long, Boolean> pinPressHandler;
//
//    private static final double PIN_HEIGHT = 20;
//    private static final double PIN_SPACE_HEIGHT = 40;
//    private static final double RECT_WIDTH = 70;
//
//    public ChoiceComponentCell(long id, FunctionTwo<Button, Long, Boolean> pinPressHandler) {
//        super(id);
//        this.name = component.getType().name();
//        this.pinPressHandler = pinPressHandler;
//
//
//        List<Pin> inputPins = createInputPins(component.getChoices());
//        Pin outputPin = createOutputPins(component.getOutput());
//
//        double rectHeight = inputPins.size() * PIN_HEIGHT + PIN_SPACE_HEIGHT;
//
//        Rectangle view = new Rectangle(RECT_WIDTH, rectHeight);
//
//        bindInputPins(view, inputPins);
//        bindOutputPin(view, outputPin);
//
//        Label label = new Label(name);
//
//        label.layoutXProperty().bind(view.xProperty().add(view.getWidth() / 2).subtract(label.widthProperty().divide(2)));
//        label.layoutYProperty().bind(view.yProperty().add(view.getHeight() / 2).subtract(label.heightProperty().divide(2)));
//
//        view.setStroke(Color.BLACK);
//        view.setFill(Color.LAVENDER);
//
//        List<Node> nodes = new ArrayList<>(inputPins);
//        nodes.add(outputPin);
//        nodes.add(label);
//        setView(view, nodes);
//    }
//
//    private void bindInputPins(Rectangle view, List<Pin> pins) {
//        int conditionsCount = pins.size() / 2;
//        for (int i = 0; i < pins.size(); i++) {
//            Pin pin = pins.get(i);
//            if (i < conditionsCount) {
//                pin.layoutYProperty().bind(view.xProperty().divide(pins.size()).add(i * PIN_HEIGHT));
//            } else {
//                pin.layoutYProperty().bind(view.xProperty().divide(pins.size()).add(i * PIN_HEIGHT + PIN_SPACE_HEIGHT));
//            }
//            pin.layoutXProperty().bind(view.xProperty().subtract(PIN_HEIGHT * 2));
//        }
//    }
//
//    private void bindOutputPin(Rectangle view, Button pin) {
//        pin.layoutYProperty().bind(view.xProperty());
//        pin.layoutXProperty().bind(view.xProperty().add(view.getWidth()));
//    }
//
//    private List<Pin> createInputPins(List<Choice> inputs) {
//        List<Pin> responsePins = new ArrayList<>();
//        List<Pin> conditionPin = new ArrayList<>();
//        for (Choice choice : inputs) {
//            Pin condition = new InputPin(this, choice.getCondition().getName());
//            condition.minHeight(PIN_HEIGHT);
//            condition.minWidth(PIN_HEIGHT);
//            condition.onActionProperty().setValue(
//                    actionEvent -> pinPressHandler.apply(condition, choice.getCondition().getId()));
//            conditionPin.add(condition);
//
//            Pin response = new InputPin(this, choice.getOutput().getName());
//            response.minHeight(PIN_HEIGHT);
//            response.minWidth(PIN_HEIGHT);
//            response.onActionProperty().setValue(
//                    actionEvent -> pinPressHandler.apply(response, choice.getOutput().getId()));
//            responsePins.add(response);
//        }
//        responsePins.addAll(conditionPin);
//        return responsePins;
//    }
//
//    private Pin createOutputPins(OutputVariable out) {
//        Pin p = new OutputPin(this, out.getName());
//        p.minHeight(PIN_HEIGHT);
//        p.minWidth(PIN_HEIGHT);
//        p.onActionProperty().setValue(
//                actionEvent -> pinPressHandler.apply(p, out.getId()));
//        return p;
//    }
//
//    @Override
//    public String toString() {
//        return this.name;
//    }
//}
