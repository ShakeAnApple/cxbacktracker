package shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph;

import javafx.scene.Group;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.model.*;

import java.util.*;

public class GraphView extends Group {
    private CauseGraph graph;
    private List<GraphNodeView> nodes;
    private Map<String, ConnectionView> connections;
    private GraphNodeView root;

    private Collection<GraphNodeView> selectedNodes;
    private Collection<ConnectionView> selectedConnections;

    public GraphView(){
        this.nodes = new ArrayList<>();
        this.connections = new HashMap<>();
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
            graphView.connections.put(connView.toString(), connView);
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
        return new ArrayList<>(this.connections.values());
    }

    public void highlightSubTreeWithRoot(GraphNodeView node) {
        Set<GraphNodeView> processedNodes = new HashSet<>();
        this.selectedConnections = new ArrayList<>();
        node.isSelected(true);
        processedNodes.add(node);
        this.highlightChildren(node, processedNodes);
        this.selectedNodes = processedNodes;
    }

    private void highlightChildren(GraphNodeView node, Set<GraphNodeView> processedNodes){
        for(GraphNodeView child: node.getChildren()){
            if (!processedNodes.contains(child)){
                processedNodes.add(child);
                child.isSelected(true);
                ConnectionView connectionView = this.connections.get(node.getName() + child.getName());
                connectionView.isSelected(true);
                this.selectedConnections.add(connectionView);
                this.highlightChildren(child, processedNodes);
            }
        }
    }

    public void clearHighlighting(){
        if (this.selectedNodes != null) {
            for (GraphNodeView node : this.selectedNodes) {
                node.isSelected(false);
            }
            this.selectedNodes.clear();
        }

        if (this.selectedConnections != null) {
            for (ConnectionView connectionView : this.selectedConnections) {
                connectionView.isSelected(false);
            }
            this.selectedConnections.clear();
        }
    }
}
