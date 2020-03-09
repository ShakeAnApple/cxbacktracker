package shakeanapple.backtracker.ui.explainer.control.menubar;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import shakeanapple.backtracker.ui.explainer.Context;
import shakeanapple.backtracker.ui.explainer.control.menubar.model.CustomConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class MenuBarCustom extends VBox {
    private File initialDirectory;

    private Runnable onFileChosen;
    private Function<CustomConfig, Boolean> onConfigCompleted;

    private CustomConfig config = new CustomConfig();

    public MenuBarCustom() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/main/explainer/menuBarCustom.fxml"));
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
        return chooser.showOpenDialog(((MenuItem) event.getSource()).getParentPopup().getScene().getWindow());
    }

    private void preProcessFile() {
        if (config.isCompleted()) {
            config.clear();
            this.onFileChosen.run();
        }
    }

    public void handleOpenModelMenuItemClick(ActionEvent actionEvent) {

        List<FileChooser.ExtensionFilter> filters = Collections.singletonList(new FileChooser.ExtensionFilter("NuSMV typed model", "*.smv"));
        File file = this.chooseFile(actionEvent, filters);
        if (file != null) {
            this.preProcessFile();

            this.initialDirectory = file.getParentFile();
            this.config.setDiagramPathCustom(file.getAbsolutePath());
            if (this.config.isCompleted()) {
                this.onConfigCompleted.apply(this.config);
//                this.isReadOnly.setValue(false);
//                this.customInit();
            }
        }
    }

    public void handleOpenCounterexampleMenuItemClick(ActionEvent actionEvent) {

        //List<FileChooser.ExtensionFilter> filters = Collections.singletonList(new FileChooser.ExtensionFilter("Plain text", "*.*"));
        File file = this.chooseFile(actionEvent, null);
        if (file != null) {
            this.preProcessFile();

            this.initialDirectory = file.getParentFile();
            this.config.setCxPathCustom(file.getAbsolutePath());
            if (this.config.isCompleted()) {
                this.onConfigCompleted.apply(config);

//                this.isReadOnly.setValue(false);
//                this.customInit();
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

    public void handleInputFormulaMenuItemClick(ActionEvent actionEvent) throws IOException {
        File file = this.chooseFile(actionEvent, null);
        if (file != null) {
            this.preProcessFile();

            this.initialDirectory = file.getParentFile();
            this.config.setFormulaCustom(Files.readAllLines(Path.of(file.getAbsolutePath())).stream()
                    .map(String::trim)
                    .filter(str -> !str.isBlank() && !str.isEmpty())
                    .findFirst().orElse(""));
            if (this.config.isCompleted()) {
                this.onConfigCompleted.apply(config);

//                this.isReadOnly.setValue(false);
//                this.customInit();
            }
        }
    }


    public void handleExitMenuItemClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}
