package shakeanapple.backtracker.ui.mainview;

import javafx.fxml.FXML;
import shakeanapple.backtracker.core.ltlcalculation.LtlOnCounterexampleEvaluator;
import shakeanapple.backtracker.core.ltlcalculation.LtlSequentialEvaluator;
import shakeanapple.backtracker.core.ltlcalculation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.ltlcalculation.model.counterexample.Counterexample;
import shakeanapple.backtracker.core.ltlcalculation.model.counterexample.State;
import shakeanapple.backtracker.core.ltlcalculation.model.ltlformula.model.LtlFormula;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.BooleanVariable;
import shakeanapple.backtracker.common.variable.Variable;
import shakeanapple.backtracker.ui.infrasructure.visfx.graph.VisGraph;
import shakeanapple.backtracker.ui.TreeHelper;
import shakeanapple.backtracker.ui.control.VisGraphControl;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private VisGraphControl ltlGraph;

    private final LtlSequentialEvaluator calculationWalker;

    public MainController()  {
        this.calculationWalker = dummyInitialise();
    }

    private LtlSequentialEvaluator dummyInitialise() {
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

        Counterexample cx = new Counterexample(states);
        LtlFormula formula = LtlFormula.parse("G(((!(alarm) & !(criteria)) & X (criteria & !(ack_button))) -> X (alarm))");

        return new LtlOnCounterexampleEvaluator(cx, formula);
    }

    @FXML
    protected void updateGraph() {
        ICalculatedFormula formula = this.calculationWalker.moveNext();
        VisGraph graph = TreeHelper.convertToGraph(formula);
        this.ltlGraph.updateGraph(graph);
    }
}
