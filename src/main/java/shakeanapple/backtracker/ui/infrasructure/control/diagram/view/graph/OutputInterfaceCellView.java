package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Cell;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.InputPin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.OutputInterfaceCell;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.OutputPin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.DiagramStyles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutputInterfaceCellView extends NodeView implements DiagramCellView{
    private OutputInterfaceCell cell;

    private InputPinView input;

    private Rectangle view;

    private List<DiagramCellView> parents = new ArrayList<>();

    private String name;

    public OutputInterfaceCellView(Group parent, OutputInterfaceCell cell) {
        super(parent);
        this.cell = cell;

        this.view = new Rectangle(10, DiagramStyles.PIN_SIZE);
        view.setFill(DiagramStyles.OUTPUT_CELL_COLOR);
        view.setStroke(DiagramStyles.OUTPUT_CELL_STROKE_COLOR);
        this.name = cell.getName();

        Label label = new Label(cell.getShortName());
        label.layoutXProperty().bind(view.layoutXProperty().add(2));
        label.layoutYProperty().bind(view.layoutYProperty()
                .add(view.heightProperty().divide(2))
                .subtract(label.heightProperty().divide(2)));
        label.addEventHandler(MouseEvent.ANY, e -> this.view.fireEvent(e));
        view.widthProperty().bind(label.widthProperty().add(4));

        this.input = new InputPinView(parent, cell.getInputPins().values().stream().findFirst().get(), this);
        this.input.getView().layoutXProperty().bind(view.layoutXProperty().subtract(this.input.getView().widthProperty()));
        this.input.getView().layoutYProperty().bind(view.layoutYProperty());

        parent.getChildren().add(view);
        parent.getChildren().add(label);
    }

    public OutputInterfaceCell getCell() {
        return cell;
    }

    public Node getView() {
        return this.view;
    }

    @Override
    public List<InputPinView> getInputs() {
        return Collections.singletonList(this.input);
    }

    @Override
    public List<OutputPinView> getOutputs() {
        return new ArrayList<>();
    }

    @Override
    public List<DiagramCellView> getParents() {
        return this.parents;
    }

    @Override
    public double getWidth() {
        double width = this.view.getBoundsInParent().getWidth();
        if (this.input != null){
            width += this.input.getWidth();
        }
        return width;
    }

    @Override
    public String getName() {
        return this.getName();
    }

    @Override
    public double getHeight() {
        return this.view.heightProperty().get();
    }

}
