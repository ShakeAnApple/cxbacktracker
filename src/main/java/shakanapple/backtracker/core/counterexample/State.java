package shakeanapple.backtracker.core.counterexample;

import shakeanapple.backtracker.core.variable.Variable;

import java.util.List;

public class State {
    private List<Variable> _variableValues;

    public State(List<Variable> variableValues) {
        _variableValues = variableValues;
    }
}
