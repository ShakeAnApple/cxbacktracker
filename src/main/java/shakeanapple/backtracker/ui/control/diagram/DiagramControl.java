package shakeanapple.backtracker.ui.control.diagram;

import javafx.scene.layout.BorderPane;
import shakeanapple.backtracker.ui.control.diagram.model.*;

import java.util.List;

public class DiagramControl extends BorderPane {

    private Graph graph;

    public DiagramControl() {
        this.graph = new Graph();
        super.setCenter(this.graph.getScrollPane());
    }

    public void draw(ViewGraph graph){
        for(Cell cell : graph.getCells()){
            this.graph.getModel().addCell(cell);
        }

        for(Connection conn : graph.getConnections()){
            this.graph.getModel().addEdge(conn);
        }

        this.graph.update();

        Layout layout = new RandomLayout(this.graph);
        layout.execute();
    }
}
