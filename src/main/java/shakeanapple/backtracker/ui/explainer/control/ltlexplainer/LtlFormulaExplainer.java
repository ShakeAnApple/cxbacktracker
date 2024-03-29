package shakeanapple.backtracker.ui.explainer.control.ltlexplainer;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import shakeanapple.backtracker.core.counterexample.SpecVerified;
import shakeanapple.backtracker.core.ltl.evaluation.LtlEvaluator;
import shakeanapple.backtracker.core.ltl.evaluation.LtlWithCounterexampleEvaluator;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.ltl.explanation.ILtlFormulaExplainer;
import shakeanapple.backtracker.core.ltl.explanation.model.FormulaCause;
import shakeanapple.backtracker.core.ltl.formula.model.LtlFormula;
import shakeanapple.backtracker.ui.GraphHelper;
import shakeanapple.backtracker.ui.explainer.Context;
import shakeanapple.backtracker.ui.infrasructure.control.ltl.StepsViewTable;
import shakeanapple.backtracker.ui.infrasructure.control.ltl.model.FormulaNodeSnapshot;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.VisGraphControl;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.visfx.graph.VisGraph;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LtlFormulaExplainer extends VBox {
    @FXML
    private ComboBox<SpecVerified> failedFormulas;
    @FXML
    private VisGraphControl ltlGraph;
    @FXML
    private StepsViewTable formulaByStepsTable;

    private ObservableList<FormulaCause> currentCauses = FXCollections.observableArrayList();

    private LtlEvaluator calculationWalker;
    private ILtlFormulaExplainer ltlExplainer;

    private ChangeListener formulaChangedListener;
    private ListChangeListener explanationChangedListener;

    public LtlFormulaExplainer() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/main/explainer/ltl/ltlexplainer.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setOnLtlCauseClicked(Function<FormulaNodeSnapshot, Boolean> onCauseClicked){
        this.formulaByStepsTable.setOnLtlCauseClicked(onCauseClicked);
    }

    public void init() {
        LtlFormula formula = LtlFormula.parse(Context.instance().getFormulaStr());
        this.calculationWalker = new LtlWithCounterexampleEvaluator(Context.instance().getCounterexample(), formula);
        this.ltlExplainer = new shakeanapple.backtracker.core.ltl.explanation.LtlFormulaExplainer(formula, Context.instance().getCounterexample(), this.calculationWalker);

        if (this.formulaChangedListener != null) {
            this.failedFormulas.valueProperty().removeListener(this.formulaChangedListener);
        }
        if (this.explanationChangedListener != null) {
            this.currentCauses.removeListener(this.explanationChangedListener);
        }
        this.failedFormulas.setItems(FXCollections.observableArrayList(Context.instance().getSpecsVerified()));
        this.failedFormulas.getSelectionModel().select(
                this.failedFormulas.getItems().indexOf(
                        Context.instance().getActiveSpecVerified()
                )
        );

        this.formulaByStepsTable.init(this.ltlExplainer, formula);
    }

    public void setOnExplainedFormulaChanged(Function<SpecVerified, Boolean> onExplainedFormulaChanged) {
        this.formulaChangedListener = (obs, oldItem, newItem) -> {
            if (newItem != null && !newItem.equals(oldItem)) {
                onExplainedFormulaChanged.apply((SpecVerified) newItem);
            }
        };
        this.failedFormulas.valueProperty().addListener(this.formulaChangedListener);
    }

    public void setOnExplanationChanged(Function<List<FormulaCause>, Boolean> onExplanationChanged) {
        this.explanationChangedListener = change -> onExplanationChanged.apply(currentCauses);

//                (obs, oldItem, newItem) -> {
//            if (newItem != null && !newItem.equals(oldItem)) {
//                onExplanationChanged.apply(newItem);
//            }
//        };
        this.currentCauses.addListener(this.explanationChangedListener);
    }

    public List<FormulaCause> explainFormulaFor(int stepNum) {
        return this.explainFormulaImpl(stepNum);
    }

    private List<FormulaCause> explainFormulaImpl(int forStep) {
        List<FormulaCause> causes = this.ltlExplainer.explainRootForStep(forStep).getCauses().stream()
                .sorted(Comparator.comparing(FormulaCause::getStepNum).thenComparing(FormulaCause::getVarName)).collect(Collectors.toList());
        this.currentCauses.setAll(causes);
        return causes;
    }

    public void updateLtlTree() {
        ICalculatedFormula formula = this.calculationWalker.calculateRootForStep(Context.instance().getCurrentStep());
        VisGraph graph = GraphHelper.convertToGraph(formula);
        this.ltlGraph.updateGraph(graph);
    }

    public void reset() {
        this.failedFormulas.getItems().clear();
        this.ltlGraph.clear();
        this.calculationWalker = null;
        this.ltlExplainer = null;
        this.formulaByStepsTable.reset();
    }

    public void explainFormula(ActionEvent actionEvent) {
        this.explainFormulaFor(Context.instance().getCurrentStep());
    }

//    private void explainFormulaFor(int stepNum){
//        List<FormulaCause> causes = this.ltlExplainer.explainFormulaFor(stepNum);
//
////        this.formulaCausesList.getItems().clear();
////        this.formulaCausesList.setItems(FXCollections.observableArrayList(causes));
//
//        this.valueTable.highlightCauses(causes);
//        this.valueTable.refresh();
//    }
}
