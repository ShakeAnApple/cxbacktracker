package shakeanapple.backtracker.ui.basiccomponentsconstructor.component.choice;

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
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.*;

import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddChoiceDialogController implements Initializable {
    @FXML
    private ComboBox<VarType> inputTypeComboBox;

    @FXML
    private TextField inputName;

    @FXML
    private TextField constantValue;

    @FXML
    private CheckBox isConstant;

    @FXML
    private ComboBox<VarType> outputTypeComboBox;

    @FXML
    private TextField outputName;

    @FXML
    private TextField defaultValue;

    private ObservableList<Choice> choices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.outputTypeComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(VarType.values())));
        this.inputTypeComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(VarType.values())));
    }

    @FXML
    public void add(ActionEvent event){
        Random r = new Random();
        InputVariable input;
        if (this.isConstant.isSelected()){
            input = new ConstantInput(r.nextLong(), this.inputTypeComboBox.getValue(), this.inputName.getText(), this.constantValue.getText());
        } else{
            input = new InputVariable(r.nextLong(), this.inputTypeComboBox.getValue(), this.inputName.getText());
        }
        Choice choice = new Choice(input,
                new OutputVariable(r.nextLong(), this.outputTypeComboBox.getValue(), this.outputName.getText(), this.defaultValue.getText()));
        this.choices.add(choice);

        this.closeDialog(event);
    }

    private void closeDialog(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setChoicesObservableList(ObservableList<Choice> choices) {
        this.choices = choices;
    }
}
