package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.Clocks;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Gate extends DiagramElement {

    private FunctionBlockBase owner;
    private Connection incomingConnection;
    private List<Connection> outgoingConnections;

    private int gateTime;

    private String fullName;

    private InputUpdatedEvent inputUpdatedEvent;
    private OutputUpdatedEvent outputUpdatedEvent;

    public Gate(String name, String type, FunctionBlockBase owner) {
        super(name, type);

        this.gateTime = 0;
        this.outgoingConnections = new ArrayList<>();
        this.inputUpdatedEvent = new InputUpdatedEvent();
        this.outputUpdatedEvent = new OutputUpdatedEvent();
        this.owner = owner;

        this.fullName = (this.owner.getStringPathInSystem() == null || this.owner.getStringPathInSystem().isEmpty() ? "" : (this.owner.getStringPathInSystem() + "."))
                + (this.owner.isRoot() || this.owner instanceof FunctionBlockBasic ? "" : (this.owner.getName() + ".")) + this.getName();
    }

    public FunctionBlockBase getOwner(){
        return this.owner;
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

    public Event inputUpdatedEvent(){
        return this.inputUpdatedEvent;
    }

    public void populateInput(ValueHolder value) {
        if (this.gateTime < this.owner.getClocks().currentTime()){
            this.input().setValue(value);
            this.inputUpdatedEvent.fire(this);
            this.outputUpdatedEvent.fire(this);
            this.gateTime = this.owner.getClocks().currentTime();
            this.propagateValue();
        } else{
            this.delayPropagation(value);
        }
    }

    private void delayPropagation(ValueHolder value) {
        this.owner.getClocks().onceOnNextTick(() -> {
            this.populateInput(value);});
    }

    public ValueHolder getValue(){
        return this.output().getValue();
    }

    public void propagateValue(){
        for (Connection connection: this.getOutgoingConnections()) {
            if (connection.isInverted()){
                connection.toGate().populateInput(this.output().getValue().invert());
            } else {
                connection.toGate().populateInput(this.output().getValue());
            }
        }
    }

    public Connection getIncomingConnection() {
        return this.incomingConnection;
    }

    public OutputUpdatedEvent outputUpdatedEvent() {
        return this.outputUpdatedEvent;
    }

    public String getFullName(){
//        this.fullName = (this.owner.getStringPathInSystem() == null || this.owner.getStringPathInSystem().isEmpty() ? "" : (this.owner.getStringPathInSystem() + "."))
//                + (this.owner.isRoot() || this.owner instanceof FunctionBlockBasic ? "" : (this.owner.getName() + ".")) + this.getName();
//
        this.fullName = (this.owner.getStringPathInSystem() == null || this.owner.getStringPathInSystem().isEmpty() ? "" : (this.owner.getStringPathInSystem() + "."))
                + (this.owner.isRoot() ? "" : (this.owner.getName() + ".")) + this.getName();
        return this.fullName;
    }

    public String getFullNameInContext(String contextBlockName) {
        if (!contextBlockName.equals("main")) {
            return this.fullName.substring(this.fullName.indexOf(contextBlockName) + contextBlockName.length() + 1);
        }
        return this.fullName;
    }
}
