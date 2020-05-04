package shakeanapple.backtracker.core.diagramexplanation.model;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class OutputUpdatedEvent implements Event {
    private List<OutputUpdatedListener> listeners;

    public OutputUpdatedEvent() {
        this.listeners = new ArrayList<>();
    }

    public void fire(Gate gate){
        for (OutputUpdatedListener l : this.listeners) {
            l.onOutputUpdated(gate);
        }
    }

    @Override
    public void addListener(EventListener listener) {
        this.listeners.add((OutputUpdatedListener) listener);
    }
}
