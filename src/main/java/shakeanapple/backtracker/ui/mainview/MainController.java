package shakeanapple.backtracker.ui.mainview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import shakeanapple.backtracker.infrastructure.visfx.graph.VisEdge;
import shakeanapple.backtracker.infrastructure.visfx.graph.VisGraph;
import shakeanapple.backtracker.infrastructure.visfx.graph.VisNode;
import shakeanapple.backtracker.ui.controls.VisGraphControl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainController {

    @FXML
    private VisGraphControl visGraph;

    public MainController()  {
    }

    @FXML
    protected void updateGraph() {
        this.visGraph.updateGraph(getDummyGraph());
    }

    private VisGraph getDummyGraph(){
        VisGraph graph = new VisGraph();
        VisNode father = new VisNode(1,"Mike");
        VisNode mother = new VisNode(2,"Delia");
        VisNode firstSon = new VisNode(3,"Tim");
        VisNode secondSon = new VisNode(4,"Andrew");
        VisNode firstDaughter = new VisNode(5,"Gina");
        VisNode firstSonSon = new VisNode(6,"Luke");
        VisEdge edge1 = new VisEdge(father,mother,"to;from","married");
        VisEdge edge2 = new VisEdge(father,firstSon,"to","father_to");
        VisEdge edge3 = new VisEdge(father,secondSon,"to","father_to");
        VisEdge edge4 = new VisEdge(father,firstDaughter,"to","father_to");
        VisEdge edge5 = new VisEdge(mother,firstSon,"to","mother_to");
        VisEdge edge6 = new VisEdge(mother,secondSon,"to","mother_to");
        VisEdge edge7 = new VisEdge(mother,firstDaughter,"to","mother_to");
        graph.addNodes(father,mother,firstSon,secondSon,firstDaughter,firstSonSon);
        graph.addEdges(edge1,edge2,edge3,edge4,edge5,edge6,edge7);
        return graph;
    }
}
