package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.InputInterfaceCell;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.OutputInterfaceCell;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.DiagramStyles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InputInterfaceCellView extends NodeView implements DiagramCellView{
    private InputInterfaceCell cell;

    private OutputPinView output;

    private Rectangle view;

    private List<DiagramCellView> parents = new ArrayList<>();

    public InputInterfaceCellView(Group parent, InputInterfaceCell cell) {
        super(parent);
        this.cell = cell;

        this.view = new Rectangle(10, DiagramStyles.PIN_SIZE);
        view.setFill(DiagramStyles.INPUT_CELL_COLOR);
        view.setStroke(DiagramStyles.INPUT_CELL_STROKE_COLOR);

        Label label = new Label(cell.getName());
        label.layoutXProperty().bind(view.layoutXProperty());
        label.layoutYProperty().bind(view.layoutYProperty());
        label.addEventHandler(MouseEvent.ANY, e -> this.view.fireEvent(e));
        view.widthProperty().bind(label.widthProperty().add(4));

        this.output = new OutputPinView(parent, cell.getOutputPins().values().stream().findFirst().get(), this);
        this.output.getView().layoutXProperty().bind(view.layoutXProperty().add(this.view.widthProperty()));
        this.output.getView().layoutYProperty().bind(view.layoutYProperty());

        parent.getChildren().add(view);
        parent.getChildren().add(label);
    }

    public InputInterfaceCell getCell() {
        return cell;
    }

    public Node getView() {
        return this.view;
    }

    @Override
    public List<InputPinView> getInputs() {
        return new ArrayList<>();
    }

    @Override
    public List<OutputPinView> getOutputs() {
        return Collections.singletonList(this.output);
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
