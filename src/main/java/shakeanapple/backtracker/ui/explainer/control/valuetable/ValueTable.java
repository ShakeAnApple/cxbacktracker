package shakeanapple.backtracker.ui.explainer.control.valuetable;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import shakeanapple.backtracker.core.counterexample.State;
import shakeanapple.backtracker.core.ltl.explanation.model.FormulaCause;
import shakeanapple.backtracker.ui.explainer.Context;
import shakeanapple.backtracker.ui.explainer.control.valuetable.model.CxVar;
import shakeanapple.backtracker.ui.explainer.control.valuetable.model.VarValueCell;
import shakeanapple.backtracker.ui.explainer.control.valuetable.model.VarValueForStep;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ValueTable extends VBox {
    @FXML
    private TableView<CxVar> variablesByStepsTable;
    private ObservableList<CxVar> varsTableItems = FXCollections.observableArrayList();

    private Function<VarValueForStep, Boolean> onTableCellClicked;

    public ValueTable() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/main/explainer/valueTable.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void init() {

        TableColumn<CxVar, String> c = new TableColumn<>("Var name");
        c.setCellValueFactory(param ->
                new ReadOnlyObjectWrapper<>(param.getValue().getVarName())
        );
        this.variablesByStepsTable.getColumns().add(c);

        for (int i = 0; i < Context.instance().getCounterexample().length(); i++) {
            final int finalIdx = i;
            TableColumn<CxVar, VarValueForStep> column = new TableColumn<>(String.valueOf(i));
            column.setCellValueFactory(param ->
                    new ReadOnlyObjectWrapper<>(param.getValue().getVarValues().get(finalIdx))
            );
            column.setCellFactory(param -> new VarValueCell(this.onTableCellClicked));
            column.setPrefWidth(50);
            this.variablesByStepsTable.getColumns().add(column);
        }

        Map<String, List<VarValueForStep>> varsValues = new HashMap<>();
        for (int step = 0; step < Context.instance().getCounterexample().length(); step++) {
            State cxState = Context.instance().getCounterexample().getPath().get(step);
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

    public void highlightCauses(List<FormulaCause> causes){
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
    }

    public void refresh() {
        this.variablesByStepsTable.refresh();
    }

    public void reset() {
        this.variablesByStepsTable.setItems(FXCollections.observableArrayList());
        this.variablesByStepsTable.getColumns().clear();
        this.varsTableItems = FXCollections.observableArrayList();
    }

    public void setOnTableCellClicked(Function<VarValueForStep, Boolean> onTableCellClicked) {
        this.onTableCellClicked = onTableCellClicked;
    }

}
