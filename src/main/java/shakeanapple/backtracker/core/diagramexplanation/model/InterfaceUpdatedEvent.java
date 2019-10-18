package shakeanapple.backtracker.core.diagramexplanation.model;

import java.util.*;
import java.util.stream.Collectors;

public class InterfaceUpdatedEvent implements Event {

    private List<InterfaceUpdatedListener> listeners;

    private final Map<String, InputGate> inGates;
    private Map<String, InputGate> updatedGates;

    public InterfaceUpdatedEvent(Map<String, InputGate> inGates) {
        this.listeners = new ArrayList<>();

        this.inGates = inGates;
        this.updatedGates = new HashMap<>();
    }

    public void tryFire(Gate gate){
        this.updatedGates.put(gate.getName(), this.inGates.get(gate.getName()));
        // Here because after initialization gates can gain connections
        if (this.updatedGates.size() == this.inGates.values().stream().filter(in -> in.getIncomingConnection() != null).count()){
            this.fire();
        }
    }

    private void fire(){
        this.updatedGates.clear();
        for (InterfaceUpdatedListener listener : this.listeners) {
            listener.onInterfaceUpdated();
        }
        for (InputGate inGate : this.inGates.values().stream().filter(in -> in.getIncomingConnection() == null).collect(Collectors.toList())){
            inGate.propagateValue();
        }
    }

    @Override
    public void addListener(EventListener listener) {
        this.listeners.add((InterfaceUpdatedListener) listener);
    }
}
