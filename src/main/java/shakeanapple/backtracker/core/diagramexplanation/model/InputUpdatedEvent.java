package shakeanapple.backtracker.core.diagramexplanation.model;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class InputUpdatedEvent implements Event {
    private List<InputUpdatedListener> listeners;

    public InputUpdatedEvent() {
        this.listeners = new ArrayList<>();
    }

    public void fire(Gate gate){
        for (InputUpdatedListener l : this.listeners) {
            l.onInputUpdated(gate);
        }
    }

    @Override
    public void addListener(EventListener listener) {
        this.listeners.add((InputUpdatedListener) listener);
    }
}
