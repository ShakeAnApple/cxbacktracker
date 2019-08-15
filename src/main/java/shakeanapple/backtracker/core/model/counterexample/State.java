package shakeanapple.backtracker.core.model.counterexample;

import shakeanapple.backtracker.core.model.variable.Variable;

import java.util.List;

public class State {
    private List<Variable> variableValues;
    private final int order;

    public State(List<Variable> variableValues, int order) {
        this.variableValues = variableValues;
        this.order = order;
    }

    public int getOrder(){
        return this.order;
    }
}
