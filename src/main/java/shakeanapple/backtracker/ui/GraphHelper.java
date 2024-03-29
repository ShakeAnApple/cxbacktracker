package shakeanapple.backtracker.ui;

import javafx.scene.paint.Color;
import shakeanapple.backtracker.common.variable.IntegerValueHolder;
import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph.CauseFinalNode;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.causefinalgraph.CausePathFinalGraph;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangeStayedExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.model.changestayedcausetree.ChangedStayedCauseNode;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.ConnectionSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.FunctionBlockSnapshot;
import shakeanapple.backtracker.core.ltl.evaluation.model.CalculationResult;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedNode;
import shakeanapple.backtracker.core.ltl.evaluation.model.LogicalResult;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.model.CauseGraph;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.model.ChangeNode;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.model.GraphNode;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.model.RemainNode;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.*;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.visfx.graph.VisEdge;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.visfx.graph.VisGraph;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.visfx.graph.VisNode;

import java.util.*;
import java.util.function.Function;

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

    private static void flattenCausesTree(VisNode parent, ChangedStayedCauseNode child, Random r, List<VisNode> nodes, List<VisEdge> edges){
        VisNode visChild = null;
        visChild = new VisNode(r.nextLong(), child.getGate().getFullName() + System.lineSeparator() + child.getChange().getNewStep() + ": " + child.getChange().getNewValue()
                + System.lineSeparator() + "critical step: " + child.getCriticalStepNumber(), (child.getGate().getOwner() instanceof FunctionBlockComplex ? "red" :
                (!child.isChangeCause() ? "#E3ECF0" : "#55C9FC")));
        nodes.add(visChild);

        if (parent != null) {
            VisEdge edge = new VisEdge(parent, visChild, "to");
            edges.add(edge);
        }

        for (ChangedStayedCauseNode newChild : child.getChildren()) {
            flattenCausesTree(visChild, newChild, r, nodes, edges);
        }
    }


    public static VisGraph convertToGraph(ChangeStayedExplanationItem expItem){
        Random r = new Random();

        List<VisNode> nodes = new ArrayList<>();
        List<VisEdge> edges = new ArrayList<>();

        flattenCausesTree(null, expItem.getTree().getRoots().get(0), r, nodes, edges);

        VisGraph graph = new VisGraph(nodes, edges);
        return graph;
    }

