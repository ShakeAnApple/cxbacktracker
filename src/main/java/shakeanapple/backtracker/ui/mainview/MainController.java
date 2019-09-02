package shakeanapple.backtracker.ui.mainview;

import javafx.fxml.FXML;
import shakeanapple.backtracker.core.fblockmapping.DiagramSequentialEvaluator;
import shakeanapple.backtracker.core.fblockmapping.DiagramWithCounterexampleEvaluator;
import shakeanapple.backtracker.core.fblockmapping.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.ltlcalculation.LtlWithCounterexampleEvaluator;
import shakeanapple.backtracker.core.ltlcalculation.LtlSequentialEvaluator;
import shakeanapple.backtracker.core.ltlcalculation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.State;
import shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.LtlFormula;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.BooleanVariable;
import shakeanapple.backtracker.common.variable.Variable;
import shakeanapple.backtracker.ui.infrasructure.visfx.graph.VisGraph;
import shakeanapple.backtracker.ui.GraphHelper;
import shakeanapple.backtracker.ui.control.VisGraphControl;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private VisGraphControl ltlGraph;

    @FXML
    private VisGraphControl functionBlocksGraph;

    private final LtlSequentialEvaluator calculationWalker;
    private final DiagramSequentialEvaluator diagramEvaluator;

    public MainController()  {
//        Counterexample cx = this.hardcodedCounterexample();
        Counterexample cx = Counterexample.load("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\cx");
        LtlFormula formula = LtlFormula.parse("G(((!(alarm) & !(criteria)) & X (criteria & !(ack_button))) -> X (alarm))");
        this.calculationWalker = new LtlWithCounterexampleEvaluator(cx, formula);

        this.diagramEvaluator = new DiagramWithCounterexampleEvaluator("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\m.smv", cx );
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
        VisGraph blocksGraph = GraphHelper.convertToGraph(snapshot);
        this.functionBlocksGraph.updateGraph(blocksGraph);
    }
}
