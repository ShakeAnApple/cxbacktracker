package shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.graph;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.model.ChangeNode;
import shakeanapple.backtracker.ui.infrasructure.control.causegraph.view.DiagramStyles;

import java.util.ArrayList;
import java.util.List;

public class ChangeNodeView extends NodeView implements GraphNodeView {
    private ChangeNode node;
    private Rectangle view;
    private String name;
    private List<GraphNodeView> children = new ArrayList<>();

    public ChangeNodeView(Group parent, ChangeNode node) {
        super(parent);
        this.node = node;

        this.view = new Rectangle(DiagramStyles.NODE_DEF_WIDTH, DiagramStyles.NODE_DEF_HEIGHT);
        view.setFill(DiagramStyles.CHANGE_NODE_COLOR);
        view.setStroke(DiagramStyles.NODE_STROKE_COLOR);

        this.name = node.getGateFullName() + (node.isRoot() ? " (root)" : "") + '\n' + node.getStep() + ": " + node.getValue() + '\n' + "Last updated at: " + node.getLastUpdatedStep();
        Label label = new Label(this.name);
        label.layoutXProperty().bind(view.layoutXProperty().add(2));
        label.layoutYProperty().bind(view.layoutYProperty()
                .add(view.heightProperty().divide(2))
                .subtract(label.heightProperty().divide(2)));
        label.addEventHandler(MouseEvent.ANY, e -> this.view.fireEvent(e));
        view.widthProperty().bind(label.widthProperty().add(4));
        view.heightProperty().bind(label.heightProperty().add(4));

        parent.getChildren().add(view);
        parent.getChildren().add(label);
    }

    @Override
    public Node getView() {
        return this.view;
    }

    @Override
    public List<GraphNodeView> getChildren() {
        return this.children;
    }

    @Override
    public double getWidth() {
        return this.view.widthProperty().get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getHeight() {
        return this.view.heightProperty().get();
    }

    @Override
    public void addChild(GraphNodeView child) {
        this.children.add(child);
    }
}
