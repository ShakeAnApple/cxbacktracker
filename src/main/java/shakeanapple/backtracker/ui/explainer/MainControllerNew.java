package shakeanapple.backtracker.ui.explainer;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import shakeanapple.backtracker.Config;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.State;
import shakeanapple.backtracker.core.diagramexplanation.*;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.ConnectionSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.FBInterfaceSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.FunctionBlockSnapshot;
import shakeanapple.backtracker.core.ltl.evaluation.LtlEvaluator;
import shakeanapple.backtracker.core.ltl.evaluation.LtlWithCounterexampleEvaluator;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.ltl.explanation.ILtlFormulaExplainer;
import shakeanapple.backtracker.core.ltl.explanation.LtlFormulaExplainer;
import shakeanapple.backtracker.core.ltl.explanation.model.FormulaCause;
import shakeanapple.backtracker.core.ltl.formula.model.LtlFormula;
import shakeanapple.backtracker.ui.GraphHelper;
import shakeanapple.backtracker.ui.explainer.model.*;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.DiagramControl;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Connection;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.DiagramCell;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Graph;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Pin;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.VisGraphControl;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.visfx.graph.VisGraph;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainControllerNew implements Initializable {

    @FXML
    public ListView<Cause> diagramCausesList;
    @FXML
    public ListView<FormulaCause> formulaCausesList;
    @FXML
    private VisGraphControl ltlGraph;
    @FXML
    private DiagramControl diagram;
    @FXML
    private ListView<Step> stepsList;
    @FXML
    private TableView variablesByStepsTable;
    @FXML
    private ObjectProperty<Boolean> isReadOnly = new SimpleObjectProperty<>(false);
    @FXML
    private GridPane mainLayout;
    @FXML
    private TextField formulaField;

    private LtlEvaluator calculationWalker;
    private DiagramCounterexampleExecutor diagramExecutor;
    private DiagramOutputExplainer diagramOutputExplainer;
    private Counterexample counterexample;
    private ILtlFormulaExplainer ltlExplainer;

    private String formulaStr;

    private Map<String, Connection> connections = new HashMap<>();
    private Map<String, Pin> diagramPins = new HashMap<>();
    private int currentStep = 0;
    private DiagramEvaluationCache cache = new DiagramEvaluationCache();

    private ObservableList<CxVar> varsTableItems = FXCollections.observableArrayList();

    private String diagramPathCustom;
    private String cxPathCustom;
    private String formulaCustom;
    private boolean isInitialState = true;

    public MainControllerNew() throws IOException {
        Config config = Config.instance();
        if (config.useConfig()) {
            this.construct(config.getDiagramPath(), config.getCxPath(), config.getFormula());
        } else {
            this.isReadOnly.setValue(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.mainLayout.disableProperty().bind(this.isReadOnly);
        if (!this.isReadOnly.getValue()) {
            this.init();
        }
    }

    private void init() {
        this.stepsList.setItems(FXCollections.observableArrayList(
                this.counterexample.getPath().values().stream().sorted(Comparator.comparing(State::getOrder))
                        .map(state -> new Step(state.getOrder(), this.counterexample.getLoopStart() == state.getOrder())).collect(Collectors.toList())
        ));
        this.stepsList.setOnMouseClicked(this::handleStepChosen);
        this.formulaField.setText(this.formulaStr);
        this.initializeTable();
    }

    private void construct(String diagramPath, String cxPath, String formulaStr) {
        this.formulaStr = formulaStr;
        FunctionBlockComplex diagram = FunctionBlockComplex.parse(diagramPath);
        this.counterexample = Counterexample.load(cxPath);
        LtlFormula formula = LtlFormula.parse(formulaStr);

        this.calculationWalker = new LtlWithCounterexampleEvaluator(counterexample, formula);

        this.diagramExecutor = new DiagramCounterexampleExecutor(diagram, counterexample);

        this.diagramOutputExplainer = new DiagramBackwardExplainer(diagram);

        this.ltlExplainer = new LtlFormulaExplainer(formula, this.counterexample, this.calculationWalker);
    }

    private void customInit() {
        this.construct(this.diagramPathCustom, this.cxPathCustom, this.formulaCustom);
        this.init();
    }

    private void clearMainView() {
        this.isReadOnly.setValue(true);
        this.diagramPathCustom = null;
        this.cxPathCustom = null;
        this.formulaCustom = null;
        this.currentStep = 0;
        this.isInitialState = true;
        this.formulaField.setText("");
        Clocks.instance().reset();

        this.diagramCausesList.setItems(FXCollections.observableArrayList());
        this.formulaCausesList.setItems(FXCollections.observableArrayList());
        this.stepsList.setItems(FXCollections.observableArrayList());
        this.variablesByStepsTable.setItems(FXCollections.observableArrayList());
        this.variablesByStepsTable.getColumns().clear();
        this.calculationWalker = null;
        this.diagramExecutor = null;
        this.diagramOutputExplainer = null;
        this.counterexample = null;
        this.ltlExplainer = null;

        this.diagram.clear();
        this.ltlGraph.clear();

        this.connections = new HashMap<>();
        this.diagramPins = new HashMap<>();
        this.cache = new DiagramEvaluationCache();
        this.varsTableItems = FXCollections.observableArrayList();
    }

    private boolean onTableCellClicked(VarValueForStep varValueForStep) {
        if (!varValueForStep.isCauseProperty().getValue()) {
            return true;
        }
        this.stepsList.getSelectionModel().select(this.stepsList.getItems().stream().filter(step -> step.getNumber() == varValueForStep.getStepNum()).findFirst().get());

        this.currentStep = varValueForStep.getStepNum() + 1;
        DiagramSnapshot snapshot = this.diagramExecutor.moveTo(varValueForStep.getStepNum());
        this.cache.add(snapshot, varValueForStep.getStepNum());
        this.updateDiagram(snapshot);
        this.explainCause(varValueForStep.getFullVarName(), varValueForStep.getBlockName(), this.currentStep);
        return true;
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
            column.setCellFactory(param -> new VarValueCell(this::onTableCellClicked));
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
            final int finalStep = step;
            varsValues.keySet().forEach(varName -> {
                varsValues.get(varName).add(
                        new VarValueForStep(varName, cxState.getVarByName(varName).getValue().toString(), finalStep, false));
            });
        }

        varsValues.keySet().forEach(varName -> this.varsTableItems.add(new CxVar(varName, varsValues.get(varName))));
        this.variablesByStepsTable.getItems().addAll(this.varsTableItems.stream().sorted(Comparator.comparing(CxVar::getVarName)).collect(Collectors.toList()));
    }

    private void handleStepChosen(MouseEvent mouseEvent) {
        if (this.isInitialState) {
            this.explainFormulaImpl(0);
            this.isInitialState = false;
        }

        Step step = (this.stepsList.getSelectionModel().getSelectedItem());
        this.currentStep = step.getNumber() + 1;

        DiagramSnapshot snapshot = this.diagramExecutor.moveTo(step.getNumber());
        this.cache.add(snapshot, step.getNumber());
        this.updateDiagram(snapshot);

        ICalculatedFormula formula = this.calculationWalker.calculateRootForStep(step.getNumber());
        VisGraph graph = GraphHelper.convertToGraph(formula);
        this.ltlGraph.updateGraph(graph);
    }

    private void clearConnections() {
        for (Connection conn : this.connections.values()) {
            conn.isCauseTreeEdge(false);
        }
    }

    private void explainCause(String varName, String blockName, int timestamp) {
        this.clearConnections();

        ExplanationItem expRes = this.diagramOutputExplainer.explain(varName, blockName, timestamp);
        CauseNodeUI causesTree = CauseNodeUI.parse(expRes.getTree().getRoots().get(0));


        Map<String, Map<String, List<Cause>>> connectionsCauses = causesTree.inferConnectionCauses();
        for (String connId : connectionsCauses.keySet()) {
            if (this.connections.containsKey(connId)) {
                Connection conn = this.connections.get(connId);
                conn.isCauseTreeEdge(true);

                List<Cause> causesFrom = connectionsCauses.get(connId).get(conn.getFrom().getName());
                conn.getFrom().getCausesObservable().addAll(causesFrom.stream().map(c ->
                        new shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Cause(c.getTimestamp(), c.getVarName(), c.getBlockName(), c.getValue())).collect(Collectors.toList()));

                List<Cause> causesTo = connectionsCauses.get(connId).get(conn.getTo().getName());
                conn.getTo().getCausesObservable().addAll(causesTo.stream().map(c ->
                        new shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Cause(c.getTimestamp(), c.getVarName(), c.getBlockName(), c.getValue())).collect(Collectors.toList()));
            }
        }

        this.diagramCausesList.setItems(FXCollections.observableArrayList(expRes.getFreshNodes().stream()
                .map(causeNode -> new Cause(causeNode.getTimestamp() - 1, causeNode.getGate().getName(), causeNode.getGate().getOwner().getName(), causeNode.getValue()))
                .sorted(Comparator.comparing(Cause::getTimestamp).thenComparing(Cause::getBlockName).thenComparing(Cause::getVarName)).collect(Collectors.toList())));
    }

    private Boolean diagramPinPressHandler(shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Pin pin) {
        this.explainCause(pin.getName(), pin.getOwner().getName(), this.currentStep);
        return true;
    }

    private void updateDiagram(DiagramSnapshot snapshot) {
        this.clearConnections();
        if (this.diagram.isClear()) {
            Graph diagram = GraphHelper.convertToDiagramGraphNew(snapshot, this::diagramPinPressHandler);
            this.diagram.draw(diagram);
            this.recordConnections(diagram.getConnections());
            this.recordPins(diagram.getCells());
            this.updateBlockInterfaces(snapshot.getBlocks(), snapshot.getDiagramInterface());
//            this.updateConnections(snapshot.getConnections());

        } else {
            this.updateBlockInterfaces(snapshot.getBlocks(), snapshot.getDiagramInterface());
//            this.updateConnections(snapshot.getConnections());
        }
    }


    private void updateBlockInterfaces(List<FunctionBlockSnapshot> blockSnapshots, FBInterfaceSnapshot diagramInterface) {
        for (FunctionBlockSnapshot fb : blockSnapshots) {
            for (String varName : fb.getFbInterface().getInputsValues().keySet()) {
                this.diagramPins.get(fb.getName() + varName).updateValue(fb.getFbInterface().getInputsValues().get(varName));
            }
            for (String varName : fb.getFbInterface().getOutputsValues().keySet()) {
                this.diagramPins.get(fb.getName() + varName).updateValue(fb.getFbInterface().getOutputsValues().get(varName));
            }
        }
        for (String varName : diagramInterface.getInputsValues().keySet()) {
            this.diagramPins.get(varName + varName).updateValue(diagramInterface.getInputsValues().get(varName));
        }
        for (String varName : diagramInterface.getOutputsValues().keySet()) {
            this.diagramPins.get(varName + varName).updateValue(diagramInterface.getOutputsValues().get(varName));
        }
    }


    private void recordConnections(List<Connection> connections) {
        this.connections = connections.stream().collect(Collectors.toMap(Connection::getId, c -> c));
    }

    private void recordPins(List<DiagramCell> cells) {
        for (DiagramCell cell : cells) {
            for (Pin pin : cell.getInputPins().values()) {
                this.diagramPins.put(pin.getOwner().getName() + pin.getName(), pin);
            }
            for (Pin pin : cell.getOutputPins().values()) {
                this.diagramPins.put(pin.getOwner().getName() + pin.getName(), pin);
            }
        }
    }


    public void explainFormula(ActionEvent actionEvent) {
        this.explainFormulaImpl(this.currentStep - 1);
    }

    private void explainFormulaImpl(int forStep) {
        List<FormulaCause> causes = this.ltlExplainer.explainRootForStep(forStep).getCauses().stream()
                .sorted(Comparator.comparing(FormulaCause::getStepNum).thenComparing(FormulaCause::getVarName)).collect(Collectors.toList());
        Map<String, List<FormulaCause>> causesByVarName = new HashMap<>();
        for (FormulaCause cause : causes) {
            if (!causesByVarName.containsKey(cause.getVarName())) {
                causesByVarName.put(cause.getVarName(), new ArrayList<>());
            }
            causesByVarName.get(cause.getVarName()).add(cause);
        }

        this.varsTableItems.forEach(cxVar -> {
            for (VarValueForStep varValueForStep : cxVar.getVarValues()) {
                varValueForStep.isCauseProperty().setValue(false);
            }
        });

        this.varsTableItems.forEach(cxVar -> {
            if (causesByVarName.containsKey(cxVar.getVarName())) {
                List<FormulaCause> cxVarCauses = causesByVarName.get(cxVar.getVarName());
                for (FormulaCause cause : cxVarCauses) {
                    cxVar.getVarValues().get(cause.getStepNum()).isCauseProperty().setValue(true);
                }
            }
        });

        this.variablesByStepsTable.refresh();
        this.formulaCausesList.setItems(FXCollections.observableArrayList(causes));
    }


    //////////////////// Menu item clicks /////////////////

    private File initialDirectory;

    private File chooseFile(ActionEvent event, List<FileChooser.ExtensionFilter> extensionFilters) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(extensionFilters);
        chooser.setInitialDirectory(this.initialDirectory);
        chooser.setTitle("Open File");
        return chooser.showOpenDialog(((MenuItem) event.getSource()).getParentPopup().getScene().getWindow());
    }

    public void handleOpenModelMenuItemClick(ActionEvent actionEvent) {

        List<FileChooser.ExtensionFilter> filters = Collections.singletonList(new FileChooser.ExtensionFilter("NuSMV typed model", "*.smv"));
        File file = this.chooseFile(actionEvent, filters);
        if (file != null) {
            if (!this.isReadOnly.getValue()) {
                this.clearMainView();
            }
            this.initialDirectory = file.getParentFile();
            this.diagramPathCustom = file.getAbsolutePath();
            if (this.cxPathCustom != null && this.formulaCustom != null) {
                this.isReadOnly.setValue(false);
                this.customInit();
            }
        }
    }

    public void handleOpenCounterexampleMenuItemClick(ActionEvent actionEvent) {

        List<FileChooser.ExtensionFilter> filters = Collections.singletonList(new FileChooser.ExtensionFilter("Plain text", "*.*"));
        File file = this.chooseFile(actionEvent, filters);
        if (file != null) {
            if (this.cxPathCustom != null) {
                this.clearMainView();
            }
            this.initialDirectory = file.getParentFile();
            this.cxPathCustom = file.getAbsolutePath();
            if (this.diagramPathCustom != null && this.formulaCustom != null) {
                this.isReadOnly.setValue(false);
                this.customInit();
            }
        }
    }

    public void handleInputFormulaMenuItemClick(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input formula");
        dialog.setContentText("Input ltl formula:");
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.setMinWidth(500);
        dialog.setResizable(true);
        dialog.getDialogPane().setGraphic(null);
        dialog.getDialogPane().setHeaderText("");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (this.formulaCustom != null) {
                this.clearMainView();
            }
            this.formulaCustom = result.get();
            if (this.diagramPathCustom != null && this.cxPathCustom != null) {
                this.isReadOnly.setValue(false);
                this.customInit();
            }
        }
    }

    public void handleExitMenuItemClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}
