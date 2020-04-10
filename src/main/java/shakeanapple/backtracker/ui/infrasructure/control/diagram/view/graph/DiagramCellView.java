package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph;

import javafx.scene.Node;

import java.util.List;

public interface DiagramCellView {
    Node getView();

    List<InputPinView> getInputs();

    List<OutputPinView> getOutputs();

    List<DiagramCellView> getParents();

    double getWidth();

    String getName();

    double getHeight();
}
