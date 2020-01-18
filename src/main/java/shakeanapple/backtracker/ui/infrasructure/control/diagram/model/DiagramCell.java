package shakeanapple.backtracker.ui.infrasructure.control.diagram.model;


import java.util.Map;

public interface DiagramCell {
    Map<String, InputPin> getInputPins();

    Map<String, OutputPin> getOutputPins();

    String getName();
}
