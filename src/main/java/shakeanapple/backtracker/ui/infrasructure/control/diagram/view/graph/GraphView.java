package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph;

import javafx.scene.Group;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.*;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.layout.ConnectionLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GraphView extends Group {
    private List<DiagramCellView> nodes;
    private List<ConnectionView> connections;

    private Graph graph;

    private GraphView() {
        this.nodes = new ArrayList<>();
        this.connections = new ArrayList<>();
    }

    public static GraphView from(Graph graph) {
        GraphView graphView = new GraphView();
        graphView.graph = graph;

        Map<Pin, Long> idsForPinViews = new HashMap<>();
        Map<Long, PinView> pinViewsById = new HashMap<>();
        for (DiagramCell cell : graph.getCells()) {

            DiagramCellView cellView;
            if (cell instanceof Cell){
                cellView = new CellView(graphView, (Cell)cell);
            } else if (cell instanceof InputInterfaceCell){
                cellView = new InputInterfaceCellView(graphView, (InputInterfaceCell) cell);
            } else if (cell instanceof OutputInterfaceCell){
                cellView = new OutputInterfaceCellView(graphView, (OutputInterfaceCell) cell);
            } else{
                throw new RuntimeException("Cell type is not supported by view");
            }
            graphView.nodes.add(cellView);
            
            idsForPinViews.putAll(cellView.getInputs().stream().collect(Collectors.toMap(PinView::getPin, InputPinView::getViewId)));
            pinViewsById.putAll(cellView.getInputs().stream().collect(Collectors.toMap(PinView::getViewId, in -> in)));


            idsForPinViews.putAll(cellView.getOutputs().stream().collect(Collectors.toMap(PinView::getPin, OutputPinView::getViewId)));
            pinViewsById.putAll(cellView.getOutputs().stream().collect(Collectors.toMap(PinView::getViewId, in -> in)));

        }

        for (Connection connection : graph.getConnections()) {
            PinView from = pinViewsById.get(idsForPinViews.get(connection.getFrom()));
            PinView to = pinViewsById.get(idsForPinViews.get(connection.getTo()));

            to.getOwner().getParents().add(from.getOwner());

            ConnectionView connView = new ConnectionView(graphView, connection, from, to);
            graphView.connections.add(connView);
        }
        return graphView;
    }

    public List<DiagramCellView> getNodes() {
        return nodes;
    }

    public List<ConnectionView> getConnections() {
        return connections;
    }
}
