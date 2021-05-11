package shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph;

import javafx.scene.Group;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphView extends Group {
    private CauseGraph graph;
    private List<GraphNodeView> nodes;
    private List<ConnectionView> connections;
    private GraphNodeView root;

    public GraphView(){
        this.nodes = new ArrayList<>();
        this.connections = new ArrayList<>();
    }

    public static GraphView from(CauseGraph graph) {

        GraphView graphView = new GraphView();
        graphView.graph = graph;

        Map<GraphNode, GraphNodeView> viewsByNodes = new HashMap<>();
        for (GraphNode node : graph.getCells()) {

            GraphNodeView nodeView;
            if (node instanceof ChangeNode){
                nodeView = new ChangeNodeView(graphView, (ChangeNode)node);
            } else if (node instanceof RemainNode){
                nodeView = new RemainNodeView(graphView, (RemainNode) node);
            } else{
                throw new RuntimeException("Cell type is not supported by view");
            }
            graphView.nodes.add(nodeView);
            viewsByNodes.put(node,nodeView);
            if (node.equals(graph.getRoot())){
                graphView.root = nodeView;
            }
        }

        for (Connection connection : graph.getConnections()) {
            GraphNodeView from = viewsByNodes.get(connection.from());
            GraphNodeView to = viewsByNodes.get(connection.to());

            from.addChild(to);

            ConnectionView connView = new ConnectionView(graphView, connection, from, to);
            graphView.connections.add(connView);
        }
        return graphView;

    }

    public GraphNodeView getRoot(){
        return this.root;
    }

    public List<GraphNodeView> getNodes() {
        return this.nodes;
    }

    public List<ConnectionView> getConnections(){
        return this.connections;
    }
}
