package shakeanapple.backtracker.ui.explainer.model.graph.cell;

import shakeanapple.backtracker.ui.infrasructure.control.diagramold.model.Cell;

import java.util.Map;

public abstract class ExplainerCell extends Cell {
    public ExplainerCell(long cellId) {
        super(cellId);
    }

    public abstract Map<String, InputPin> getInputPins();

    public abstract Map<String, OutputPin> getOutputPins();

    public abstract String getName();
}
