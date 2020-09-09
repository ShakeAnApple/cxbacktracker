package shakeanapple.backtracker.ui.infrasructure.control.ltl.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import shakeanapple.backtracker.ui.infrasructure.control.ltl.model.FormulaNodeSnapshot;
import shakeanapple.backtracker.ui.infrasructure.control.ltl.model.FormulaStep;
import shakeanapple.backtracker.ui.infrasructure.control.ltl.model.NodeResultKind;

public class FormulaStepView extends HBox {
    public FormulaStepView(FormulaStep formulaStep) {
        for (FormulaNodeSnapshot node: formulaStep.getFormula().getNodes()){
            Label nodeLabel = new Label(node.getNodeName());
            Paint backgroundColor = node.getResultKind() == NodeResultKind.INTEGER ? Paint.valueOf(LtlTableStyles.INT_VALUE_COLOR_DEF) :
                    (node.getResult().toLowerCase().equals("true") ? Paint.valueOf(LtlTableStyles.TRUE_VALUE_COLOR_DEF) : Paint.valueOf(LtlTableStyles.FALSE_VALUE_COLOR_DEF));
            nodeLabel.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
            this.getChildren().add(nodeLabel);

            if (node.getResultKind() == NodeResultKind.INTEGER){
                nodeLabel.setTooltip(new Tooltip(node.getResult()));
            }

            if (node.isCause()){
                nodeLabel.setBorder(new Border(new BorderStroke(Paint.valueOf(LtlTableStyles.CAUSE_BORDER_COLOR_DEF), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }

            nodeLabel.setPadding(new Insets(0, 1, 0, 1));
        }
    }
}
