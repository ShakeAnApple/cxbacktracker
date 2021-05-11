package shakeanapple.backtracker.ui.infrasructure.control.causegraph;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.model.CauseGraph;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph.GraphView;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.Canvas;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.DiagramStyles;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph.ConnectionView;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.layout.ConnectionLayoutManager;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.layout.HierarchicalLayout;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.layout.Layout;

public class CauseGraphControl extends BorderPane {

    private Canvas canvas;

    private boolean isClear = true;

    public CauseGraphControl() {
        this.canvas = new Canvas(new Group());
        this.canvas.setStyle("-fx-background-color:white");
        super.setCenter(this.canvas.getScrollPane());
        FlowPane zoomPane = new FlowPane();
        Button zoomInButton = new Button("+");
        Button zoomOutButton = new Button("-");
        zoomPane.getChildren().add(zoomInButton);
        zoomPane.getChildren().add(zoomOutButton);
        super.setTop(zoomPane);
        super.setStyle("-fx-background-color:white");
        this.setStyle("-fx-background-color:white");

        zoomInButton.onActionProperty().setValue((e) -> {
            this.canvas.zoomIn();
        });

        zoomOutButton.onActionProperty().setValue((e) -> {
            this.canvas.zoomOut();
        });
    }

    public boolean isClear() {
        return this.isClear;
    }

    public void draw(CauseGraph graph) {
        this.isClear = false;

        GraphView graphView = GraphView.from(graph);

        this.canvas.update(graphView);
        Layout layout = new HierarchicalLayout(this.canvas);
        layout.execute();
        Platform.runLater(() -> {
            layout.execute();
            ConnectionLayoutManager connLayoutManager = new ConnectionLayoutManager(graphView, DiagramStyles.DIAGRAM_ELEMENTS_PADDING);
            for (ConnectionView cv : graphView.getConnections()) {
                connLayoutManager.draw(cv);
            }
        });


    }

    public void clear() {
        this.canvas.clear();
        this.isClear = true;
    }
}
