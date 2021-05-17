package shakeanapple.backtracker.ui.infrasructure.control.causegraph.model;

import shakeanapple.backtracker.common.variable.ValueHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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

    private Function<String, Boolean> selectCausesSubGraphHandler;

    public RemainNode(String gateFullName, boolean isRoot, ValueHolder value, int step, Function<String, Boolean> selectCausesSubGraphHandler) {
        this.gateFullName = gateFullName;
        this.isRoot = isRoot;
        this.value = value;
        this.step = step;

        this.children = new ArrayList<>();
        this.selectCausesSubGraphHandler = selectCausesSubGraphHandler;
    }

    public Function<String, Boolean> getSelectCausesSubGraphHandler() {
        return this.selectCausesSubGraphHandler;
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
