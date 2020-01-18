package shakeanapple.backtracker.ui.infrasructure.control.diagram;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Graph;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.Canvas;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.HierarchicalLayout;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.Layout;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.RandomLayout;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.GraphView;

public class DiagramControl extends BorderPane {

    private Canvas canvas;

    private boolean isClear = true;

    public DiagramControl() {
        this.canvas = new Canvas(new Group());
        super.setCenter(this.canvas.getScrollPane());
    }

    public boolean isClear() {
        return this.isClear;
    }

    public void draw(Graph graph) {
        this.isClear = false;

        GraphView graphView = GraphView.from(graph);

//        for (Cell cell : graph.getCells()) {
//            this.panel.getGraph().addCell(cell);
//        }
//
//        for (DiagramConnection conn : graph.getConnections()) {
//            this.panel.getGraph().addEdge(conn);
//        }

        this.canvas.update(graphView);

        Layout layout = new HierarchicalLayout(this.canvas);
        layout.execute();

    }

//    public void updateConnections(List<DiagramConnection> connections) {
//        for (DiagramConnection conn : connections) {
//            this.panel.getGraph().addEdge(conn);
//        }
//
//        this.panel.update();
//    }

    public void clear() {
        this.canvas.clear();
        this.isClear = true;
    }
}