//    public static VisGraph convertToGraph(CausePathFinalGraph graph){
//        Random r = new Random();
//
////        List<VisNode> nodes = new ArrayList<>();
//        List<VisEdge> edges = new ArrayList<>();
//
//        Map<Long, VisNode> nodes = new HashMap<>();
//        Map<CauseFinalNode, Long> nodesVisited = new HashMap<>();
//        for (CauseFinalNode causeNode: graph.getNodes()) {
//            long id = r.nextLong();
//            VisNode visNode = new VisNode(id, causeNode.getGate().getFullName() + System.lineSeparator() + causeNode.getStep() + ": " + causeNode.getValue(),
//                    (causeNode.getGate().getOwner() instanceof FunctionBlockComplex ? "red" :
//                            (!causeNode.hasValueChanged() ? "#E3ECF0" : "#55C9FC")));
//            nodesVisited.put(causeNode, id);
//            nodes.put(id, visNode);
//        }
//
//        for (CauseFinalNode causeNode: graph.getNodes()) {
//            VisNode visNode = nodes.get(nodesVisited.get(causeNode));
//            for (CauseFinalNode child: causeNode.getChildren()) {
//                VisNode visChild = nodes.get(nodesVisited.get(child));
//                edges.add(new VisEdge(visNode, visChild, "to"));
//            }
//        }
//
//        VisGraph visgraph = new VisGraph(new ArrayList<>(nodes.values()), edges);
//        return visgraph;
//    }

    private static void flattenCausesGraph(GraphNode parent, CauseFinalNode child, Random r, Map<Integer, GraphNode> nodes, List<shakeanapple.backtracker.ui.infrasructure.control.causegraph.model.Connection> edges, Function<String, Boolean> selectCausesSubGraphHandler){
        GraphNode viewChild = null;
        boolean existed = false;
        if (!nodes.containsKey(child.hashCode())) {
            viewChild = child.hasValueChanged() ? new ChangeNode(child.getGate().getFullName(), child.isRoot(), child.getValue(), child.getStep(), child.getPrevValue(), child.getPrevStep(), selectCausesSubGraphHandler) :
                    new RemainNode(child.getGate().getFullName(), child.isRoot(), child.getValue(), child.getStep(), selectCausesSubGraphHandler);
            nodes.put(child.hashCode(), viewChild);
        } else{
            viewChild = nodes.get(child.hashCode());
            existed = true;
        }

        if (parent != null) {
            shakeanapple.backtracker.ui.infrasructure.control.causegraph.model.Connection edge = new shakeanapple.backtracker.ui.infrasructure.control.causegraph.model.Connection(parent, viewChild);
            edges.add(edge);
        }

        if (!existed) {
            for (CauseFinalNode newChild : child.getChildren()) {
                flattenCausesGraph(viewChild, newChild, r, nodes, edges, selectCausesSubGraphHandler);
            }
        }
    }


    public static CauseGraph convertToGraph(CausePathFinalGraph graph, Function<String, Boolean> selectCausesSubGraphHandler){
        Random r = new Random();

        Map<Integer, GraphNode> nodes = new HashMap<>();
        List<shakeanapple.backtracker.ui.infrasructure.control.causegraph.model.Connection> edges = new ArrayList<>();

        flattenCausesGraph(null, graph.getRoot(), r, nodes, edges, selectCausesSubGraphHandler);

        CauseGraph causeGraph = new CauseGraph(new ArrayList<>(nodes.values()), edges, nodes.values().stream().filter(GraphNode::isRoot).findFirst().orElse(null));

        return causeGraph;
    }


    private static void flattenCausesVisGraph(VisNode parent, CauseFinalNode child, Random r, Map<Integer, VisNode> nodes, List<VisEdge> edges){
        VisNode visChild = null;
        boolean existed = false;
        String label = child.getGate().getFullName() + System.lineSeparator() + child.getStep() + ": " + child.getValue();
        if (!nodes.containsKey(child.hashCode())) {
//            visChild = new VisNode(r.nextLong(), label, (child.getGate().getOwner() instanceof FunctionBlockComplex ? "red" :
//                    (!child.hasValueChanged() ? "#E3ECF0" : "#55C9FC")));
            visChild = new VisNode(r.nextLong(), label, (!child.hasValueChanged() ? "#E3ECF0" : "#55C9FC"));
            nodes.put(child.hashCode(), visChild);
        } else{
            visChild = nodes.get(child.hashCode());
            existed = true;
        }

        if (parent != null) {
            VisEdge edge = new VisEdge(parent, visChild, "to");
            edges.add(edge);
        }

        if (!existed) {
            for (CauseFinalNode newChild : child.getChildren()) {
                flattenCausesVisGraph(visChild, newChild, r, nodes, edges);
            }
        }
    }


    public static VisGraph convertToVisGraph(CausePathFinalGraph graph){
        Random r = new Random();

        Map<Integer, VisNode> nodes = new HashMap<>();
        List<VisEdge> edges = new ArrayList<>();

        flattenCausesVisGraph(null, graph.getRoot(), r, nodes, edges);

        VisGraph visgraph = new VisGraph(new ArrayList<>(nodes.values()), edges);

        return visgraph;
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
                    return "#FFFFFF";
                    //return "#FF9999";
                case TRUE:
                    return "#FF9999";
//                    return "#CCFF99";
                // dark gray for unknown
                default:
                    return "#A0A0A0";
            }
        }
        // light gray for arithmetic res
        return "#E0E0E0";
    }

