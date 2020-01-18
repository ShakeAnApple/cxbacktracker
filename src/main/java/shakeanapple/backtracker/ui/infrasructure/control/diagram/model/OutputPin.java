package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import shakeanapple.backtracker.common.variable.ValueHolder;

import java.util.function.Function;

public class OutputPin extends Pin {
    public OutputPin(DiagramCell owner, String name, Function<Pin, Boolean> pinClickHandler) {
        super(owner, name, pinClickHandler);
    }

    @Override
    public void updateValue(ValueHolder value) {
        super.valueProperty().setValue(value);
    }
}
