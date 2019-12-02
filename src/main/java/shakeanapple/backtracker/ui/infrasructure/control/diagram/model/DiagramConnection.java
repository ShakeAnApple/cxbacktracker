package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import shakeanapple.backtracker.common.variable.ValueHolder;

public interface DiagramConnection {
    Edge inferEdge();

    String getId();

    void updateValue(ValueHolder value);

    void isCauseEdge(boolean isCause);
}
