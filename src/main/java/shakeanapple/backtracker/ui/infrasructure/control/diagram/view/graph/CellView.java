package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
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

    public CellView(Group parent, Cell cell) {
        super(parent);
        this.cell = cell;

        double rectHeight = Math.max(cell.getInputPins().size() * DiagramStyles.PIN_SIZE, cell.getOutputPins().size() * DiagramStyles.PIN_SIZE);
        this.view = new Rectangle(10, rectHeight);
        view.setFill(DiagramStyles.CELL_COLOR);
        view.setStroke(DiagramStyles.CELL_STROKE_COLOR);

        Label label = new Label(cell.getName());
        label.layoutXProperty().bind(view.layoutXProperty());
        label.layoutYProperty().bind(view.layoutYProperty());
        label.addEventHandler(MouseEvent.ANY, e -> this.view.fireEvent(e));
        view.widthProperty().bind(label.widthProperty().add(4));

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

            p.getView().layoutXProperty().bind(view.layoutXProperty().subtract(DiagramStyles.PIN_SIZE));
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
    public void setParents(List<DiagramCellView> parents){
        this.parents = parents;
    }

    @Override
    public List<DiagramCellView> getParents() {
        return this.parents;
    }

}
