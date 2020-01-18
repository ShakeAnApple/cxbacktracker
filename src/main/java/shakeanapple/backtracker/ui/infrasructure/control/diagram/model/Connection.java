package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import shakeanapple.backtracker.common.variable.ValueHolder;

public class Connection {
    private final OutputPin from;
    private final InputPin to;

    private final String id;

    private final boolean isInverted;
    private Property<ValueHolder> value;
    private Property<Boolean> isCause;

    public Connection(OutputPin from, InputPin to, ValueHolder value, boolean isInverted) {
        this.from = from;
        this.to = to;
        this.isInverted = isInverted;
        if (this.isInverted){
            this.to.invert();
        }

        this.isCause = new SimpleObjectProperty<>(false);
        this.value = new SimpleObjectProperty<>(value);

        this.id = this.makeId();
    }

    private String makeId() {
        String id = this.from.getOwner().getName();
        if (!this.from.getOwner().getName().equals(this.from.getName())){
            id += this.from.getName();
        }
        id += this.to.getOwner().getName();
        if (!this.to.getOwner().getName().equals(this.to.getName())){
            id += this.to.getName();
        }
        return id;
    }

    public void isCauseTreeEdge(boolean isCauseTreeEdge){
        this.isCause.setValue(isCauseTreeEdge);
        if (!isCauseTreeEdge){
            this.from.getCausesObservable().clear();
            this.to.getCausesObservable().clear();
        }
    }

    public Property<Boolean> isCauseEdgeProperty() {
        return this.isCause;
    }

    public ObservableValue<ValueHolder> getValueProperty() {
        return value;
    }

    public void updateValue(ValueHolder value) {
        this.value.setValue(value);
        this.from.updateValue(value);
        this.to.updateValue(value);
    }

    public Pin getFrom() {
        return from;
    }

    public Pin getTo() {
        return to;
    }

    public String getId() {
        return this.id;
    }
}
