package shakeanapple.backtracker.ui.explainer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import shakeanapple.backtracker.core.diagramexplanation.*;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.ConnectionSnapshot;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.ltlexplanation.LtlWithCounterexampleEvaluator;
import shakeanapple.backtracker.core.ltlexplanation.LtlSequentialEvaluator;
import shakeanapple.backtracker.core.ltlexplanation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.LtlFormula;
import shakeanapple.backtracker.ui.explainer.model.Cause;
import shakeanapple.backtracker.ui.explainer.model.graph.cell.Pin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.DiagramControl;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.ViewGraph;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.DiagramConnection;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.visfx.graph.VisGraph;
import shakeanapple.backtracker.ui.GraphHelper;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.VisGraphControl;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    @FXML
    public ListView causesList;

    @FXML
    private VisGraphControl ltlGraph;

    @FXML
    private VisGraphControl functionBlocksGraph;

    @FXML
    private DiagramControl diagram;

    private final LtlSequentialEvaluator calculationWalker;
    private DiagramSequentialEvaluator diagramEvaluator;
    private DiagramCounterexampleExecutor diagramExecutor;
    private DiagramOutputExplainer diagramOutputExplainer;

    private Map<String, DiagramConnection> connections = new HashMap<>();
    private int currentStep = 0;
    private DiagramEvaluationCache cache = new DiagramEvaluationCache();

    public MainController()  {
        Counterexample cx = Counterexample.load("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\cx");
        LtlFormula formula = LtlFormula.parse("G(((!(alarm) & !(criteria)) & X (criteria & !(ack_button))) -> X (alarm))");
        this.calculationWalker = new LtlWithCounterexampleEvaluator(cx, formula);

        FunctionBlockComplex diagram = FunctionBlockComplex.parse("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\m.smv",
                "C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\basics");


        this.diagramEvaluator = new DiagramWithCounterexampleEvaluator(diagram, cx );
        this.diagramExecutor = new DiagramCounterexampleExecutor(diagram, cx );

        this.diagramOutputExplainer = new DiagramBackwardExplainer(diagram);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.causesList.setOnMouseClicked(this::handleCauseChosen);
    }

    private void handleCauseChosen(MouseEvent event){
        Cause cause = (Cause)(this.causesList.getSelectionModel().getSelectedItem());
        this.explainCause(cause.getVarName(), cause.getBlockName(), cause.getTimestamp());
        this.renderDiagramForStep(cause.getTimestamp());
    }

    private void renderDiagramForStep(int timestamp) {
        DiagramSnapshot snapshot = this.cache.getByStep(timestamp);
        this.updateConnections(snapshot.getConnections());
    }

    private void explainCause(String varName, String blockName, int timestamp){
        ObservableList<Cause> causes =  FXCollections.observableList(
                this.diagramOutputExplainer.explain(varName, blockName, timestamp).stream().map(c -> new Cause(c.getTimestamp(), c.getGate().getOwner().getName(), c.getGate().getName(), c.getValue())).collect(Collectors.toList())
        );
        this.causesList.setItems(causes);
    }

    private Boolean pinPressHandler(Pin pin){
        this.explainCause(pin.getName(), pin.getOwner().getName(), this.currentStep);
        return true;
    }

    @FXML
    protected void updateGraph() {
        this.currentStep ++;
        ICalculatedFormula formula = this.calculationWalker.moveNext();
        VisGraph graph = GraphHelper.convertToGraph(formula);
        this.ltlGraph.updateGraph(graph);

//        DiagramSnapshot snapshot = this.diagramEvaluator.moveNext();
        DiagramSnapshot snapshot = this.diagramExecutor.moveNext();
        this.cache.add(snapshot, this.currentStep);
        //VisGraph blocksGraph = GraphHelper.convertToGraph(snapshot);
        //this.diagram.draw();updateGraph(blocksGraph);
        if (this.diagram.isClear()) {
            ViewGraph diagram = GraphHelper.convertToDiagramGraph(snapshot, this::pinPressHandler);
            this.diagram.draw(diagram);
            this.recordConnections(diagram.getConnections());
        } else{
            this.updateConnections(snapshot.getConnections());
        }
    }

    private void updateConnections(List<ConnectionSnapshot> connections) {
        for (ConnectionSnapshot snap: connections){
            this.connections.get(snap.getId()).updateValue(snap.getValue());
        }
    }

    private void recordConnections(List<DiagramConnection> connections){
        this.connections = connections.stream().collect(Collectors.toMap(DiagramConnection::getId, c -> c));
    }


  }
