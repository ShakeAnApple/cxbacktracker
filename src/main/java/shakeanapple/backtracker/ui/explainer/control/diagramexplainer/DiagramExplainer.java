package shakeanapple.backtracker.ui.explainer.control.diagramexplainer;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.ui.explainer.Context;

import java.io.IOException;

public class DiagramExplainer extends TabPane {
    private DiagramExplainerTab systemOverviewTab;


    public DiagramExplainer() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/main/explainer/diagram/diagramExplainer.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        this.setStyle("-fx-background-color:white");

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void init(){
        this.systemOverviewTab = new DiagramExplainerTab("System", this);

        this.systemOverviewTab.init(FunctionBlockComplex.parse(Context.instance().getDiagramPath()));
        this.systemOverviewTab.closableProperty().setValue(false);

        this.getTabs().add(this.systemOverviewTab);
    }

    public void explainCause(String varName, String blockName, int timestamp) {
        this.systemOverviewTab.explainCause(varName, blockName, timestamp);
    }

    public void updateDiagram(){
        for (Tab tab: this.getTabs()){
            ((DiagramExplainerTab)tab).updateDiagram();
        }
    }

    public void reset() {
        this.getTabs().clear();
    }

    public void addTab(DiagramExplainerTab subTab) {
        this.getTabs().add(subTab);
        this.getSelectionModel().select(subTab);
    }
}
