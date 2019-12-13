package shakeanapple.backtracker.ui.infrasructure.control.diagram;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import shakeanapple.backtracker.ui.explainer.model.graph.HierarchicalLayout;
import shakeanapple.backtracker.ui.explainer.model.graph.cell.ExplainerCell;
import shakeanapple.backtracker.ui.explainer.model.graph.cell.InputPin;
import shakeanapple.backtracker.ui.explainer.model.graph.cell.Pin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.*;

import java.util.List;
import java.util.Map;

public class DiagramControl extends BorderPane {

    private Panel panel;

    private boolean isClear = true;

    public DiagramControl() {
        this.panel = new Panel();
        super.setCenter(this.panel.getScrollPane());
    }

    public boolean isClear(){
        return this.isClear;
    }

    public void draw(ViewGraph graph){
        this.isClear = false;

        for(Cell cell : graph.getCells()){
            this.panel.getGraph().addCell(cell);
        }

        for(DiagramConnection conn : graph.getConnections()){
            this.panel.getGraph().addEdge(conn);
        }

        this.panel.update();

        //Layout layout = new RandomLayout(this.panel);
        Layout layout = new HierarchicalLayout(this.panel);
        layout.execute();

    }

    public void update(ViewGraph diagram){

    }

    public void clear(){
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
        this.panel.getGraph().getAllCells().stream().filter(cell -> pinsByBlocks.containsKey(((ExplainerCell)cell).getName()))
                .forEach(cell ->{
                    ExplainerCell expCell = (ExplainerCell) cell;
                    List<String> blockPinsToColor = pinsByBlocks.get(expCell.getName());
                    for (String pin : blockPinsToColor){
                        if (expCell.getInputPins().containsKey(pin)){
                            expCell.getInputPins().get(pin).colorBorder(this.toHexString(Color.RED));
                        } else if (expCell.getOutputPins().containsKey(pin)){
                            expCell.getOutputPins().get(pin).colorBorder(this.toHexString(Color.RED));
                        }
                    }
                });
    }

    public void resetPinsColor(){
        this.panel.getGraph().getAllCells()
                .forEach(cell ->{
                    ExplainerCell expCell = (ExplainerCell) cell;
                    expCell.getInputPins().values().forEach(InputPin::usualColor);
                    expCell.getOutputPins().values().forEach(Pin::usualColor);
                });
    }
}
