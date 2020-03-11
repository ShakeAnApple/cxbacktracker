package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import shakeanapple.backtracker.common.variable.ValueHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InputInterfaceCell implements DiagramCell{
    private String name;

    private OutputPin outputPin;

    private String diagramOwnerName;

    public InputInterfaceCell(String name, String diagramOwnerName, Function<Pin, Boolean> pinClickHandler, ValueHolder initialValue) {
        this.name = name;
        this.outputPin = new OutputPin(this, name, pinClickHandler, initialValue);
        this.diagramOwnerName = diagramOwnerName;
    }

    public String getName() {
        return name;
    }

    public String getDiagramOwnerName() {
        return this.diagramOwnerName;
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
