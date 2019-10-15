package shakeanapple.backtracker.ui.basiccomponentsconstructor.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.component.choice.AddChoiceDialogController;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.component.input.AddInputDialogController;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.component.output.AddOutputDialogController;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class AddComponentDialogController implements Initializable {
    @FXML
    private ComboBox<ComponentType> componentTypeComboBox;

    @FXML
    private ListView<InputVariable> inputsList;

    @FXML
    private ListView<OutputVariable> outputsList;

    @FXML
    private ListView<Choice> choicesList;

    @FXML
    private TextField delay;

    private ObservableList<BasicComponentAbstract> components;

    private Random rand;

    private ObservableList<InputVariable> inputs = FXCollections.observableArrayList();
    private ObservableList<OutputVariable> outputs = FXCollections.observableArrayList();
    private ObservableList<Choice> choices = FXCollections.observableArrayList();

    public AddComponentDialogController() {
        this.rand = new Random();
    }

    public void showAddInputDialog(ActionEvent actionEvent) throws IOException {
        URL url = new File("src\\main\\java\\shakeanapple\\backtracker\\ui\\basiccomponentsconstructor\\component\\input\\index.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent parent = fxmlLoader.load();
        AddInputDialogController dialogController = fxmlLoader.<AddInputDialogController>getController();
        dialogController.setInputsObservableList(this.inputs);

        this.showDialog(parent);
    }

    public void showAddOutputDialog(ActionEvent actionEvent) throws IOException {
        URL url = new File("src\\main\\java\\shakeanapple\\backtracker\\ui\\basiccomponentsconstructor\\component\\output\\index.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent parent = fxmlLoader.load();
        AddOutputDialogController dialogController = fxmlLoader.<AddOutputDialogController>getController();
        dialogController.setOutputsObservableList(this.outputs);

        this.showDialog(parent);
    }

    public void showAddChoiceDialog(ActionEvent actionEvent) throws IOException {
        URL url = new File("src\\main\\java\\shakeanapple\\backtracker\\ui\\basiccomponentsconstructor\\component\\choice\\index.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent parent = fxmlLoader.load();
        AddChoiceDialogController dialogController = fxmlLoader.<AddChoiceDialogController>getController();
        dialogController.setChoicesObservableList(this.choices);

        this.showDialog(parent);
    }

    public void create(ActionEvent actionEvent) {
        BasicComponentAbstract component;
        if (this.componentTypeComboBox.getValue() == ComponentType.BOOL_CHOICE || this.componentTypeComboBox.getValue() == ComponentType.INT_CHOICE) {
            component = new ChoiceComponent(this.componentTypeComboBox.getValue(), this.rand.nextLong(), this.choices, this.outputs.get(0));
        } else if (!this.delay.getText().isEmpty()) {
            if (this.inputs.size() == 2){
                component = new DelayComponent(this.rand.nextLong(), this.inputs.get(0), this.inputs.get(1), this.outputs.get(0), Integer.parseInt(this.delay.getText()));
            } else {
                component = new DelayComponent(this.rand.nextLong(), this.inputs.get(0), this.outputs.get(0), Integer.parseInt(this.delay.getText()));
            }
        } else {
            component = new BasicComponent(this.componentTypeComboBox.getValue(), this.rand.nextLong(), this.inputs, this.outputs);
        }
        this.components.add(component);

        this.closeDialog(actionEvent);
    }

    private void showDialog(Parent parent) {
        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void closeDialog(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.componentTypeComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(ComponentType.values())));
        this.choicesList.setItems(this.choices);
        this.inputsList.setItems(this.inputs);
        this.outputsList.setItems(this.outputs);
    }

    public void setComponentsObservableList(ObservableList<BasicComponentAbstract> components) {
        this.components = components;
    }
}
