package shakeanapple.backtracker.ui.explainer;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import shakeanapple.backtracker.core.diagramexplanation.DiagramCounterexampleExecutor;
import shakeanapple.backtracker.core.diagramexplanation.DiagramSequentialEvaluator;
import shakeanapple.backtracker.core.diagramexplanation.DiagramWithCounterexampleEvaluator;
import shakeanapple.backtracker.core.diagramexplanation.model.snapshot.DiagramSnapshot;
import shakeanapple.backtracker.core.ltlexplanation.LtlWithCounterexampleEvaluator;
import shakeanapple.backtracker.core.ltlexplanation.LtlSequentialEvaluator;
import shakeanapple.backtracker.core.ltlexplanation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.State;
import shakeanapple.backtracker.core.ltlexplanation.model.ltlformula.model.LtlFormula;
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
import shakeanapple.backtracker.common.variable.BooleanVariable;
import shakeanapple.backtracker.common.variable.Variable;
import shakeanapple.backtracker.ui.explainer.model.graphcell.Pin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.DiagramControl;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.ViewGraph;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Cell;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.Connection;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.RectangleCell;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.visfx.graph.VisGraph;
import shakeanapple.backtracker.ui.GraphHelper;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.VisGraphControl;

import java.util.*;

public class MainController {

    @FXML
    private VisGraphControl ltlGraph;

    @FXML
    private VisGraphControl functionBlocksGraph;

    @FXML
    private DiagramControl diagram;

    private final LtlSequentialEvaluator calculationWalker;
    private DiagramSequentialEvaluator diagramEvaluator;
    private DiagramCounterexampleExecutor diagramExecutor;

    public MainController()  {
        Counterexample cx = Counterexample.load("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\cx");
        LtlFormula formula = LtlFormula.parse("G(((!(alarm) & !(criteria)) & X (criteria & !(ack_button))) -> X (alarm))");
        this.calculationWalker = new LtlWithCounterexampleEvaluator(cx, formula);

        this.diagramEvaluator = new DiagramWithCounterexampleEvaluator("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\m.smv",
                "C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\basics", cx );

        this.diagramExecutor = new DiagramCounterexampleExecutor("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\m.smv",
                "C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\basics", cx );

    }

    @FXML
    protected void updateGraph() {
        ICalculatedFormula formula = this.calculationWalker.moveNext();
        VisGraph graph = GraphHelper.convertToGraph(formula);
        this.ltlGraph.updateGraph(graph);

//        DiagramSnapshot snapshot = this.diagramEvaluator.moveNext();
        DiagramSnapshot snapshot = this.diagramExecutor.moveNext();
        //VisGraph blocksGraph = GraphHelper.convertToGraph(snapshot);
        //this.diagram.draw();updateGraph(blocksGraph);
        ViewGraph diagram = GraphHelper.convertToDiagramGraph(snapshot, this::pinPressHandler);
        if (this.diagram.isClear()) {
            this.diagram.draw(diagram);
        } else{
            this.diagram.update(diagram);
        }
    }

    private Boolean pinPressHandler(Pin pin){
        return true;
    }
  }
