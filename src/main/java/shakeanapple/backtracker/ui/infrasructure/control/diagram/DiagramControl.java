package shakeanapple.backtracker.ui.infrasructure.control.diagram;

import javafx.scene.layout.BorderPane;
import shakeanapple.backtracker.ui.explainer.model.graph.HierarchicalLayout;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.*;

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

}
