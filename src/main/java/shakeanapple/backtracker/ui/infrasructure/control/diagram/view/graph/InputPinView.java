package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph;

import javafx.scene.Group;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.InputPin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Pin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.DiagramStyles;

import java.util.function.Function;

public class InputPinView extends PinView {
    public InputPinView(Group parent, InputPin pin, DiagramCellView owner) {
        super(parent, pin, owner);

        pin.isInvertedProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal) {
                this.getView().setStyle(this.getView().getStyle().concat(";").concat(DiagramStyles.PIN_INVERTED_STYLE));
            }
        });

        if (pin.isInvertedProperty().getValue()){
            this.getView().setStyle(this.getView().getStyle().concat(";").concat(DiagramStyles.PIN_INVERTED_STYLE));
        }

        super.getLabel().layoutXProperty().bind(this.getView().layoutXProperty().subtract(super.getLabel().widthProperty()));
        super.getLabel().layoutYProperty().bind(this.getView().layoutYProperty().add(this.getView().heightProperty().divide(2)));
    }
}
