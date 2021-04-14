package shakeanapple.backtracker.ui.explainer;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import shakeanapple.backtracker.Config;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.SpecVerified;
import shakeanapple.backtracker.core.counterexample.State;
import shakeanapple.backtracker.core.diagramexplanation.backwardexplanation.DiagramChangesExplainer;
import shakeanapple.backtracker.core.ltl.explanation.model.FormulaCause;
import shakeanapple.backtracker.ui.explainer.control.diagramchangeexplainer.DiagramExplainer;
import shakeanapple.backtracker.ui.explainer.control.ltlexplainer.LtlFormulaExplainer;
import shakeanapple.backtracker.ui.explainer.control.projectinitializer.model.CustomConfig;
import shakeanapple.backtracker.ui.explainer.control.projectinitializer.ProjectInitializer;
import shakeanapple.backtracker.ui.explainer.control.valuetable.ValueTable;
import shakeanapple.backtracker.ui.explainer.control.valuetable.model.VarValueForStep;
import shakeanapple.backtracker.ui.explainer.model.Step;
import shakeanapple.backtracker.ui.infrasructure.control.ltl.model.FormulaNodeSnapshot;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainController implements Initializable {
    @FXML
    public ListView<FormulaCause> formulaCausesList;
    @FXML
    private ListView<Step> stepsList;
    @FXML
    private ProjectInitializer projectInitializer;

    ////// layout
    @FXML
    private TabPane mainTabs;
    @FXML
    private Tab workspaceTab;
    @FXML
    private GridPane mainLayout;
    private ObjectProperty<Boolean> isReadonly = new SimpleObjectProperty<>(false);

    /// controls
    @FXML
    private ValueTable valueTable;
    @FXML
    private DiagramExplainer diagramExplainer;
    @FXML
    private LtlFormulaExplainer ltlExplainer;

    /////////////
    private boolean isInitialState = true;
    /////////////

    public MainController() throws IOException {
        if (Config.instance().useConfig()){
            if (Config.instance().useFullCx()) {
                // only failed ones will be displayed
                List<SpecVerified> specs = Counterexample.loadFromNusmvOutput(Config.instance().getCxPath()).stream()
                        .filter(spec -> spec.getCx() != null).collect(Collectors.toList());
                Context.instance().setSpecsVerified(specs);
            } else {
                SpecVerified spec = new SpecVerified(Config.instance().getFormula(), Counterexample.load(Config.instance().getCxPath()));
                Context.instance().setSpecsVerified(new ArrayList<>(){{add(spec);}});
            }
            Context.instance().setDiagramPath(Config.instance().getDiagramPath());
        } else{
            this.isReadonly.setValue(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.mainLayout.disableProperty().bind(this.isReadonly);
        this.workspaceTab.disableProperty().bind(this.isReadonly);

        this.projectInitializer.init();
        this.projectInitializer.setOnConfigCompleted(config -> {
            try {
                return initMainView(config);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
        this.projectInitializer.setOnFileChosen(this::cleanMainView);

        this.valueTable.setOnTableCellClicked(this::onTableCellClicked);
        this.ltlExplainer.setOnLtlCauseClicked(this::onLtlCauseClicked);

        if (!this.isReadonly.getValue()) {
            this.stepsList.setItems(FXCollections.observableArrayList(
                    Context.instance().getCounterexample().getPath().values().stream().sorted(Comparator.comparing(State::getOrder))
                            .map(state -> new Step(state.getOrder(), Context.instance().getCounterexample().getLoopStart() == state.getOrder())).collect(Collectors.toList())
            ));
            this.stepsList.setOnMouseClicked(this::handleStepChosen);
            this.valueTable.init();
            this.diagramExplainer.init();
            this.ltlExplainer.init();
            this.ltlExplainer.setOnExplainedFormulaChanged(this::changeFormula);
        }
    }

    private boolean changeFormula(SpecVerified newFormula) {
        this.valueTable.reset();
        this.diagramExplainer.reset();
        this.ltlExplainer.reset();
        this.stepsList.getItems().clear();

        this.formulaCausesList.setItems(FXCollections.observableArrayList());
        this.stepsList.setItems(FXCollections.observableArrayList());

        Context.instance().setActiveSpecVerified(newFormula);

        this.valueTable.init();
        this.diagramExplainer.init();
        this.ltlExplainer.init();
        this.ltlExplainer.setOnExplainedFormulaChanged(this::changeFormula);
        this.stepsList.setItems(FXCollections.observableArrayList(
                Context.instance().getCounterexample().getPath().values().stream().sorted(Comparator.comparing(State::getOrder))
                        .map(state -> new Step(state.getOrder(), Context.instance().getCounterexample().getLoopStart() == state.getOrder())).collect(Collectors.toList())
        ));
        this.isInitialState = true;
        return true;
    }

    private void cleanMainView(){
        if (this.isReadonly.getValue()){
            return;
        }
        this.isReadonly.setValue(true);

        this.valueTable.reset();
        this.diagramExplainer.reset();
        this.ltlExplainer.reset();

        this.formulaCausesList.setItems(FXCollections.observableArrayList());
        this.stepsList.setItems(FXCollections.observableArrayList());

        Context.instance().reset();
    }

    private boolean initMainView(CustomConfig config) throws IOException {
        if (config.useFullCx()) {
            List<SpecVerified> specs = Counterexample.loadFromNusmvOutput(config.getCxPathCustom()).stream()
                    .filter(spec -> spec.getCx() != null).collect(Collectors.toList());
            Context.instance().setSpecsVerified(specs);
        } else {
            SpecVerified spec = new SpecVerified(config.getFormulaCustom(), Counterexample.load(config.getCxPathCustom()));
            Context.instance().setSpecsVerified(new ArrayList<>(){{add(spec);}});
        }
        Context.instance().setDiagramPath(config.getDiagramPathCustom());

        this.stepsList.setItems(FXCollections.observableArrayList(
                Context.instance().getCounterexample().getPath().values().stream().sorted(Comparator.comparing(State::getOrder))
                        .map(state -> new Step(state.getOrder(), Context.instance().getCounterexample().getLoopStart() == state.getOrder())).collect(Collectors.toList())
        ));

        this.valueTable.init();
        this.diagramExplainer.init();
        this.ltlExplainer.init();

        this.stepsList.setOnMouseClicked(this::handleStepChosen);
        this.ltlExplainer.setOnExplainedFormulaChanged(this::changeFormula);

        this.isInitialState = true;
        this.isReadonly.setValue(false);
        this.mainTabs.getSelectionModel().select(this.workspaceTab);
        return true;
    }

    private void handleStepChosen(MouseEvent mouseEvent) {
        Step step = (this.stepsList.getSelectionModel().getSelectedItem());
        Context.instance().setCurrentStep(step.getNumber());

        if (this.isInitialState) {
            this.explainFormulaFor(0);
            this.isInitialState = false;
        }

        this.diagramExplainer.updateDiagram();
        this.ltlExplainer.updateLtlTree();
    }

    public void explainFormula(ActionEvent actionEvent) {
        this.explainFormulaFor(Context.instance().getCurrentStep());
    }

    private void explainFormulaFor(int stepNum){
        List<FormulaCause> causes = this.ltlExplainer.explainFormulaFor(stepNum);

        this.formulaCausesList.getItems().clear();
        this.formulaCausesList.setItems(FXCollections.observableArrayList(causes));

        this.valueTable.highlightCauses(causes);
        this.valueTable.refresh();
    }

    private List<String> getVariableBlockPath(String fullVarName){
        String[] nameParts = fullVarName.split("\\.");
        return Arrays.asList(Arrays.copyOfRange(nameParts, 0, nameParts.length - 1));
    }

    private boolean onTableCellClicked(VarValueForStep varValueForStep) {
        if (!varValueForStep.isCauseProperty().getValue()) {
            return true;
        }
        return this.showPathInDiagramFor(varValueForStep.getStepNum(), varValueForStep.getVarName(), this.getVariableBlockPath(varValueForStep.getFullVarName()));
    }

    private boolean onLtlCauseClicked(FormulaNodeSnapshot node){
        return this.showPathInDiagramFor(node.getStepNum(), node.getNodeName(), this.getVariableBlockPath(node.getNodeName()));
    }

    private boolean showPathInDiagramFor(int stepNum, String varName, List<String> blockPath){
        this.stepsList.getSelectionModel().select(this.stepsList.getItems().stream().filter(step -> step.getNumber() == stepNum).findFirst().get());
        Context.instance().setCurrentStep(stepNum);
        this.diagramExplainer.updateDiagram();
        this.diagramExplainer.explainCause(varName, blockPath, Context.instance().getCurrentStep() + 1);

        return true;
    }
}
