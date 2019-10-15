package shakeanapple.backtracker.ui.basiccomponentsconstructor;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shakeanapple.backtracker.common.XmlSerializer;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Block;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.component.AddComponentDialogController;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.component.input.AddInputDialogController;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.component.output.AddOutputDialogController;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.*;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.BasicComponent;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.BasicComponentAbstract;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.ChoiceComponent;
import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.Connection;
import shakeanapple.backtracker.ui.control.diagram.DiagramControl;
import shakeanapple.backtracker.ui.control.diagram.ViewGraph;
import shakeanapple.backtracker.ui.infrasructure.FunctionTwo;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainController implements Initializable {
    @FXML
    private ListView<Connection> connectionsList;

    @FXML
    private DiagramControl diagramControl;

    @FXML
    private ListView<InputVariable> inputsList;

    @FXML
    private ListView<OutputVariable> outputsList;

    @FXML
    private TextField blockName;

    private ObservableList<InputVariable> inputs = FXCollections.observableArrayList();
    private ObservableList<OutputVariable> outputs = FXCollections.observableArrayList();

    private ViewGraph graph;
    private ConnectionBuilder connectionBuilder;

    public MainController() {
        this.graph = new ViewGraph();
        this.connectionBuilder = new ConnectionBuilder();
    }

    private ObservableList<BasicComponentAbstract> components = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Connection> connections = FXCollections.observableArrayList();

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

    private void showDialog(Parent parent) {
        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private boolean pinPressHandler(Button b, long id){
        String color = this.connectionBuilder.registerPin(id);
        b.setStyle(String.format("-fx-background-color: %s", color));
        if (this.connectionBuilder.ready()){
            Connection conn = this.connectionBuilder.get();
            this.connections.add(conn);
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Random r = new Random();
        this.connectionsList.setItems(connections);

        this.inputsList.setItems(this.inputs);
        this.outputsList.setItems(this.outputs);


        FunctionTwo<Button, Long, Boolean> pressHandler = this::pinPressHandler;
        Function<Long, Boolean> checkHandler = this::isInvertedPressHandler;
        this.components.addListener((ListChangeListener<BasicComponentAbstract>) change -> {
            change.next();
            BasicComponentAbstract newComponent = change.getAddedSubList().get(0);
            if (newComponent instanceof ChoiceComponent){
                graph.addCell(new ChoiceComponentCell(r.nextLong(), (ChoiceComponent) newComponent, pressHandler, checkHandler));
            } else{
                graph.addCell(new BasicComponentCell(r.nextLong(), (BasicComponent) newComponent, pressHandler, checkHandler));
            }
            diagramControl.draw(graph);
        });

        this.inputs.addListener((ListChangeListener<InputVariable>) change -> {
            change.next();
            InputVariable newVar = change.getAddedSubList().get(0);
            graph.addCell(new ComponentVarCell(newVar.getId(), newVar.getName(), pressHandler));
            diagramControl.draw(graph);
        });

        this.outputs.addListener((ListChangeListener<OutputVariable>) change -> {
            change.next();
            OutputVariable newVar = change.getAddedSubList().get(0);
            graph.addCell(new ComponentVarCell(newVar.getId(), newVar.getName(), pressHandler));
            diagramControl.draw(graph);
        });
    }

    private Boolean isInvertedPressHandler(Long pinId) {
        Connection c = this.connections.stream().filter(con -> con.getToId() == pinId).findFirst().orElse(null);
        if (c != null){
            c.isInverted(!c.isInverted());
            return true;
        }
        return false;
    }


    public void showAddComponentDialog(ActionEvent actionEvent) throws IOException {
        URL url = new File("src\\main\\java\\shakeanapple\\backtracker\\ui\\basiccomponentsconstructor\\component\\index.fxml").toURI().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent parent = fxmlLoader.load();
        AddComponentDialogController dialogController = fxmlLoader.<AddComponentDialogController>getController();
        dialogController.setComponentsObservableList(this.components);

        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void saveAsXml() throws IOException, JAXBException {
        List<shakeanapple.backtracker.parser.basiccomponents.xmlmodel.BasicComponentAbstract> compsXml = this.components.stream().map(BasicComponentAbstract::translate).collect(Collectors.toList());
        List<shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Connection> connectionsXml = this.connections.stream().map(Connection::translate).collect(Collectors.toList());
        List<shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable> inputsXml = this.inputs.stream().map(InputVariable::translate).collect(Collectors.toList());
        List<shakeanapple.backtracker.parser.basiccomponents.xmlmodel.OutputVariable> outputsXml = this.outputs.stream().map(OutputVariable::translate).collect(Collectors.toList());

        Block block = new Block(this.blockName.getText(), inputsXml, outputsXml, compsXml, connectionsXml);
        XmlSerializer.serializeToXML(block, "C:\\Users\\ovsianp1\\projects\\SEARCH\\basic-components\\" + this.blockName.getText() + ".xml");
    }
}
