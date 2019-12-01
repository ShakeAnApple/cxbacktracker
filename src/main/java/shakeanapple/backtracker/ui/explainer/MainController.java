package shakeanapple.backtracker.ui.explainer;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import shakeanapple.backtracker.Config;
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
import shakeanapple.backtracker.ui.explainer.model.*;
import shakeanapple.backtracker.ui.explainer.model.Cause;
import shakeanapple.backtracker.ui.explainer.model.graph.cell.Pin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.DiagramControl;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.ViewGraph;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.DiagramConnection;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.visfx.graph.VisGraph;
import shakeanapple.backtracker.ui.GraphHelper;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.VisGraphControl;

import java.io.IOException;
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

    private ObservableList<CxVar> varsTableItems = FXCollections.observableArrayList();

    public MainController() throws IOException {
        Config config = new Config();
        FunctionBlockComplex diagram = FunctionBlockComplex.parse(config.getDiagramPath());
        counterexample = Counterexample.load(config.getCxPath());
        LtlFormula formula = LtlFormula.parse(config.getFormula());


//        FunctionBlockComplex diagram = FunctionBlockComplex.parse("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\test-model\\triggeredff-typed.smv",
//                "C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\basics");
//        counterexample = Counterexample.load("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\test-model\\cx-trig");
//        LtlFormula formula = LtlFormula.parse("G (SET & X !SET -> X !ACT)");


//        FunctionBlockComplex diagram = FunctionBlockComplex.parse("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\test-model\\m-typed.smv",
//                "C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\basics");
//        counterexample = Counterexample.load("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\test-model\\cx-m");
//        LtlFormula formula = LtlFormula.parse("G !(mode_a & mode_b)");

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

        this.initializeTable();
    }

    private void initializeTable() {

        TableColumn<CxVar, String> c = new TableColumn<>("Var name");
        c.setCellValueFactory(param ->
                new ReadOnlyObjectWrapper<>(param.getValue().getVarName())
        );
        this.variablesByStepsTable.getColumns().add(c);

        for (int i = 0; i < this.counterexample.length(); i++) {
            final int finalIdx = i;
            TableColumn<CxVar, VarValueForStep> column = new TableColumn<>(String.valueOf(i));
            column.setCellValueFactory(param ->
                    new ReadOnlyObjectWrapper<>(param.getValue().getVarValues().get(finalIdx))
            );
            column.setCellFactory(param -> new VarValueCell());
            column.setPrefWidth(50);
            this.variablesByStepsTable.getColumns().add(column);
        }

        Map<String, List<VarValueForStep>> varsValues = new HashMap<>();
        for (int step = 0; step < this.counterexample.length(); step++) {
            State cxState = this.counterexample.getPath().get(step);
            if (varsValues.isEmpty()) {
                cxState.getVarsByNames().values().forEach(var -> {
                    varsValues.put(var.getName(), new ArrayList<>());
                });
            }
            varsValues.keySet().forEach(varName -> {
                varsValues.get(varName).add(
                        new VarValueForStep(varName, cxState.getVarByName(varName).getValue().toString(), false));
            });
        }

        varsValues.keySet().forEach(varName -> this.varsTableItems.add(new CxVar(varName, varsValues.get(varName))));
        this.variablesByStepsTable.getItems().addAll(this.varsTableItems);
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
        Map<String, List<FormulaCause>> causesByVarName = new HashMap<>();
        for (FormulaCause cause: causes){
            if (!causesByVarName.containsKey(cause.getVarName())){
                causesByVarName.put(cause.getVarName(), new ArrayList<>());
            }
            causesByVarName.get(cause.getVarName()).add(cause);
        }

        this.varsTableItems.forEach(cxVar -> {
            for (VarValueForStep varValueForStep: cxVar.getVarValues()){
                varValueForStep.isCauseProperty().setValue(false);
            }
        });

        this.varsTableItems.forEach(cxVar -> {
            if (causesByVarName.containsKey(cxVar.getVarName())) {
                List<FormulaCause> cxVarCauses = causesByVarName.get(cxVar.getVarName());
                for (FormulaCause cause: cxVarCauses){
                    cxVar.getVarValues().get(cause.getStepNum()).isCauseProperty().setValue(true);
                }
            }
        });

        this.variablesByStepsTable.refresh();
        this.formulaCausesList.setItems(FXCollections.observableArrayList(causes));
    }
}
