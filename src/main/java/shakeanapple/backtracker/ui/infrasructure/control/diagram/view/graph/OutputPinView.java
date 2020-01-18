package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph;

import javafx.scene.Group;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.OutputPin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Pin;

import java.util.function.Function;

public class OutputPinView extends PinView {
    public OutputPinView(Group parent, OutputPin pin, DiagramCellView owner) {
        super(parent, pin, owner);

        super.getLabel().layoutXProperty().bind(this.getView().layoutXProperty().add(this.getView().widthProperty()));
        super.getLabel().layoutYProperty().bind(this.getView().layoutYProperty().add(super.getLabel().heightProperty().divide(2)));
    }
}
