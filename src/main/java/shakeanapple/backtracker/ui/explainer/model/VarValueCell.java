package shakeanapple.backtracker.ui.explainer.model;

import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import shakeanapple.backtracker.ui.infrasructure.FunctionTwo;

import java.util.function.Function;

public class VarValueCell extends TableCell<CxVar, VarValueForStep> {
    private Function<VarValueForStep, Boolean> onCellClicked;

    public VarValueCell(Function<VarValueForStep, Boolean> onCellClicked) {
        this.onCellClicked = onCellClicked;
    }

    @Override
    protected void updateItem(VarValueForStep item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || getTableRow() == null) {
            setText(null);
            setGraphic(null);
        } else {
            super.setOnMouseClicked((me) -> this.onCellClicked.apply(item));
            this.defineStyle(item.isCauseProperty().getValue());
            this.setText(item.getValue());
            item.isCauseProperty()
                    .addListener((observable, oldValue, newValue) ->
                            defineStyle(newValue));
        }
    }

    private void defineStyle(Boolean isCause) {
        if (isCause){
            this.setStyle("-fx-background-color: red");
        } else{
            this.setStyle(null);
        }
    }
}
