package shakeanapple.backtracker.ui.infrasructure.control.diagramold;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import shakeanapple.backtracker.ui.explainer.model.Cause;
import shakeanapple.backtracker.ui.explainer.model.graph.HierarchicalLayout;
import shakeanapple.backtracker.ui.explainer.model.graph.cell.*;
import shakeanapple.backtracker.ui.infrasructure.control.diagramold.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class DiagramControl extends BorderPane {

    private Panel panel;

    private boolean isClear = true;

    public DiagramControl() {
        this.panel = new Panel();
        super.setCenter(this.panel.getScrollPane());
    }

    public boolean isClear() {
        return this.isClear;
    }

    public void draw(ViewGraph graph) {
        this.isClear = false;

        for (Cell cell : graph.getCells()) {
            this.panel.getGraph().addCell(cell);
        }

        for (DiagramConnection conn : graph.getConnections()) {
            this.panel.getGraph().addEdge(conn);
        }

        this.panel.update();

        //Layout layout = new RandomLayout(this.panel);
        Layout layout = new HierarchicalLayout(this.panel);
        layout.execute();

    }

    public void update(ViewGraph diagram) {

    }

    public void clear() {
        this.panel.clear();
        this.isClear = true;
    }

    // Helper method
    private String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    private String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }

    public void colorPins(Map<String, List<String>> pinsByBlocks) {
        this.panel.getGraph().getAllCells().stream().filter(cell -> pinsByBlocks.containsKey(((ExplainerCell) cell).getName()))
                .forEach(cell -> {
                    ExplainerCell expCell = (ExplainerCell) cell;
                    List<String> blockPinsToColor = pinsByBlocks.get(expCell.getName());
                    for (String pin : blockPinsToColor) {
                        if (expCell.getInputPins().containsKey(pin)) {
                            expCell.getInputPins().get(pin).colorBorder(this.toHexString(Color.RED));
                        } else if (expCell.getOutputPins().containsKey(pin)) {
                            expCell.getOutputPins().get(pin).colorBorder(this.toHexString(Color.RED));
                        }
                    }
                });

        List<String> rootPins = pinsByBlocks.get("root");
        this.panel.getGraph().getAllCells().stream().filter(cell -> cell instanceof InputVarCell && rootPins.contains(((InputVarCell) cell).getName()))
                .forEach(cell -> {
                    InputVarCell expCell = (InputVarCell) cell;
                    expCell.getOutputPin().colorBorder(this.toHexString(Color.RED));
                });

        this.panel.getGraph().getAllCells().stream().filter(cell -> cell instanceof OutputVarCell && rootPins.contains(((OutputVarCell) cell).getName()))
                .forEach(cell -> {
                    OutputVarCell expCell = (OutputVarCell) cell;
                    expCell.getInputPin().colorBorder(this.toHexString(Color.RED));
                });
    }

    public void resetPins() {
        this.panel.getGraph().getAllCells()
                .forEach(cell -> {
                    ExplainerCell expCell = (ExplainerCell) cell;
                    expCell.getInputPins().values().forEach(in -> {
                        in.usualColor();
                        in.addTextToTooltip("");
                    });
                    expCell.getOutputPins().values().forEach(out -> {
                        out.usualColor();
                        out.addTextToTooltip("");
                    });
                });
    }

    public void addTooltipsToPins(Map<String, List<Cause>> causesPinsByBlocks) {
        this.panel.getGraph().getAllCells().stream().filter(cell -> causesPinsByBlocks.containsKey(((ExplainerCell) cell).getName()))
                .forEach(cell -> {
                    ExplainerCell expCell = (ExplainerCell) cell;
                    List<Cause> blockCauses = causesPinsByBlocks.get(expCell.getName());
                    Map<String, List<Cause>> blockCausesByVars = new HashMap<>();
                    for (Cause cause : blockCauses) {
                        if (!blockCausesByVars.containsKey(cause.getVarName())) {
                            blockCausesByVars.put(cause.getVarName(), new ArrayList<>());
                        }
                        blockCausesByVars.get(cause.getVarName()).add(cause);
                    }
                    for (String pin : blockCausesByVars.keySet()) {
                        if (expCell.getInputPins().containsKey(pin)) {
                            expCell.getInputPins().get(pin).colorBorder(this.toHexString(Color.RED));
                            String pinText = blockCausesByVars.get(pin).stream()
                                    .distinct()
                                    .sorted(Comparator.comparing(Cause::getTimestamp))
                                    .map(cause -> (cause.getTimestamp() - 1) + ":" + cause.getValue().toString())
                                    .collect(Collectors.joining("\n"));
                            expCell.getInputPins().get(pin).addTextToTooltip(pinText);
                        } else if (expCell.getOutputPins().containsKey(pin)) {
                            expCell.getOutputPins().get(pin).colorBorder(this.toHexString(Color.RED));
                            String pinText = blockCausesByVars.get(pin).stream()
                                    .distinct()
                                    .sorted(Comparator.comparing(Cause::getTimestamp))
                                    .map(cause -> (cause.getTimestamp()-1) + ":" + cause.getValue().toString())
                                    .collect(Collectors.joining("\n"));
                            expCell.getOutputPins().get(pin).addTextToTooltip(pinText);
                        }
                    }
                });

        List<String> rootPinsNames = causesPinsByBlocks.get("root").stream().map(Cause::getVarName).collect(Collectors.toList());
        this.panel.getGraph().getAllCells().stream().filter(cell -> cell instanceof InputVarCell && rootPinsNames.contains(((InputVarCell) cell).getName()))
                .forEach(cell -> {
                    InputVarCell expCell = (InputVarCell) cell;
                    String pinText = causesPinsByBlocks.get("root").stream().filter(cause -> cause.getVarName().equals(expCell.getName()))
                            .distinct()
                            .sorted(Comparator.comparing(Cause::getTimestamp))
                            .map(cause -> (cause.getTimestamp() - 1) + ":" + cause.getValue().toString())
                            .collect(Collectors.joining("\n"));
                    expCell.getOutputPin().addTextToTooltip(pinText);
                });

        this.panel.getGraph().getAllCells().stream().filter(cell -> (cell instanceof OutputVarCell) && rootPinsNames.contains(((OutputVarCell) cell).getName()))
                .forEach(cell -> {
                    OutputVarCell expCell = (OutputVarCell) cell;
                    String pinText = causesPinsByBlocks.get("root").stream().filter(cause -> cause.getVarName().equals(expCell.getName()))
                            .distinct()
                            .sorted(Comparator.comparing(Cause::getTimestamp))
                            .map(cause -> (cause.getTimestamp() - 1) + ":" + cause.getValue().toString())
                            .collect(Collectors.joining("\n"));
                    expCell.getInputPin().addTextToTooltip(pinText);
                });
    }

}