package shakeanapple.backtracker.ui.explainer.control.diagramexplainer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import shakeanapple.backtracker.core.diagramexplanation.DiagramBackwardExplainer;
import shakeanapple.backtracker.core.diagramexplanation.DiagramCounterexampleExecutor;
import shakeanapple.backtracker.core.diagramexplanation.DiagramOutputExplainer;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.FBInterfaceSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.FunctionBlockSnapshot;
import shakeanapple.backtracker.ui.GraphHelper;
import shakeanapple.backtracker.ui.explainer.Context;
import shakeanapple.backtracker.ui.explainer.control.diagramexplainer.model.Cause;
import shakeanapple.backtracker.ui.explainer.control.diagramexplainer.model.CauseNodeUI;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.DiagramControl;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Connection;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.DiagramCell;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Graph;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Pin;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Cause> explainCause(String varName, String blockName, int timestamp) {
        return this.systemOverviewTab.explainCause(varName, blockName, timestamp);
    }

    public void updateDiagram(){
        for (Tab tab: this.getTabs()){
            ((DiagramExplainerTab)tab).updateDiagram();
        }
//        this.systemOverviewTab.updateDiagram();
    }

    public void reset() {
        this.getTabs().clear();
//        this.systemOverviewTab.reset();
    }

    public ObservableList<Cause> getDiagramCausesList(){
        return this.systemOverviewTab.getDiagramCausesList();
    }

    public void addTab(DiagramExplainerTab subTab) {
        this.getTabs().add(subTab);
        this.getSelectionModel().select(subTab);
    }
}
