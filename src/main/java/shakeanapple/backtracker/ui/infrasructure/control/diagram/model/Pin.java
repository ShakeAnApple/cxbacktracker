package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.Diagram;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Pin {
    private DiagramCell owner;
    private String name;

    private ObservableList<Cause> causes;
    private Property<ValueHolder> valueProperty;

    private Function<Pin, Boolean> pinClickHandler;

    private String shortName;

    public Pin(DiagramCell owner, String name, Function<Pin, Boolean> pinClickHandler, ValueHolder initialValue) {
        this.owner = owner;
        this.name = name;

        this.causes = FXCollections.observableArrayList(new ArrayList<>());
        this.valueProperty = new SimpleObjectProperty<>(initialValue);
        this.pinClickHandler = pinClickHandler;

        String[] nameParts = this.name.split("\\.");
        this.shortName = nameParts.length > 0 ? nameParts[nameParts.length - 1] : this.name;
    }

    public Property<ValueHolder> valueProperty(){
        return this.valueProperty;
    }

    public Function<Pin, Boolean> getPinClickHandler() {
        return this.pinClickHandler;
    }

    public ObservableList<Cause> getCausesObservable(){
        return this.causes;
    }

    public boolean belongsToSystemInterface(){
        return (this.getOwner() instanceof InputInterfaceCell) || (this.getOwner() instanceof OutputInterfaceCell);
    }

    public DiagramCell getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public abstract void updateValue(ValueHolder value);
}
