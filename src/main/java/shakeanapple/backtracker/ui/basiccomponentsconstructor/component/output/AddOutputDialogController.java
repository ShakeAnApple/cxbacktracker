package shakeanapple.backtracker.ui.basiccomponentsconstructor.component.output;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.OutputVariable;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.VarType;

import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class AddOutputDialogController implements Initializable {
    @FXML
    private ComboBox<VarType> outputTypeComboBox;

    @FXML
    private TextField outputName;

    @FXML
    private TextField defaultValue;

    private ObservableList<OutputVariable> outputs;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.outputTypeComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(VarType.values())));
    }

    @FXML
    public void add(ActionEvent event){
        Random r = new Random();
        this.outputs.add(
                new OutputVariable(r.nextLong(), this.outputTypeComboBox.getValue(), this.outputName.getText(), this.defaultValue.getText())
        );
        this.closeDialog(event);
    }

    private void closeDialog(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setOutputsObservableList(ObservableList<OutputVariable> outputs) {
        this.outputs = outputs;
    }
}
