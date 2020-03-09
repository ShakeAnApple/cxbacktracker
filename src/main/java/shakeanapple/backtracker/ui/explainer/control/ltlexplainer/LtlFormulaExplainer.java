package shakeanapple.backtracker.ui.explainer.control.ltlexplainer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import shakeanapple.backtracker.core.ltl.evaluation.LtlEvaluator;
import shakeanapple.backtracker.core.ltl.evaluation.LtlWithCounterexampleEvaluator;
import shakeanapple.backtracker.core.ltl.evaluation.model.ICalculatedFormula;
import shakeanapple.backtracker.core.ltl.explanation.ILtlFormulaExplainer;
import shakeanapple.backtracker.core.ltl.explanation.model.FormulaCause;
import shakeanapple.backtracker.core.ltl.formula.model.LtlFormula;
import shakeanapple.backtracker.ui.GraphHelper;
import shakeanapple.backtracker.ui.explainer.Context;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.VisGraphControl;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.visfx.graph.VisGraph;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class LtlFormulaExplainer extends VBox {
    @FXML
    private TextField formulaField;
    @FXML
    private VisGraphControl ltlGraph;

    private LtlEvaluator calculationWalker;
    private ILtlFormulaExplainer ltlExplainer;

    public LtlFormulaExplainer() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view/main/explainer/ltlexplainer.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public void init() {
        LtlFormula formula = LtlFormula.parse(Context.instance().getFormulaStr());
        this.calculationWalker = new LtlWithCounterexampleEvaluator(Context.instance().getCounterexample(), formula);
        this.ltlExplainer = new shakeanapple.backtracker.core.ltl.explanation.LtlFormulaExplainer(formula, Context.instance().getCounterexample(), this.calculationWalker);
        this.formulaField.setText(Context.instance().getFormulaStr());
    }

    public List<FormulaCause> explainFormulaFor(int stepNum) {
        return this.explainFormulaImpl(stepNum);
    }

    private List<FormulaCause> explainFormulaImpl(int forStep) {
        List<FormulaCause> causes = this.ltlExplainer.explainRootForStep(forStep).getCauses().stream()
                .sorted(Comparator.comparing(FormulaCause::getStepNum).thenComparing(FormulaCause::getVarName)).collect(Collectors.toList());
        return causes;
    }

    public void updateLtlTree() {
        ICalculatedFormula formula = this.calculationWalker.calculateRootForStep(Context.instance().getCurrentStep());
        VisGraph graph = GraphHelper.convertToGraph(formula);
        this.ltlGraph.updateGraph(graph);
    }

    public void reset() {
        this.formulaField.setText("");
        this.ltlGraph.clear();
        this.calculationWalker = null;
        this.ltlExplainer = null;
    }
}