//    public static ViewGraph convertToDiagramGraph(DiagramSnapshot diagram, Function<Pin, Boolean> pinPressHandler) {
//        Random r = new Random();
//
//        Map<String, ExplainerCell> nodes = new HashMap<>();
//        List<DiagramConnection> edges = new ArrayList<>();
//
////        for (String input : diagram.getDiagramInterface().getInputs()) {
////            long id = r.nextLong();
////            nodes.put(input, new InputCell(id, "input: " + input));
////        }
////
////        for (String output : diagram.getDiagramInterface().getOutputs()) {
////            long id = r.nextLong();
////            nodes.put(output, new OutputCell(id, "output: " + output));
////        }
//
//        for (FunctionBlockSnapshot fblock : diagram.getBlocks()) {
//            long id = r.nextLong();
//            if (diagram.getDiagramInterface().getInputs().contains(fblock.getName())){
//                nodes.put(fblock.getName(), new InputVarCell(id, fblock.getName(), pinPressHandler));
//            } else if (diagram.getDiagramInterface().getOutputs().contains(fblock.getName())){
//                nodes.put(fblock.getName(), new OutputVarCell(id, fblock.getName(), pinPressHandler));
//            } else{
//                nodes.put(fblock.getName(), new BasicComponentCell(id, fblock, pinPressHandler));
//            }
//        }
//
//        for (ConnectionSnapshot connection : diagram.getConnections()) {
//            String blockNameFrom = connection.from() != null ? connection.from().getName() : connection.fromVarName();
//            ExplainerCell from = nodes.get(blockNameFrom);
//
//            String blockNameTo = connection.to() != null ? connection.to().getName() : connection.toVarName();
//            ExplainerCell to = nodes.get(blockNameTo);
//            edges.add(new Connection(from.getOutputPins().get(connection.fromVarName()), to.getInputPins().get(connection.toVarName()), connection.getValue(), connection.isInverted()));
//        }
//
//        ViewGraph graph = new ViewGraph(new ArrayList<>(nodes.values()), edges);
//        return graph;
//    }

    public static Graph convertToDiagramGraphNew(DiagramSnapshot diagram,
                                                 Function<Pin, Boolean> pinClickHandler,
                                                 Function<String, Boolean> cellClickHandler) {
        Random r = new Random();

        Map<String, DiagramCell> nodes = new HashMap<>();
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
            if (diagram.getDiagramInterface().getInputs().contains(fblock.getName())){
                ValueHolder input = diagram.getDiagramInterface().getInputsValues().get(fblock.getName());
                nodes.put(fblock.getName(), new InputInterfaceCell(fblock.getName(), diagram.getName(), pinClickHandler, input instanceof BooleanValueHolder ? new BooleanValueHolder(false) : new IntegerValueHolder(Integer.MIN_VALUE)));
            } else if (diagram.getDiagramInterface().getOutputs().contains(fblock.getName())){
                ValueHolder output = diagram.getDiagramInterface().getOutputsValues().get(fblock.getName());
                nodes.put(fblock.getName(), new OutputInterfaceCell(fblock.getName(), diagram.getName(), pinClickHandler, output instanceof BooleanValueHolder ? new BooleanValueHolder(false) : new IntegerValueHolder(Integer.MIN_VALUE)));
            } else{
                nodes.put(fblock.getName(), new Cell(fblock.getName(), fblock.getType(), fblock.getFbInterface().getInputsValues(), fblock.getFbInterface().getOutputsValues(), pinClickHandler, cellClickHandler));
            }
        }

        for (ConnectionSnapshot connection : diagram.getConnections()) {
            String blockNameFrom = connection.from() != null ? connection.from().getName() : connection.fromVarName();
            DiagramCell from = nodes.get(blockNameFrom);

            String blockNameTo = connection.to() != null ? connection.to().getName() : connection.toVarName();
            DiagramCell to = nodes.get(blockNameTo);
            edges.add(new Connection(from.getOutputPins().get(connection.fromVarName()), to.getInputPins().get(connection.toVarName()), connection.getValue(), connection.isInverted()));
        }

        Graph graph = new Graph(new ArrayList<>(nodes.values()), edges);
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
