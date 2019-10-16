package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Gate extends DiagramElement {

    private Connection incomingConnection;

    private List<Connection> outgoingConnections;

    public Gate(String name, String type) {
        super(name, type);
        this.outgoingConnections = new ArrayList<>();
    }

    public abstract InputVariable input();

    public abstract OutputVariable output();

    public void makeIncomingConnection(Gate fromGate, DiagramElement from, DiagramElement to, boolean isInverted){
        this.incomingConnection = new Connection(isInverted, from, fromGate, to, this);
    }

    public void makeOutgoingConnection(Gate toGate, DiagramElement from, DiagramElement to, boolean isInverted){
        this.outgoingConnections.add(new Connection(isInverted, from, this, to, toGate));
    }

    public List<Connection> getOutgoingConnections(){
        return Collections.unmodifiableList(this.outgoingConnections);
    }

    public void populateInput(ValueHolder value) {
        this.input().setValue(value);
        this.propagateValue();
    }

    public ValueHolder getValue(){
        return this.output().getValue();
    }

    //TODO cycles!! (work \w time?)
    public void propagateValue(){
        for (Connection connection: this.getOutgoingConnections()) {
            if (connection.isInverted()){
                connection.toGate().populateInput(this.output().getValue().invert());
            } else {
                connection.toGate().populateInput(this.output().getValue());
            }
        }
    }
}
