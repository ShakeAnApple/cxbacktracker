package shakeanapple.backtracker.ui.infrasructure.control.diagram;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Graph;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.*;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.ConnectionView;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph.GraphView;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.layout.ConnectionLayoutManager;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.layout.HierarchicalLayout;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.layout.Layout;

import java.util.concurrent.Flow;

public class DiagramControl extends BorderPane {

    private Canvas canvas;

    private boolean isClear = true;

    public DiagramControl() {
        this.canvas = new Canvas(new Group());
        super.setCenter(this.canvas.getScrollPane());
        FlowPane zoomPane = new FlowPane();
        Button zoomInButton =new Button("+");
        Button zoomOutButton =new Button("-");
        zoomPane.getChildren().add(zoomInButton);
        zoomPane.getChildren().add(zoomOutButton);
        super.setTop(zoomPane);

        zoomInButton.onActionProperty().setValue((e) -> {
            this.canvas.zoomIn();
        });

        zoomOutButton.onActionProperty().setValue((e) -> {
            this.canvas.zoomOut();
        });

//        super.getChildren().add();
//        super.getChildren().add(new Button("-"));
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
        Platform.runLater(() -> {
            layout.execute();
            ConnectionLayoutManager connLayoutManager = new ConnectionLayoutManager(graphView, DiagramStyles.DIAGRAM_ELEMENTS_PADDING);
            for (ConnectionView cv: graphView.getConnections()){
                connLayoutManager.draw(cv);
            }
        });


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
