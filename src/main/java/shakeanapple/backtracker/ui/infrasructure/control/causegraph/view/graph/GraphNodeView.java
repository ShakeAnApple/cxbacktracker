package shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.DiagramStyles;

import java.util.List;
import java.util.function.Function;

public abstract class GraphNodeView extends NodeView {

    public GraphNodeView(GraphView parent) {
        super(parent);

    }

    protected void addButton(Function<String, Boolean> selectCausesSubGraph, String gateFullName, int timestamp) {
        Button button = new Button();
        button.setMinSize(12, 12);
        button.setMaxSize(12, 12);
        button.layoutXProperty().bind(this.getView().layoutXProperty().subtract(8));
        button.layoutYProperty().bind(this.getView().layoutYProperty().subtract(8));
        this.getParent().getChildren().add(button);
        button.onActionProperty().setValue(
                actionEvent -> {
                    selectCausesSubGraph.apply(gateFullName + " " + timestamp);
                    internalSelectCauseHandler();
                });
    }

    private void internalSelectCauseHandler() {
        this.getParent().clearHighlighting();
        this.getParent().highlightSubTreeWithRoot(this);
    }

    public abstract Rectangle getView();

    public abstract List<GraphNodeView> getChildren();

    public abstract double getWidth();

    public abstract String getName();

    public abstract double getHeight();

    public abstract void addChild(GraphNodeView child);

    public void isSelected(boolean selected) {
        if (selected){
            this.getView().setStyle(this.getView().getStyle().concat(";").concat(DiagramStyles.CAUSE_SELECTED_STYLE));
        }
        else{
            this.getView().setStyle(this.getView().getStyle().replace(DiagramStyles.CAUSE_SELECTED_STYLE, ""));
        }
    }
}
