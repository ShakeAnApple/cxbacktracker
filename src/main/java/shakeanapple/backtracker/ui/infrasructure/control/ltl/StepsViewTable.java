package shakeanapple.backtracker.ui.infrasructure.control.ltl;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import shakeanapple.backtracker.core.ltl.evaluation.ILtlInPlaceEvaluator;
import shakeanapple.backtracker.core.ltl.evaluation.LtlEvaluator;
import shakeanapple.backtracker.core.ltl.evaluation.LtlInPlaceEvaluator;
import shakeanapple.backtracker.core.ltl.explanation.ILtlFormulaExplainer;
import shakeanapple.backtracker.core.ltl.explanation.model.ExplanationResult;
import shakeanapple.backtracker.core.ltl.formula.model.LtlFormula;
import shakeanapple.backtracker.ui.explainer.Context;
import shakeanapple.backtracker.ui.infrasructure.control.ltl.model.FormulaNodeSnapshot;
import shakeanapple.backtracker.ui.infrasructure.control.ltl.model.FormulaStep;
import shakeanapple.backtracker.ui.infrasructure.control.ltl.view.FormulaStepView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StepsViewTable extends VBox {

    private ILtlInPlaceEvaluator ltlInStepEvaluator;

    @FXML
    private TableView<FormulaStep> formulaByStepsTable;
    private Function<FormulaNodeSnapshot, Boolean> onCauseClicked;

    public StepsViewTable() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/main/explainer/ltl/stepsViewTable.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void init(ILtlFormulaExplainer ltlExplainer, LtlFormula formula){
        this.createColumns();
        this.ltlInStepEvaluator = new LtlInPlaceEvaluator(Context.instance().getCounterexample(), formula);

        ExplanationResult explanationResult = ltlExplainer.explainRootForStep(0);
        List<FormulaStep> formulaStepSnapshots = new ArrayList<>();
        for (int step = 0; step < Context.instance().getCounterexample().length(); step++) {
            FormulaStep formulaStep = FormulaStep.fromCalculatedFormula(this.ltlInStepEvaluator.evaluateInStep(step),
                    step == Context.instance().getCounterexample().getLoopStart(), explanationResult);
            formulaStepSnapshots.add(formulaStep);
        }

        this.formulaByStepsTable.getItems().addAll(formulaStepSnapshots);
    }

    private void createColumns() {
        TableColumn<FormulaStep, String> stepNumColumn = new TableColumn<>("#");
        stepNumColumn.setCellValueFactory(param ->
                new ReadOnlyObjectWrapper<>(param.getValue().getStepNumber() + (param.getValue().isLoop() ? " loop starts here" : ""))
        );
        this.formulaByStepsTable.getColumns().add(stepNumColumn);

        TableColumn<FormulaStep, FormulaStepView> formulaColumn = new TableColumn<>("Formula evaluation");
        formulaColumn.setCellValueFactory(param ->
                new ReadOnlyObjectWrapper<>(new FormulaStepView(param.getValue(), this.onCauseClicked)));
        this.formulaByStepsTable.getColumns().add(formulaColumn);
    }

    public void setOnLtlCauseClicked(Function<FormulaNodeSnapshot, Boolean> onCauseClicked) {
        this.onCauseClicked = onCauseClicked;
    }

    public void reset() {
        this.formulaByStepsTable.setItems(FXCollections.observableArrayList());
        this.formulaByStepsTable.getColumns().clear();
        this.ltlInStepEvaluator = null;
    }
}
