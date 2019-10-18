package shakeanapple.backtracker.core.diagramexplanation.model;

import java.util.EventListener;

public interface InputUpdatedListener extends EventListener {
    void onInputUpdated(Gate gate);
}
