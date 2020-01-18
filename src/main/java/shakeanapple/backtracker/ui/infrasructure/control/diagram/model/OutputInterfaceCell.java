package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class OutputInterfaceCell implements DiagramCell {
    private String name;

    private InputPin inputPin;

    public OutputInterfaceCell(String name, Function<Pin, Boolean> pinClickHandler) {
        this.name = name;
        this.inputPin = new InputPin(this, name, pinClickHandler);
    }

    public String getName() {
        return name;
    }


    public Map<String, InputPin> getInputPins() {
        HashMap<String, InputPin> out = new HashMap<>();
        out.put(this.inputPin.getName(), this.inputPin);
        return out;
    }

    public Map<String, OutputPin> getOutputPins() {
        return new HashMap<>();

    }
}
