package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

public abstract class Gate extends DiagramElement {

    public Gate(String name, String type) {
        super(name, type);
    }

    public abstract InputVariable input();

    public abstract OutputVariable output();
}
