package shakeanapple.backtracker.ui.explainer.control.projectinitializer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import shakeanapple.backtracker.ui.explainer.Context;
import shakeanapple.backtracker.ui.explainer.control.projectinitializer.model.CustomConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class ProjectInitializer extends VBox {
    private File initialDirectory;

    private Runnable onFileChosen;
    private Function<CustomConfig, Boolean> onConfigCompleted;

    private CustomConfig config = new CustomConfig();

    @FXML
    private Label cxLabel;
    @FXML
    private Label ltlPropertyLabel;
    @FXML
    private Label systemModelLabel;
    @FXML
    private Button loadButton;

    public ProjectInitializer() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/main/explainer/projectInitializer.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void init() {
        // so the first run after initialization from config customconfig is completed
        this.config.setFormulaCustom(Context.instance().getFormulaStr());
        this.config.setCxPathCustom(Context.instance().getCounterexample() != null ? " " : null);
        this.config.setDiagramPathCustom(Context.instance().getDiagramPath());

        this.loadButton.setDisable(true);
    }

    public final void setOnFileChosen(Runnable value) {
        this.onFileChosen = value;
    }

    public final void setOnConfigCompleted(Function<CustomConfig, Boolean> value) {
        this.onConfigCompleted = value;
    }

    private File chooseFile(ActionEvent event, List<FileChooser.ExtensionFilter> extensionFilters) {
        FileChooser chooser = new FileChooser();
        if (extensionFilters != null) {
            chooser.getExtensionFilters().addAll(extensionFilters);
        }
        chooser.setInitialDirectory(this.initialDirectory);
        chooser.setTitle("Open File");
        return chooser.showOpenDialog(((Button) event.getSource()).getScene().getWindow());
    }

    private void preProcessFile() {
        if (config.isCompleted()) {
            this.onFileChosen.run();
        }
    }

    private void clear(){
        this.config.clear();
        this.ltlPropertyLabel.setText("");
        this.cxLabel.setText("");
        this.systemModelLabel.setText("");
    }

    public void handleOpenModel(ActionEvent actionEvent) {

        List<FileChooser.ExtensionFilter> filters = Collections.singletonList(new FileChooser.ExtensionFilter("NuSMV annotated model", "*.smv"));
        File file = this.chooseFile(actionEvent, filters);
        if (file != null) {
            this.preProcessFile();

            this.initialDirectory = file.getParentFile();
            this.config.setDiagramPathCustom(file.getAbsolutePath());
            this.systemModelLabel.setText(this.config.getDiagramPathCustom());
            if (this.config.isCompleted()) {
                this.loadButton.setDisable(false);
            }
        }
    }

    public void handleOpenCounterexample(ActionEvent actionEvent) {

        File file = this.chooseFile(actionEvent, null);
        if (file != null) {
            this.preProcessFile();

            this.initialDirectory = file.getParentFile();
            this.config.setCxPathCustom(file.getAbsolutePath());
            if (this.config.useFullCx()){
                this.config.useFullCx(false);
                this.config.setFormulaCustom("");
                this.ltlPropertyLabel.setText("");
                this.loadButton.setDisable(true);
            }
            this.cxLabel.setText(this.config.getCxPathCustom());
            if (this.config.isCompleted()) {
                this.loadButton.setDisable(false);
            }
        }
    }

    // TODO for future
//    public void handleInputFormulaMenuItemClick(ActionEvent actionEvent) {
//        TextInputDialog dialog = new TextInputDialog();
//        dialog.setTitle("Input formula");
//        dialog.setContentText("Input ltl formula:");
//        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
//        stage.setMinWidth(500);
//        dialog.setResizable(true);
//        dialog.getDialogPane().setGraphic(null);
//        dialog.getDialogPane().setHeaderText("");
//
//        Optional<String> result = dialog.showAndWait();
//        if (result.isPresent()) {
//            if (this.formulaCustom != null) {
//                this.clearMainView();
//            }
//            this.formulaCustom = result.get();
//            if (this.diagramPathCustom != null && this.cxPathCustom != null) {
//                this.isReadOnly.setValue(false);
//                this.customInit();
//            }
//        }
//    }

    public void handleOpenFormulaFile(ActionEvent actionEvent) throws IOException {
        File file = this.chooseFile(actionEvent, null);
        if (file != null) {
            this.preProcessFile();

            this.initialDirectory = file.getParentFile();
            this.config.setFormulaCustom(Files.readAllLines(Path.of(file.getAbsolutePath())).stream()
                    .map(String::trim)
                    .filter(str -> !str.isBlank() && !str.isEmpty())
                    .findFirst().orElse(""));
            if (this.config.useFullCx()){
                this.config.useFullCx(false);
                this.config.setCxPathCustom("");
                this.cxLabel.setText("");
                this.loadButton.setDisable(true);
            }
            this.ltlPropertyLabel.setText(this.config.getFormulaCustom());
            if (this.config.isCompleted()) {
                this.loadButton.setDisable(false);
            }
        }
    }

    public void handleReadFromNuSMVOutput(ActionEvent actionEvent){
        File file = this.chooseFile(actionEvent, null);
        this.config.useFullCx(true);
        if (file != null) {
            this.preProcessFile();

            this.initialDirectory = file.getParentFile();
            this.config.setCxPathCustom(file.getAbsolutePath());
            this.cxLabel.setText(this.config.getCxPathCustom());
            this.ltlPropertyLabel.setText(this.config.getCxPathCustom());
            if (this.config.isCompleted()) {
                this.loadButton.setDisable(false);
            }
        }
    }

    public void handleLoadSystem(){
        this.onConfigCompleted.apply(this.config);
    }
}
