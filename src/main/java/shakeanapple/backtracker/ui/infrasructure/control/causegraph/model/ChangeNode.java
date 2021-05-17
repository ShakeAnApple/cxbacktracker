package shakeanapple.backtracker.ui.infrasructure.control.causegraph.model;

import shakeanapple.backtracker.common.variable.ValueHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ChangeNode implements GraphNode {
    private final String gateFullName;
    private final boolean isRoot;

    private final ValueHolder value;
    private final int step;

    private final ValueHolder prevValue;
    // so the value becomes new at prevStep + 1
    private final int prevStep;

    private List<GraphNode> children;

    private Function<String, Boolean> selectCausesSubGraphHandler;



    public ChangeNode(String gateFullName, boolean isRoot, ValueHolder value, int step, ValueHolder prevValue, int prevStep, Function<String, Boolean> selectCausesSubGraphHandler) {
        this.gateFullName = gateFullName;
        this.isRoot = isRoot;
        this.value = value;
        this.step = step;
        this.prevValue = prevValue;
        this.prevStep = prevStep;

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

    public boolean isRoot() {
        return this.isRoot;
    }

    public int getLastUpdatedStep() {
        return this.prevStep + 1;
    }

    public ValueHolder getValue() {
        return this.value;
    }

    public int getStep() {
        return this.step;
    }
}
