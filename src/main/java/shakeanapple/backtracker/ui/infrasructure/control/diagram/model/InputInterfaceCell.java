package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InputInterfaceCell implements DiagramCell{
    private String name;

    private OutputPin outputPin;

    public InputInterfaceCell(String name, Function<Pin, Boolean> pinClickHandler) {
        this.name = name;
        this.outputPin = new OutputPin(this, name, pinClickHandler);
    }

    public String getName() {
        return name;
    }


    public Map<String, InputPin> getInputPins() {
        return new HashMap<>();
    }

    public Map<String, OutputPin> getOutputPins() {
        HashMap<String, OutputPin> out = new HashMap<>();
        out.put(this.outputPin.getName(), this.outputPin);
        return out;
    }
}
