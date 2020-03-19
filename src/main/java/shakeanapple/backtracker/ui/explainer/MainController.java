package shakeanapple.backtracker.ui.explainer;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import shakeanapple.backtracker.Config;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.State;
import shakeanapple.backtracker.core.diagramexplanation.Clocks;
import shakeanapple.backtracker.core.ltl.explanation.model.FormulaCause;
import shakeanapple.backtracker.ui.explainer.control.diagramexplainer.DiagramExplainer;
import shakeanapple.backtracker.ui.explainer.control.ltlexplainer.LtlFormulaExplainer;
import shakeanapple.backtracker.ui.explainer.control.menubar.model.CustomConfig;
import shakeanapple.backtracker.ui.explainer.control.menubar.MenuBarCustom;
import shakeanapple.backtracker.ui.explainer.control.valuetable.ValueTable;
import shakeanapple.backtracker.ui.explainer.control.valuetable.model.VarValueForStep;
import shakeanapple.backtracker.ui.explainer.control.diagramexplainer.model.Cause;
import shakeanapple.backtracker.ui.explainer.model.Step;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {
    @FXML
    public ListView<FormulaCause> formulaCausesList;
    @FXML
    private ListView<Step> stepsList;
        @FXML
    private MenuBarCustom menuBarCustom;

    ////// layout
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
            Context.instance().setFormulaStr(Config.instance().getFormula());
            Context.instance().setCounterexample(Counterexample.load(Config.instance().getCxPath()));
            Context.instance().setDiagramPath(Config.instance().getDiagramPath());
        } else{
            this.isReadonly.setValue(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.mainLayout.disableProperty().bind(this.isReadonly);

        this.menuBarCustom.init();
        this.menuBarCustom.setOnConfigCompleted(this::initMainView);
        this.menuBarCustom.setOnFileChosen(this::cleanMainView);

        this.valueTable.setOnTableCellClicked(this::onTableCellClicked);

        if (!this.isReadonly.getValue()) {
            this.stepsList.setItems(FXCollections.observableArrayList(
                    Context.instance().getCounterexample().getPath().values().stream().sorted(Comparator.comparing(State::getOrder))
                            .map(state -> new Step(state.getOrder(), Context.instance().getCounterexample().getLoopStart() == state.getOrder())).collect(Collectors.toList())
            ));
            this.stepsList.setOnMouseClicked(this::handleStepChosen);
            this.valueTable.init();
            this.diagramExplainer.init();
            this.ltlExplainer.init();
        }
    }

    private void cleanMainView(){
        this.isReadonly.setValue(true);

        this.valueTable.reset();
        this.diagramExplainer.reset();
        this.ltlExplainer.reset();

        this.formulaCausesList.setItems(FXCollections.observableArrayList());
        this.stepsList.setItems(FXCollections.observableArrayList());

        Context.instance().reset();
    }

    private boolean initMainView(CustomConfig config){
        Context.instance().setFormulaStr(config.getFormulaCustom());
        Context.instance().setCounterexample(Counterexample.load(config.getCxPathCustom()));
        Context.instance().setDiagramPath(config.getDiagramPathCustom());

        this.stepsList.setItems(FXCollections.observableArrayList(
                Context.instance().getCounterexample().getPath().values().stream().sorted(Comparator.comparing(State::getOrder))
                        .map(state -> new Step(state.getOrder(), Context.instance().getCounterexample().getLoopStart() == state.getOrder())).collect(Collectors.toList())
        ));

        this.valueTable.init();
        this.diagramExplainer.init();
        this.ltlExplainer.init();

        this.isInitialState = true;
        this.isReadonly.setValue(false);
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


    private boolean onTableCellClicked(VarValueForStep varValueForStep) {

        if (!varValueForStep.isCauseProperty().getValue()) {
            return true;
        }
        this.stepsList.getSelectionModel().select(this.stepsList.getItems().stream().filter(step -> step.getNumber() == varValueForStep.getStepNum()).findFirst().get());
        Context.instance().setCurrentStep(varValueForStep.getStepNum() + 1);
        this.diagramExplainer.updateDiagram();
        this.diagramExplainer.explainCause(varValueForStep.getFullVarName(), varValueForStep.getBlockName(), Context.instance().getCurrentStep());

        return true;
    }
}
