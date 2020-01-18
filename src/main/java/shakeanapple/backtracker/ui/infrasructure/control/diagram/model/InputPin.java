package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import shakeanapple.backtracker.common.variable.ValueHolder;

import java.util.function.Function;

public class InputPin extends Pin {
    private Property<Boolean> isInverted;


    public InputPin(DiagramCell owner, String name, Function<Pin, Boolean> pinClickHandler) {
        super(owner, name, pinClickHandler);

        this.isInverted = new SimpleObjectProperty<>(false);
    }

    public void invert(){
        this.isInverted.setValue(true);
    }

    @Override
    public void updateValue(ValueHolder value){
        if (this.isInverted.getValue()){
            super.valueProperty().setValue(value.invert());
        } else {
            super.valueProperty().setValue(value);
        }
    }

    public Property<Boolean> isInvertedProperty() {
        return this.isInverted;
    }

}
