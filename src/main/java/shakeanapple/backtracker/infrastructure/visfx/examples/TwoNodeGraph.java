package shakeanapple.backtracker.infrastructure.visfx.examples;

import shakeanapple.backtracker.infrastructure.visfx.api.VisFx;
import shakeanapple.backtracker.infrastructure.visfx.graph.VisEdge;
import shakeanapple.backtracker.infrastructure.visfx.graph.VisGraph;
import shakeanapple.backtracker.infrastructure.visfx.graph.VisNode;
import javafx.application.Application;
import javafx.stage.Stage;

public class TwoNodeGraph extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VisFx.graphNetwork(getDummyGraph(),primaryStage);
    }

    private VisGraph getDummyGraph(){
        VisGraph graph = new VisGraph();
        VisNode node1 = new VisNode(1,"a");
        VisNode node2 = new VisNode(2,"b");
        VisEdge edge = new VisEdge(node1,node2,"to","part_of");
        graph.addNodes(node1,node2);
        graph.addEdges(edge);
        return graph;
    }
}
