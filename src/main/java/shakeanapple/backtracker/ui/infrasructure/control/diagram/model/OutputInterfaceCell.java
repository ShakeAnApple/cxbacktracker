package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import shakeanapple.backtracker.common.variable.ValueHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class OutputInterfaceCell implements DiagramCell {
    private String name;

    private InputPin inputPin;

    private String diagramOwnerName;

    private String shortName;

    public OutputInterfaceCell(String name, String diagramOwnerName, Function<Pin, Boolean> pinClickHandler, ValueHolder initialValue) {
        this.name = name;
        this.inputPin = new InputPin(this, name, pinClickHandler, initialValue);

        this.diagramOwnerName = diagramOwnerName;

        String[] nameParts = this.name.split("\\.");
        this.shortName = nameParts.length > 0 ? nameParts[nameParts.length - 1] : this.name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public String getName() {
        return name;
    }

    public String getDiagramOwnerName() {
        return this.diagramOwnerName;
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
