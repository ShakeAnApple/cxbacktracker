package shakeanapple.backtracker.ui.mainview;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import shakeanapple.backtracker.core.diagramexplanation.DiagramSequentialEvaluator;
import shakeanapple.backtracker.core.diagramexplanation.DiagramWithCounterexampleEvaluator;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.ltlexplanation.LtlWithCounterexampleEvaluator;
import shakeanapple.backtracker.core.ltlexplanation.LtlSequentialEvaluator;
import shakeanapple.backtracker.core.ltlexplanation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.State;
import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.LtlFormula;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.BooleanVariable;
import shakeanapple.backtracker.common.variable.Variable;
import shakeanapple.backtracker.ui.control.diagram.DiagramControl;
import shakeanapple.backtracker.ui.control.diagram.ViewGraph;
import shakeanapple.backtracker.ui.control.diagram.model.Cell;
import shakeanapple.backtracker.ui.control.diagram.Connection;
import shakeanapple.backtracker.ui.control.diagram.model.RectangleCell;
import shakeanapple.backtracker.ui.control.visgraph.visfx.graph.VisGraph;
import shakeanapple.backtracker.ui.GraphHelper;
import shakeanapple.backtracker.ui.control.visgraph.VisGraphControl;

import java.util.*;

public class MainController {

    @FXML
    private VisGraphControl ltlGraph;

    @FXML
    private VisGraphControl functionBlocksGraph;

    @FXML
    private DiagramControl diagram;

    private final LtlSequentialEvaluator calculationWalker;
    private final DiagramSequentialEvaluator diagramEvaluator;

    public MainController()  {
        Counterexample cx = Counterexample.load("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\cx");
        LtlFormula formula = LtlFormula.parse("G(((!(alarm) & !(criteria)) & X (criteria & !(ack_button))) -> X (alarm))");
        this.calculationWalker = new LtlWithCounterexampleEvaluator(cx, formula);

        this.diagramEvaluator = new DiagramWithCounterexampleEvaluator("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\m.smv",
                "C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\basics", cx );
    }

    private Counterexample hardcodedCounterexample() {
        List<State> states = new ArrayList<>();

        List<Variable> variablesValues = new ArrayList<>();
        Variable v1 = new BooleanVariable(new BooleanValueHolder(true), "ack_button");
        variablesValues.add(v1);
        Variable v2 = new BooleanVariable(new BooleanValueHolder(false), "alarm");
        variablesValues.add(v2);
        Variable v3 = new BooleanVariable(new BooleanValueHolder(true), "criteria");
        variablesValues.add(v3);
        State s = new State(variablesValues, 1);
        states.add(s);

        variablesValues = new ArrayList<>();
        v1 = new BooleanVariable(new BooleanValueHolder(true), "ack_button");
        variablesValues.add(v1);
        v2 = new BooleanVariable(new BooleanValueHolder(false), "alarm");
        variablesValues.add(v2);
        v3 = new BooleanVariable(new BooleanValueHolder(false), "criteria");
        variablesValues.add(v3);
        s = new State(variablesValues, 2);
        states.add(s);

        variablesValues = new ArrayList<>();
        v1 = new BooleanVariable(new BooleanValueHolder(false), "ack_button");
        variablesValues.add(v1);
        v2 = new BooleanVariable(new BooleanValueHolder(false), "alarm");
        variablesValues.add(v2);
        v3 = new BooleanVariable(new BooleanValueHolder(true), "criteria");
        variablesValues.add(v3);
        s = new State(variablesValues, 3);
        states.add(s);

        variablesValues = new ArrayList<>();
        v1 = new BooleanVariable(new BooleanValueHolder(true), "ack_button");
        variablesValues.add(v1);
        v2 = new BooleanVariable(new BooleanValueHolder(false), "alarm");
        variablesValues.add(v2);
        v3 = new BooleanVariable(new BooleanValueHolder(true), "criteria");
        variablesValues.add(v3);
        s = new State(variablesValues, 4);
        states.add(s);

        return new Counterexample(states);
    }

    @FXML
    protected void updateGraph() {
        ICalculatedFormula formula = this.calculationWalker.moveNext();
        VisGraph graph = GraphHelper.convertToGraph(formula);
        this.ltlGraph.updateGraph(graph);

        DiagramSnapshot snapshot = this.diagramEvaluator.moveNext();
        //VisGraph blocksGraph = GraphHelper.convertToGraph(snapshot);
        //this.diagram.draw();updateGraph(blocksGraph);
        ViewGraph diagram = GraphHelper.convertToDiagramGraph(snapshot);
        this.diagram.draw(diagram);
    }

    @FXML
    private void updateGraph1(){
        List<Cell> cells = new ArrayList<>();
        cells.add(new RectangleCell(1, "Cell A"));
        cells.add(new RectangleCell(2, "Cell B"));
        cells.add(new RectangleCell(3, "Cell C"));
        cells.add(new RectangleCell(4, "Cell D"));
        cells.add(new RectangleCell(5, "Cell E"));
        cells.add(new RectangleCell(6, "Cell F"));
        cells.add(new RectangleCell(7, "Cell G"));

        List<Connection> edges = new ArrayList<>();

        edges.add(new Connection(1, 2, "A -> B", Color.AZURE));
        edges.add(new Connection(1, 3, "A -> C", Color.RED));
        edges.add(new Connection(2, 3, "B -> C", Color.GRAY));
        edges.add(new Connection(3, 4, "C -> D", Color.ALICEBLUE));
        edges.add(new Connection(3, 4, "C -> D (1)", Color.BISQUE));
        edges.add(new Connection(3, 4, "C -> D (2)", Color.BISQUE));
        edges.add(new Connection(3, 4, "C -> D (3)", Color.RED));
        edges.add(new Connection(2, 5, "B -> E", Color.ROSYBROWN));
        edges.add(new Connection(4, 6, "D -> F", Color.ROYALBLUE));
        edges.add(new Connection(4, 7, "D -> G", Color.LAVENDER));

//        this.diagram.draw(cells, edges);
    }
}
