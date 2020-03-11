package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;

import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Cell implements DiagramCell{
    private String name;
    private String type;

    private Map<String, InputPin> inputPins;
    private Map<String, OutputPin> outputPins;

    private Function<String, Boolean> cellClickHandler;

    public Cell(String name, String type, Map<String, ValueHolder> inputPins, Map<String, ValueHolder> outputPins,
                Function<Pin, Boolean> pinClickHandler,
                Function<String, Boolean> cellClickHandler) {
        this.name = name;
        this.type = type;
        this.cellClickHandler = cellClickHandler;

        this.inputPins = inputPins.keySet().stream().map(pinName -> new InputPin(this, pinName, pinClickHandler,
                inputPins.get(pinName) instanceof BooleanValueHolder ? new BooleanValueHolder(false) : new IntegerValueHolder(Integer.MIN_VALUE)
        )).collect(Collectors.toMap(Pin::getName, pin -> pin));
        this.outputPins = outputPins.keySet().stream().map(pinName -> new OutputPin(this, pinName, pinClickHandler,
                outputPins.get(pinName) instanceof BooleanValueHolder ? new BooleanValueHolder(false) : new IntegerValueHolder(Integer.MIN_VALUE)
        )).collect(Collectors.toMap(Pin::getName, pin -> pin));
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

    public Function<String, Boolean> getCellClickHandler() {
        return this.cellClickHandler;
    }
}
