package shakeanapple.backtracker.ui;

import javafx.scene.paint.Color;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.ConnectionSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.FunctionBlockSnapshot;
import shakeanapple.backtracker.core.ltlexplanation.model.*;
import shakeanapple.backtracker.ui.control.diagram.Connection;
import shakeanapple.backtracker.ui.control.diagram.ViewGraph;
import shakeanapple.backtracker.ui.control.diagram.model.Cell;
import shakeanapple.backtracker.ui.control.diagram.model.InputCell;
import shakeanapple.backtracker.ui.control.diagram.model.OutputCell;
import shakeanapple.backtracker.ui.control.diagram.model.RectangleCell;
import shakeanapple.backtracker.ui.control.visgraph.visfx.graph.VisEdge;
import shakeanapple.backtracker.ui.control.visgraph.visfx.graph.VisGraph;
import shakeanapple.backtracker.ui.control.visgraph.visfx.graph.VisNode;

import java.util.*;

public class GraphHelper {

    public static void flatten(VisNode parent, ICalculatedNode child, Random r, List<VisNode> nodes, List<VisEdge> edges) {
        VisNode visChild = null;
        visChild = new VisNode(r.nextLong(), child.getNode().getName() + " " + child.getResult() + " for step: " + child.getResult().forStep(), getColorFor(child.getResult()));
        nodes.add(visChild);

        if (parent != null) {
            VisEdge edge = new VisEdge(parent, visChild, "to");
            edges.add(edge);
        }

        for (ICalculatedNode newChild : child.getChildren()) {
            flatten(visChild, newChild, r, nodes, edges);
        }
    }


    public static VisGraph convertToGraph(ICalculatedFormula f) {
        Random r = new Random();

        List<VisNode> nodes = new ArrayList<>();
        List<VisEdge> edges = new ArrayList<>();

        flatten(null, f.getRoot(), r, nodes, edges);

        VisGraph graph = new VisGraph(nodes, edges);
        return graph;
    }

    public static String getColorFor(CalculationResult res) {
        if (res instanceof LogicalResult) {
            LogicalResult lr = (LogicalResult) res;
            switch (lr.getValue()) {
                case FALSE:
                    return "#FF9999";
                case TRUE:
                    return "#CCFF99";
                // dark gray for unknown
                default:
                    return "#A0A0A0";
            }
        }
        // light gray for arithmetic res
        return "#E0E0E0";
    }

    public static ViewGraph convertToDiagramGraph(DiagramSnapshot diagram) {
        Random r = new Random();

        Map<String, Cell> nodes = new HashMap<>();
        List<Connection> edges = new ArrayList<>();

//        for (String input : diagram.getDiagramInterface().getInputs()) {
//            long id = r.nextLong();
//            nodes.put(input, new InputCell(id, "input: " + input));
//        }
//
//        for (String output : diagram.getDiagramInterface().getOutputs()) {
//            long id = r.nextLong();
//            nodes.put(output, new OutputCell(id, "output: " + output));
//        }

        for (FunctionBlockSnapshot fblock : diagram.getBlocks()) {
            long id = r.nextLong();
            nodes.put(fblock.getName(), new RectangleCell(id, fblock.getName() + " " + fblock.getType()));
        }

        for (ConnectionSnapshot connection : diagram.getConnections()) {
            String blockNameFrom = connection.from().getName();
            Cell from = nodes.get(blockNameFrom);

            String blockNameTo = connection.to().getName();
            Cell to = nodes.get(blockNameTo);

            edges.add(new Connection(from.getCellId(), to.getCellId(), connection.fromVarName() + " -> " + connection.toVarName() + (connection.isInverted() ? " (inverted)" : ""),
                    getDiagramColorFor(connection.getValue())
            ));
        }

        ViewGraph graph = new ViewGraph(new ArrayList<>(nodes.values()), edges);
        return graph;
    }

    public static VisGraph convertToGraph(DiagramSnapshot diagram) {
//        Random r = new Random();
//
//        Map<String, VisNode> nodes = new HashMap<>();
//        List<VisEdge> edges = new ArrayList<>();
//
//        long id;
//        for (FunctionBlockSnapshot fblock : diagram.getBlocks()) {
//            if (fblock.isRoot()) {
//                for (String input : fblock.getInputs()) {
//                    id = r.nextLong();
//                    nodes.put(input, new VisNode(id, "output: " + input));
//                }
//                for (String output : fblock.getOutputs()) {
//                    id = r.nextLong();
//                    nodes.put(output, new VisNode(id, "input: " + output));
//                }
//            } else {
//                id = r.nextLong();
//                nodes.put(fblock.getName(), new VisNode(id, fblock.getName() + " " + fblock.getType()));
//            }
//        }
//
//        Map<String, List<ConnectionSnapshot>> connectionsByFromTo = new HashMap<>();
//        for (ConnectionSnapshot c : diagram.getConnections()) {
//            String fromName = "";
//            if (c.from().isRoot()) {
//                fromName = c.fromVarName();
//            } else {
//                fromName = c.from().getName();
//            }
//
//            String toName = "";
//            if (c.to().isRoot()) {
//                toName = c.toVarName();
//            } else {
//                toName = c.to().getName();
//            }
//
//            String key = fromName + toName;
//            if (!connectionsByFromTo.containsKey(key)) {
//                connectionsByFromTo.put(key, new ArrayList<>());
//            }
//            connectionsByFromTo.get(key).add(c);
//        }
//
//        for (List<ConnectionSnapshot> connectionsGroup : connectionsByFromTo.values()) {
//            //double roundness = 0.2;
//            String newLine = "";
//            for (ConnectionSnapshot connection : connectionsGroup) {
//                String blockNameFrom = connection.from().getName();
//                VisNode from = null;
//                if (blockNameFrom.equals("main")) {
//                    from = nodes.get(connection.fromVarName());
//                } else {
//                    from = nodes.get(blockNameFrom);
//                }
//
//                String blockNameTo = connection.to().getName();
//                VisNode to = null;
//                if (blockNameTo.equals("main")) {
//                    to = nodes.get(connection.toVarName());
//                } else {
//                    to = nodes.get(blockNameTo);
//                }
//
//                edges.add(new VisEdge(from, to, "to",
//                        newLine + connection.fromVarName() + " -> " + connection.toVarName() + (connection.isInverted() ? " (inverted)" : ""),
//                        getColorFor(connection.getValue())
//                ));
//                newLine += "\n\n\n";
////                edges.add(new VisEdge(from, to, "to",
////                        connection.fromVarName() + " -> " + connection.toVarName() + (connection.isInverted() ? " (inverted)" : ""),
////                        getColorFor(connection.getValue()), roundness
////                ));
////                if (roundness > 0){
////                    roundness = 0 - roundness;
////                } else if (roundness < 0){
////                    roundness = (0 - roundness) + 0.2;
////                }
//            }
//        }
//
//        VisGraph graph = new VisGraph(new ArrayList<>(nodes.values()), edges);
//        return graph;
        return null;
    }

    private static String getColorFor(ValueHolder valueHolder) {
        if (valueHolder instanceof BooleanValueHolder) {
            BooleanValueHolder bvh = (BooleanValueHolder) valueHolder;
            return bvh.getValue() ? "green" : "red";
        }
        return "#E0E0E0";
    }

    private static Color getDiagramColorFor(ValueHolder valueHolder) {
        if (valueHolder instanceof BooleanValueHolder) {
            BooleanValueHolder bvh = (BooleanValueHolder) valueHolder;
            return bvh.getValue() ? Color.GREEN : Color.RED;
        }
        return Color.GRAY;
    }
}
