package shakeanapple.backtracker.ui.infrasructure.control.causegraph.model;

import shakeanapple.backtracker.common.variable.ValueHolder;

import java.util.ArrayList;
import java.util.List;

public class RemainNode implements GraphNode {
    private final String gateFullName;
    private final boolean isRoot;

    private List<GraphNode> children;

    public boolean isRoot() {
        return this.isRoot;
    }

    public ValueHolder getValue() {
        return this.value;
    }

    public int getStep() {
        return this.step;
    }

    private final ValueHolder value;
    private final int step;

    public RemainNode(String gateFullName, boolean isRoot, ValueHolder value, int step) {
        this.gateFullName = gateFullName;
        this.isRoot = isRoot;
        this.value = value;
        this.step = step;

        this.children = new ArrayList<>();
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public String getGateFullName() {
        return this.gateFullName;
    }

    @Override
    public List<GraphNode> getChildren() {
        return this.children;
    }

    @Override
    public void addChild(GraphNode child) {
        this.children.add(child);
    }
}
