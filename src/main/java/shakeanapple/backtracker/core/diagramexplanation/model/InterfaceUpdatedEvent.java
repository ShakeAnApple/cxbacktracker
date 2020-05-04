package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;

import java.util.*;

public class InterfaceUpdatedEvent implements Event {

    private List<InterfaceUpdatedListener> listeners;

    private final Map<String, Gate> gates;
    private Map<String, Gate> updatedGates;

    private boolean isInputInterfaceWatched;

    public InterfaceUpdatedEvent(Map<String, Gate> gates, boolean isInputInterfaceWatched) {
        this.listeners = new ArrayList<>();

        this.gates = gates;
        this.updatedGates = new HashMap<>();

        this.isInputInterfaceWatched = isInputInterfaceWatched;
    }

    public void tryFire(Gate gate){
        this.updatedGates.put(gate.getName(), this.gates.get(gate.getName()));
        // Here because after initialization gates can gain connections
        if (this.updatedGates.size() == this.gates.values().stream().filter(g -> (g.getIncomingConnection() != null) || (g instanceof OutputGate && g.getOwner() instanceof FunctionBlockBasic)).count()){
            this.fire();
        }
    }

    private void fire(){
        this.updatedGates.clear();
        for (InterfaceUpdatedListener listener : this.listeners) {
            if (this.isInputInterfaceWatched) {
                listener.onInputInterfaceUpdated();
            } else{
                listener.onOutputInterfaceUpdated();
            }
        }
//        for (InputGate inGate : this.inGates.values().stream().filter(in -> in.getIncomingConnection() == null).collect(Collectors.toList())){
//            inGate.propagateValue();
//        }
    }

    @Override
    public void addListener(EventListener listener) {
        this.listeners.add((InterfaceUpdatedListener) listener);
    }
}
