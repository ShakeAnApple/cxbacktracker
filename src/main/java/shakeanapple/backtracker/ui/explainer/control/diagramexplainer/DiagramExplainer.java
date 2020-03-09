package shakeanapple.backtracker.ui.explainer.control.diagramexplainer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class DiagramExplainer extends VBox {
    @FXML
    private DiagramControl diagram;

    private DiagramCounterexampleExecutor diagramExecutor;
    private DiagramOutputExplainer diagramOutputExplainer;
    private Map<String, Connection> connections = new HashMap<>();
    private Map<String, Pin> diagramPins = new HashMap<>();

    private ObservableList<Cause> diagramCausesList = FXCollections.observableArrayList();

    public DiagramExplainer() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/main/explainer/diagramExplainer.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void init(){
        FunctionBlockComplex diagram = FunctionBlockComplex.parse(Context.instance().getDiagramPath());
        this.diagramExecutor = new DiagramCounterexampleExecutor(diagram, Context.instance().getCounterexample());

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

                List<Cause> causesFrom = connectionsCauses.get(connId).get(conn.getFrom().getName());
                conn.getFrom().getCausesObservable().addAll(causesFrom.stream().map(c ->
                        new shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Cause(c.getTimestamp(), c.getVarName(), c.getBlockName(), c.getValue())).collect(Collectors.toList()));

                List<Cause> causesTo = connectionsCauses.get(connId).get(conn.getTo().getName());
                conn.getTo().getCausesObservable().addAll(causesTo.stream().map(c ->
                        new shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Cause(c.getTimestamp(), c.getVarName(), c.getBlockName(), c.getValue())).collect(Collectors.toList()));
            }
        }

        return expRes.getFreshNodes().stream()
                .map(causeNode -> new Cause(causeNode.getTimestamp() - 1, causeNode.getGate().getName(), causeNode.getGate().getOwner().getName(), causeNode.getValue()))
                .sorted(Comparator.comparing(Cause::getTimestamp).thenComparing(Cause::getBlockName).thenComparing(Cause::getVarName)).collect(Collectors.toList());
    }

    private Boolean diagramPinPressHandler(shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Pin pin) {
        List<Cause> causes = this.explainCause(pin.getName(), pin.getOwner().getName(), Context.instance().getCurrentStep() + 1);
        this.diagramCausesList.clear();
        this.diagramCausesList.addAll(causes);
        return true;
    }

    private void updateDiagram(DiagramSnapshot snapshot) {
        this.clearConnections();
        if (this.diagram.isClear()) {
            Graph diagram = GraphHelper.convertToDiagramGraphNew(snapshot, this::diagramPinPressHandler);
            this.diagram.draw(diagram);
            this.recordConnections(diagram.getConnections());
            this.recordPins(diagram.getCells());
            this.updateBlockInterfaces(snapshot.getBlocks(), snapshot.getDiagramInterface());
        } else {
            this.updateBlockInterfaces(snapshot.getBlocks(), snapshot.getDiagramInterface());
        }
    }

    public void updateDiagram(){
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
            this.diagramPins.get(varName + varName).updateValue(diagramInterface.getInputsValues().get(varName));
        }
        for (String varName : diagramInterface.getOutputsValues().keySet()) {
            this.diagramPins.get(varName + varName).updateValue(diagramInterface.getOutputsValues().get(varName));
        }
    }


    private void recordConnections(List<Connection> connections) {
        this.connections = connections.stream().collect(Collectors.toMap(Connection::getId, c -> c));
    }

    private void recordPins(List<DiagramCell> cells) {
        for (DiagramCell cell : cells) {
            for (Pin pin : cell.getInputPins().values()) {
                this.diagramPins.put(pin.getOwner().getName() + pin.getName(), pin);
            }
            for (Pin pin : cell.getOutputPins().values()) {
                this.diagramPins.put(pin.getOwner().getName() + pin.getName(), pin);
            }
        }
    }

    public void reset() {
        this.diagram.clear();
        this.diagramExecutor = null;
        this.diagramOutputExplainer = null;
        this.connections = new HashMap<>();
        this.diagramPins = new HashMap<>();
    }

    public ObservableList<Cause> getDiagramCausesList(){
        return this.diagramCausesList;
    }

}
