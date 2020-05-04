package shakeanapple.backtracker.core.diagramexplanation.model;

import java.util.EventListener;

public interface InterfaceUpdatedListener extends EventListener {
    void onInputInterfaceUpdated();
    void onOutputInterfaceUpdated();
}
