package shakeanapple.backtracker.ui.explainer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import shakeanapple.backtracker.core.counterexample.State;
import shakeanapple.backtracker.core.diagramexplanation.*;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.ConnectionSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.ltl.evaluation.LtlWithCounterexampleEvaluator;
import shakeanapple.backtracker.core.ltl.evaluation.LtlEvaluator;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.ltl.explanation.ILtlFormulaExplainer;
import shakeanapple.backtracker.core.ltl.explanation.LtlFormulaExplainer;
import shakeanapple.backtracker.core.ltl.explanation.model.FormulaCause;
import shakeanapple.backtracker.core.ltl.formula.model.LtlFormula;
import shakeanapple.backtracker.ui.explainer.model.Cause;
import shakeanapple.backtracker.ui.explainer.model.Step;
import shakeanapple.backtracker.ui.explainer.model.graph.cell.Pin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.DiagramControl;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.ViewGraph;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.DiagramConnection;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.visfx.graph.VisGraph;
import shakeanapple.backtracker.ui.GraphHelper;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.VisGraphControl;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    @FXML
    public ListView<Cause> diagramCausesList;
    @FXML
    public ListView<FormulaCause> formulaCausesList;
    @FXML
    private VisGraphControl ltlGraph;
    @FXML
    private VisGraphControl functionBlocksGraph;
    @FXML
    private DiagramControl diagram;
    @FXML
    private ListView<Step> stepsList;
    @FXML
    private TableView variablesByStepsTable;

    private final LtlEvaluator calculationWalker;
    private DiagramSequentialEvaluator diagramEvaluator;
    private DiagramCounterexampleExecutor diagramExecutor;
    private DiagramOutputExplainer diagramOutputExplainer;
    private Counterexample counterexample;
    private ILtlFormulaExplainer ltlExplainer;

    private Map<String, DiagramConnection> connections = new HashMap<>();
    private int currentStep = 0;
    private DiagramEvaluationCache cache = new DiagramEvaluationCache();

    public MainController() {

//        FunctionBlockComplex diagram = FunctionBlockComplex.parse("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\m.smv",
//                "C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\basics");
//        counterexample = Counterexample.load("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\cx");
//        LtlFormula formula = LtlFormula.parse("G(((!(alarm) & !(criteria)) & X (criteria & !(ack_button))) -> X (alarm))");


        FunctionBlockComplex diagram = FunctionBlockComplex.parse("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\test-model\\triggeredff-typed.smv",
                "C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\basics");
        counterexample = Counterexample.load("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\test-model\\cx-trig");
        LtlFormula formula = LtlFormula.parse("G (SET & X !SET -> X !ACT)");


//        FunctionBlockComplex diagram = FunctionBlockComplex.parse("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\test-model\\m.smv",
//                "C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\basics");

        this.calculationWalker = new LtlWithCounterexampleEvaluator(counterexample, formula);

        this.diagramEvaluator = new DiagramWithCounterexampleEvaluator(diagram, counterexample);
        this.diagramExecutor = new DiagramCounterexampleExecutor(diagram, counterexample);

        this.diagramOutputExplainer = new DiagramBackwardExplainer(diagram);

        this.ltlExplainer = new LtlFormulaExplainer(formula, this.counterexample, this.calculationWalker);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.diagramCausesList.setOnMouseClicked(this::handleCauseChosen);

        this.stepsList.setItems(FXCollections.observableArrayList(
                this.counterexample.getPath().values().stream().sorted(Comparator.comparing(State::getOrder))
                        .map(state -> new Step(state.getOrder(), this.counterexample.getLoopStart() == state.getOrder())).collect(Collectors.toList())
        ));
        this.stepsList.setOnMouseClicked(this::handleStepChosen);
    }

    private void handleStepChosen(MouseEvent mouseEvent) {
        Step step = (this.stepsList.getSelectionModel().getSelectedItem());
        this.currentStep = step.getNumber();

        DiagramSnapshot snapshot = this.diagramExecutor.moveTo(step.getNumber());
        this.cache.add(snapshot, step.getNumber());
        this.updateDiagram(snapshot);

        ICalculatedFormula formula = this.calculationWalker.calculateRootForStep(step.getNumber());
        VisGraph graph = GraphHelper.convertToGraph(formula);
        this.ltlGraph.updateGraph(graph);
    }

    private void handleCauseChosen(MouseEvent event) {
        Cause cause = (this.diagramCausesList.getSelectionModel().getSelectedItem());
        this.explainCause(cause.getVarName(), cause.getBlockName(), cause.getTimestamp());
        this.renderDiagramForStep(cause.getTimestamp());
    }

    private void renderDiagramForStep(int stepNum) {
        DiagramSnapshot snapshot = this.cache.getByStep(stepNum);
        this.updateConnections(snapshot.getConnections());
    }

    private void explainCause(String varName, String blockName, int timestamp) {
        ObservableList<Cause> causes = FXCollections.observableList(
                this.diagramOutputExplainer.explain(varName, blockName, timestamp).stream().map(c -> new Cause(c.getTimestamp(), c.getGate().getOwner().getName(), c.getGate().getName(), c.getValue())).collect(Collectors.toList())
        );
        this.diagramCausesList.setItems(causes);
    }

    private Boolean pinPressHandler(Pin pin) {
        this.explainCause(pin.getName(), pin.getOwner().getName(), this.currentStep);
        return true;
    }

    private void updateDiagram(DiagramSnapshot snapshot) {
        if (this.diagram.isClear()) {
            ViewGraph diagram = GraphHelper.convertToDiagramGraph(snapshot, this::pinPressHandler);
            this.diagram.draw(diagram);
            this.recordConnections(diagram.getConnections());
        } else {
            this.updateConnections(snapshot.getConnections());
        }
    }

    @FXML
    protected void updateGraph() {
        this.currentStep++;
        ICalculatedFormula formula = this.calculationWalker.moveNext();
        VisGraph graph = GraphHelper.convertToGraph(formula);
        this.ltlGraph.updateGraph(graph);

//        DiagramSnapshot snapshot = this.diagramEvaluator.moveNext();
        DiagramSnapshot snapshot = this.diagramExecutor.moveNext();
        this.cache.add(snapshot, this.currentStep);
        //VisGraph blocksGraph = GraphHelper.convertToGraph(snapshot);
        //this.diagram.draw();updateGraph(blocksGraph);
        this.updateDiagram(snapshot);
    }

    private void updateConnections(List<ConnectionSnapshot> connections) {
        for (ConnectionSnapshot snap : connections) {
            this.connections.get(snap.getId()).updateValue(snap.getValue());
        }
    }

    private void recordConnections(List<DiagramConnection> connections) {
        this.connections = connections.stream().collect(Collectors.toMap(DiagramConnection::getId, c -> c));
    }


    public void explainFormula(ActionEvent actionEvent) {
        List<FormulaCause> causes = this.ltlExplainer.explainRootForStep(this.currentStep).getCauses();
        this.formulaCausesList.setItems(FXCollections.observableArrayList(causes));
    }
}
