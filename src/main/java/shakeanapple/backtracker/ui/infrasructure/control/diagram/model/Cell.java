package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Cell implements DiagramCell{
    private String name;
    private String type;

    private Map<String, InputPin> inputPins;
    private Map<String, OutputPin> outputPins;

    public Cell(String name, String type, List<String> inputPinsNames, List<String> outputPinsNames, Function<Pin, Boolean> pinClickHandler) {
        this.name = name;
        this.type = type;
        this.inputPins = inputPinsNames.stream().map(pinName -> new InputPin(this, pinName, pinClickHandler)).collect(Collectors.toMap(Pin::getName, pin -> pin));
        this.outputPins = outputPinsNames.stream().map(pinName -> new OutputPin(this, pinName, pinClickHandler)).collect(Collectors.toMap(Pin::getName, pin -> pin));
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return this.type;
    }


    public Map<String, InputPin> getInputPins() {
        return inputPins;
    }

    public Map<String, OutputPin> getOutputPins() {
        return outputPins;
    }
}
