package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Cell;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.InputPin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.OutputPin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.DiagramStyles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CellView extends NodeView implements DiagramCellView {

    private Cell cell;

    private List<InputPinView> inputs;
    private List<OutputPinView> outputs;

    private Rectangle view;

    private List<DiagramCellView> parents = new ArrayList<>();

    private String name;

    public CellView(Group parent, Cell cell) {
        super(parent);
        this.cell = cell;

        double rectHeight = Math.max(cell.getInputPins().size() * DiagramStyles.PIN_SIZE, cell.getOutputPins().size() * DiagramStyles.PIN_SIZE);
        this.view = new Rectangle(10, rectHeight);
        view.setFill(DiagramStyles.CELL_COLOR);
        view.setStroke(DiagramStyles.CELL_STROKE_COLOR);

        this.name = cell.getName() + '\n' + cell.getType();
        Label label = new Label(cell.getName() + '\n' + cell.getType());
        label.layoutXProperty().bind(view.layoutXProperty().add(2));
        label.layoutYProperty().bind(view.layoutYProperty()
                .add(view.heightProperty().divide(2))
                .subtract(label.heightProperty().divide(2)));
        label.addEventHandler(MouseEvent.ANY, e -> this.view.fireEvent(e));
        view.widthProperty().bind(label.widthProperty().add(4));

        this.view.onMouseClickedProperty().setValue(
                event ->
                {
                    if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                        this.cell.getCellClickHandler().apply(this.cell.getName());
                    }
                });

        this.inputs = this.createInputPinViews(parent, cell.getInputPins().values());
        this.outputs = this.createOutputPinViews(parent, cell.getOutputPins().values());

        parent.getChildren().add(view);
        parent.getChildren().add(label);

    }

    private List<OutputPinView> createOutputPinViews(Group parent, Collection<OutputPin> outputPins) {
        List<OutputPinView> pins = new ArrayList<>();
        int order = 0;
        for (OutputPin out : outputPins) {
            OutputPinView p = new OutputPinView(parent, out, this);
            pins.add(p);

            p.getView().layoutXProperty().bind(view.layoutXProperty().add(view.widthProperty()));
            p.getView().layoutYProperty().bind(view.layoutYProperty().add(DiagramStyles.PIN_SIZE * order));

            order++;
        }
        return pins;
    }

    private List<InputPinView> createInputPinViews(Group parent, Collection<InputPin> inputPins) {
        List<InputPinView> pins = new ArrayList<>();
        int order = 0;
        for (InputPin in : inputPins) {
            InputPinView p = new InputPinView(parent, in, this);
            pins.add(p);

//            p.getView().layoutXProperty().bind(view.layoutXProperty().subtract(DiagramStyles.PIN_SIZE));
            p.getView().layoutXProperty().bind(view.layoutXProperty().subtract(p.getView().widthProperty()));
            p.getView().layoutYProperty().bind(view.layoutYProperty().add(DiagramStyles.PIN_SIZE * order));

            order++;
        }
        return pins;
    }

    public Cell getCell() {
        return cell;
    }

    public List<InputPinView> getInputs() {
        return inputs;
    }

    public List<OutputPinView> getOutputs() {
        return outputs;
    }

    public Node getView() {
        return this.view;
    }

    @Override
    public List<DiagramCellView> getParents() {
        return this.parents;
    }

    @Override
    public double getWidth() {
        double width = this.view.widthProperty().get();
        if (!this.inputs.isEmpty()) {
            width += this.inputs.get(0).getWidth();
        }
        if (!this.outputs.isEmpty()) {
            width += this.outputs.get(0).getWidth();
        }
        return width;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getHeight() {
        return this.view.heightProperty().get();
    }

}
