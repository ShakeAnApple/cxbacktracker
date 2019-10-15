package shakeanapple.backtracker.ui.basiccomponentsconstructor.component.input;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.BasicComponentAbstract;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.ConstantInput;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.InputVariable;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.VarType;

import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class AddInputDialogController implements Initializable {

    @FXML
    private ComboBox<VarType> inputTypeComboBox;

    @FXML
    private TextField inputName;

    @FXML
    private TextField constantValue;

    @FXML
    private TextField order;

    @FXML
    private CheckBox isConstant;

    private ObservableList<InputVariable> inputs;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.inputTypeComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(VarType.values())));
    }

    @FXML
    public void add(ActionEvent event){
        Random r = new Random();
        InputVariable input;
        if (this.isConstant.isSelected()){
            input = new ConstantInput(r.nextLong(), this.inputTypeComboBox.getValue(), this.inputName.getText(), this.constantValue.getText(), Integer.valueOf(this.order.getText()));
        } else{
            input = new InputVariable(r.nextLong(), this.inputTypeComboBox.getValue(), this.inputName.getText(), Integer.valueOf(this.order.getText()));
        }
        this.inputs.add(input);

        this.closeDialog(event);
    }

    private void closeDialog(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setInputsObservableList(ObservableList<InputVariable> inputs) {
        this.inputs = inputs;
    }
}
