package shakeanapple.backtracker.ui.mainview;

import javafx.fxml.FXML;
import shakeanapple.backtracker.core.ltlcalculation.CounterexampleWalker;
import shakeanapple.backtracker.core.ltlcalculation.ICalculationWalker;
import shakeanapple.backtracker.core.ltlcalculation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.model.counterexample.Counterexample;
import shakeanapple.backtracker.core.model.counterexample.State;
import shakeanapple.backtracker.core.model.ltlformula.model.LtlFormula;
import shakeanapple.backtracker.core.model.variable.BooleanValueHolder;
import shakeanapple.backtracker.core.model.variable.BooleanVariable;
import shakeanapple.backtracker.core.model.variable.Variable;
import shakeanapple.backtracker.infrastructure.visfx.graph.VisGraph;
import shakeanapple.backtracker.ui.TreeHelper;
import shakeanapple.backtracker.ui.control.VisGraphControl;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private VisGraphControl visGraph;

    private final ICalculationWalker calculationWalker;

    public MainController()  {
        this.calculationWalker = dummyInitialise();
    }

    private ICalculationWalker dummyInitialise() {
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

        return new CounterexampleWalker(cx, formula);
    }

    @FXML
    protected void updateGraph() {
        ICalculatedFormula formula = this.calculationWalker.moveNext();
        VisGraph graph = TreeHelper.convertToGraph(formula);
        this.visGraph.updateGraph(graph);
    }
}
