package shakeanapple.backtracker.ui;

import shakeanapple.backtracker.core.ltlcalculation.model.*;
import shakeanapple.backtracker.infrastructure.visfx.graph.VisEdge;
import shakeanapple.backtracker.infrastructure.visfx.graph.VisGraph;
import shakeanapple.backtracker.infrastructure.visfx.graph.VisNode;

import java.util.*;

public class TreeHelper {

    public static void flatten(VisNode parent, ICalculatedNode child, Random r, List<VisNode> nodes, List<VisEdge> edges){
        VisNode visChild = null;
        try {
            visChild = new VisNode(r.nextLong(), child.getNode().getName() + " " + child.getResult() + " for step: " + child.getResult().forStep(), getColorFor(child.getResult()));
        }
        catch (Exception e){
            int a = 1;
        }
        nodes.add(visChild);

        if (parent != null) {
            VisEdge edge = new VisEdge(parent, visChild, "to");
            edges.add(edge);
        }

        for (ICalculatedNode newChild : child.getChildren()) {
            flatten(visChild, newChild, r, nodes, edges);
        }
    }


    public static VisGraph convertToGraph(ICalculatedFormula f){
        Random r = new Random();

        List<VisNode> nodes = new ArrayList<>();
        List<VisEdge> edges = new ArrayList<>();

        flatten(null, f.getRoot(), r, nodes, edges);

        VisGraph graph = new VisGraph(nodes, edges);
        return graph;
    }

    public static String getColorFor(CalculationResult res){
        if (res instanceof LogicalResult){
            LogicalResult lr = (LogicalResult)res;
            switch (lr.getValue()) {
                case FALSE: return "#FF9999";
                case TRUE: return "#CCFF99";
                // dark gray for unknown
                default: return "#A0A0A0";
            }
        }
        // light gray for arithmetic res
        return "#E0E0E0";
    }
}
