package shakeanapple.backtracker.ui.explainer.model;

import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;

public class VarValueCell extends TableCell<CxVar, VarValueForStep> {
    @Override
    protected void updateItem(VarValueForStep item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || getTableRow() == null) {
            setText(null);
            setGraphic(null);
        } else {
            this.defineStyle(item.isCauseProperty().getValue());
            this.setText(item.getValue());
            // If the statis is changing dynamic you have to add the following:
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
