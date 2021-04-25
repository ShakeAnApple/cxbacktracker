package shakeanapple.backtracker.ui.explainer.control.diaramhistorychangeexplainer;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import shakeanapple.backtracker.core.diagramexplanation.DiagramCounterexampleExecutor;
import shakeanapple.backtracker.core.diagramexplanation.DiagramExecutor;
import shakeanapple.backtracker.core.diagramexplanation.SubDiagramCounterexampleExecutorNew;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.ui.explainer.Context;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DiagramExplainer extends TabPane {
    private DiagramExplainerTab systemOverviewTab;
    private FunctionBlockComplex diagram;

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

    public void init() {
        this.systemOverviewTab = new DiagramExplainerTab("System", this);
        this.diagram = FunctionBlockComplex.parse(Context.instance().getDiagramPath());
        this.systemOverviewTab.init(this.diagram);
        this.systemOverviewTab.closableProperty().setValue(false);

        this.getTabs().add(this.systemOverviewTab);
    }

    public void explainCause(String varName, List<String> blockPath, int timestamp) {
        String tabTitle = blockPath.size() >= 2 ? "System." + String.join(".", blockPath.subList(0, blockPath.size() - 1)) : "System";
        String[] parts = varName.split("\\.");
        varName = parts.length > 1 ? parts[parts.length - 1] : parts[0];
        if (this.getTabs().stream().noneMatch(tab -> tab.getText().equals(tabTitle))) {
            List<String> parentBlockPath = blockPath.subList(0, blockPath.size() - 1);
            DiagramExplainerTab newTab = new DiagramExplainerTab(new LinkedList<>(parentBlockPath), tabTitle, this);
            FunctionBlockComplex block = (FunctionBlockComplex) this.diagram.extractInternal(parentBlockPath);
            DiagramExecutor executor = this.inferExecutorFor(parentBlockPath);
            this.addTab(newTab);
            newTab.init(block, executor);
            newTab.updateDiagram();
            String a = varName;
            Platform.runLater(() -> {
                newTab.explainCause(a, Collections.singletonList(blockPath.get(blockPath.size() - 1)), timestamp);
            });
        } else {
            DiagramExplainerTab tab = (DiagramExplainerTab) this.getTabs().stream().filter(t -> t.getText().equals(tabTitle)).findFirst().get();
            this.getSelectionModel().select(tab);
            tab.explainCause(varName, Collections.singletonList(blockPath.size() > 0 ? blockPath.get(blockPath.size() - 1) : ""), timestamp);
        }
    }

    private DiagramExecutor inferExecutorFor(List<String> blockPath) {
        DiagramExecutor exec = null;
        DiagramExecutor parentExec = new DiagramCounterexampleExecutor((FunctionBlockComplex) this.diagram.clone(), Context.instance().getCounterexample());
        if (blockPath.size() > 1) {
            FunctionBlockComplex parentBlock = this.diagram;
            for (String block : blockPath) {
                parentBlock = (FunctionBlockComplex) parentBlock.extractInternal(block);
                exec = new SubDiagramCounterexampleExecutorNew(parentBlock, parentExec);
                parentExec = exec;
            }
            return exec;
        }
        return parentExec;
    }

    public void updateDiagram() {
        for (Tab tab : this.getTabs()) {
            ((DiagramExplainerTab) tab).updateDiagram();
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
