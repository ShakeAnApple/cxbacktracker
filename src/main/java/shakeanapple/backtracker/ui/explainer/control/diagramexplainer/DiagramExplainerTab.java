package shakeanapple.backtracker.ui.explainer.control.diagramexplainer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import shakeanapple.backtracker.core.diagramexplanation.*;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.model.causetree.ExplanationItem;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.FBInterfaceSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.FunctionBlockSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.NusmvBlock;
import shakeanapple.backtracker.core.diagramexplanation.tonusmv.blocksconverters.ComplexBlockConverter;
import shakeanapple.backtracker.ui.GraphHelper;
import shakeanapple.backtracker.ui.explainer.Context;
import shakeanapple.backtracker.ui.explainer.control.diagramexplainer.model.Cause;
import shakeanapple.backtracker.ui.explainer.control.diagramexplainer.model.CauseNodeUI;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.DiagramControl;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Connection;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.DiagramCell;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Graph;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Pin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DiagramExplainerTab extends Tab {
    @FXML
    private DiagramControl diagramControl;

    @FXML
    private ListView diagramCausesList;

    private DiagramExecutor diagramExecutor;
    private DiagramOutputExplainer diagramOutputExplainer;
    private Map<String, Connection> connections = new HashMap<>();
    private Map<String, Pin> diagramPins = new HashMap<>();

    private LinkedList<String> pathInDiagram = new LinkedList<>();
    private DiagramExplainer parent;
    private FunctionBlockComplex diagram;

    private ObservableList<Cause> diagramCausesListObservable = FXCollections.observableArrayList();

    DiagramExplainerTab(String title, DiagramExplainer parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/main/explainer/diagram/diagramExplainerTab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        this.setText(title);
        this.parent = parent;

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private DiagramExplainerTab(LinkedList<String> currentPath, String currentBlockTitle, DiagramExplainer parent) {
        this(currentBlockTitle, parent);
        this.pathInDiagram = currentPath;
        this.pathInDiagram.addLast(currentBlockTitle);
    }

    private void init(FunctionBlockComplex diagram, DiagramExecutor parentExecutor) {
//        FunctionBlockComplex diagram = FunctionBlockComplex.parse(Context.instance().getDiagramPath());
        this.diagramExecutor = new SubDiagramCounterexampleExecutorNew(diagram, parentExecutor);
        this.diagram = diagram;
        this.diagramCausesList.setItems(this.diagramCausesListObservable);


        this.diagramOutputExplainer = new DiagramBackwardExplainer(diagram);
    }

    public void init(FunctionBlockComplex diagram) {
        ComplexBlockConverter converter = new ComplexBlockConverter(diagram);
        NusmvBlock block = converter.convert(true);
        try {
            FileWriter myWriter = new FileWriter("filename.smv");
            myWriter.write(block.getStatements());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
//        FunctionBlockComplex diagram = FunctionBlockComplex.parse(Context.instance().getDiagramPath());
        this.diagramExecutor = new DiagramCounterexampleExecutor(diagram, Context.instance().getCounterexample());
        this.diagram = diagram;
        this.diagramCausesList.setItems(this.diagramCausesListObservable);


        this.diagramOutputExplainer = new DiagramBackwardExplainer(diagram);
    }

    private void clearConnections() {
        for (Connection conn : this.connections.values()) {
            conn.isCauseTreeEdge(false);
        }
    }

    public List<Cause> explainCause(String varName, String blockName, int timestamp) {
        this.clearConnections();

        ExplanationItem expRes = this.diagramOutputExplainer.explain(varName, blockName, timestamp);
        CauseNodeUI causesTree = CauseNodeUI.parse(expRes.getTree().getRoots().get(0));


        Map<String, Map<String, List<Cause>>> connectionsCauses = causesTree.inferConnectionCauses();
        for (String connId : connectionsCauses.keySet()) {
            if (this.connections.containsKey(connId)) {
                Connection conn = this.connections.get(connId);
                conn.isCauseTreeEdge(true);
            }
        }
        Map<String, List<Cause>> causesForPins = causesTree.getCausesForPins();
        for (String pinName : this.diagramPins.keySet()) {
            if (causesForPins.containsKey(pinName)) {
                this.diagramPins.get(pinName).getCausesObservable().addAll(causesForPins.get(pinName).stream().map(c ->
                        new shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Cause(c.getTimestamp(), c.getVarName(), c.getBlockName(), c.getValue())
                ).collect(Collectors.toList()));
            }
        }

        List<Cause> res = expRes.getFreshNodes().stream()
                .map(causeNode -> new Cause(causeNode.getTimestamp() - 1, causeNode.getGate().getName(), causeNode.getGate().getOwner().getName(), causeNode.getValue()))
                .sorted(Comparator.comparing(Cause::getTimestamp).thenComparing(Cause::getBlockName).thenComparing(Cause::getVarName)).collect(Collectors.toList());
        this.diagramCausesListObservable.setAll(res);
        return res;
    }

    private Boolean diagramPinPressHandler(Pin pin) {
        List<Cause> causes = this.explainCause(pin.getName(), pin.getOwner().getName(), Context.instance().getCurrentStep() + 1);
        this.diagramCausesListObservable.clear();
        this.diagramCausesListObservable.addAll(causes);
        return true;
    }

    private void updateDiagram(DiagramSnapshot snapshot) {
        this.clearConnections();
        this.diagramCausesListObservable.clear();
        if (this.diagramControl.isClear()) {
            Graph diagram = GraphHelper.convertToDiagramGraphNew(snapshot, this::diagramPinPressHandler, this::onBlockDoubleClicked);
            this.diagramControl.draw(diagram);
            this.recordConnections(diagram.getConnections());
            this.recordPins(diagram.getCells());
            this.updateBlockInterfaces(snapshot.getBlocks(), snapshot.getDiagramInterface());
        } else {
            this.updateBlockInterfaces(snapshot.getBlocks(), snapshot.getDiagramInterface());
        }
    }

    private boolean onBlockDoubleClicked(String blockName) {
        FunctionBlockBase child = this.diagram.extractInternal(blockName);
        if (!(child instanceof FunctionBlockComplex)) {
            return false;
        }

        String tabTitle = this.getText() + "." + blockName;
        if (this.parent.getTabs().stream().anyMatch(tab -> tab.getText().equals(tabTitle))) {
            return false;
        }

        DiagramExplainerTab subTab = new DiagramExplainerTab(new LinkedList<>(this.pathInDiagram), tabTitle, this.parent);
        this.parent.addTab(subTab);
        subTab.init((FunctionBlockComplex) child, this.diagramExecutor);
        subTab.updateDiagram();
        System.out.println(blockName);
        return true;
    }

    public void updateDiagram() {
        DiagramSnapshot snapshot = this.diagramExecutor.moveTo(Context.instance().getCurrentStep());
        this.updateDiagram(snapshot);
    }

    private void updateBlockInterfaces(List<FunctionBlockSnapshot> blockSnapshots, FBInterfaceSnapshot diagramInterface) {
        for (FunctionBlockSnapshot fb : blockSnapshots) {
            for (String varName : fb.getFbInterface().getInputsValues().keySet()) {
                this.diagramPins.get(fb.getName() + varName).updateValue(fb.getFbInterface().getInputsValues().get(varName));
            }
            for (String varName : fb.getFbInterface().getOutputsValues().keySet()) {
                this.diagramPins.get(fb.getName() + varName).updateValue(fb.getFbInterface().getOutputsValues().get(varName));
            }
        }
        for (String varName : diagramInterface.getInputsValues().keySet()) {
            this.diagramPins.get(this.diagram.getName() + varName).updateValue(diagramInterface.getInputsValues().get(varName));
        }
        for (String varName : diagramInterface.getOutputsValues().keySet()) {
            this.diagramPins.get(this.diagram.getName() + varName).updateValue(diagramInterface.getOutputsValues().get(varName));
        }
    }


    private void recordConnections(List<Connection> connections) {
        this.connections = connections.stream().collect(Collectors.toMap(Connection::getId, c -> c));
    }

    private void recordPins(List<DiagramCell> cells) {
        for (DiagramCell cell : cells) {
            for (Pin pin : cell.getInputPins().values()) {
                this.diagramPins.put((pin.belongsToSystemInterface() ? this.diagram.getName() : pin.getOwner().getName()) + pin.getName(), pin);
            }
            for (Pin pin : cell.getOutputPins().values()) {
                this.diagramPins.put((pin.belongsToSystemInterface() ? this.diagram.getName() : pin.getOwner().getName()) + pin.getName(), pin);
            }
        }
    }

    public void reset() {
        this.diagramControl.clear();
        this.diagramExecutor = null;
        this.diagramOutputExplainer = null;
        this.connections = new HashMap<>();
        this.diagramPins = new HashMap<>();
    }

    public ObservableList<Cause> getDiagramCausesList() {
        return this.diagramCausesListObservable;
    }
}
