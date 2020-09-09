package shakeanapple.backtracker.ui.infrasructure.control.ltl;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import shakeanapple.backtracker.core.ltl.evaluation.LtlEvaluator;
import shakeanapple.backtracker.core.ltl.explanation.ILtlFormulaExplainer;
import shakeanapple.backtracker.core.ltl.explanation.model.ExplanationResult;
import shakeanapple.backtracker.core.ltl.formula.model.LtlFormula;
import shakeanapple.backtracker.ui.explainer.Context;
import shakeanapple.backtracker.ui.infrasructure.control.ltl.model.FormulaCalculatedFlatSnapshot;
import shakeanapple.backtracker.ui.infrasructure.control.ltl.model.FormulaStep;
import shakeanapple.backtracker.ui.infrasructure.control.ltl.view.FormulaStepView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StepsViewTable extends VBox {

    @FXML
    private TableView<FormulaStep> formulaByStepsTable;
    private ObservableList<FormulaStep> varsTableItems = FXCollections.observableArrayList();

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

    public void init(LtlEvaluator calculationWalker, ILtlFormulaExplainer ltlExplainer, LtlFormula formula){
        this.createColumns();

        ExplanationResult explanationResult = ltlExplainer.explainRootForStep(0);
        List<FormulaStep> formulaStepSnapshots = new ArrayList<>();
        for (int step = 0; step < Context.instance().getCounterexample().length(); step++) {
            FormulaStep formulaStep = FormulaStep.fromCalculatedFormula(calculationWalker.calculateRootForStep(step),
                    step == Context.instance().getCounterexample().getLoopStart(), explanationResult);
            formulaStepSnapshots.add(formulaStep);
        }

        this.formulaByStepsTable.getItems().addAll(formulaStepSnapshots);


        // snapshots of the formula for all steps
        // explanation for the 0-th step

    }

    private void createColumns() {
        TableColumn<FormulaStep, String> stepNumColumn = new TableColumn<>("#");
        stepNumColumn.setCellValueFactory(param ->
                new ReadOnlyObjectWrapper<>(param.getValue().getStepNumber() + (param.getValue().isLoop() ? "Loop starts here" : ""))
        );
        this.formulaByStepsTable.getColumns().add(stepNumColumn);

        TableColumn<FormulaStep, FormulaStepView> formulaColumn = new TableColumn<>("Formula evaluation");
        formulaColumn.setCellValueFactory(param ->
                new ReadOnlyObjectWrapper<>(new FormulaStepView(param.getValue())));
        this.formulaByStepsTable.getColumns().add(formulaColumn);
    }
}
