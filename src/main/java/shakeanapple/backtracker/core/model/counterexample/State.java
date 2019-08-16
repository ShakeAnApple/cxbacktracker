package shakeanapple.backtracker.core.model.counterexample;

import shakeanapple.backtracker.core.model.variable.Variable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class State {
    private List<Variable> variableValues;
    private Map<String, Variable> variablesByNames;
    private final int order;

    public State(List<Variable> variableValues, int order) {
        this.variableValues = variableValues;
        this.variablesByNames = variableValues.stream().collect(Collectors.toMap((v) -> v.getInfo().getName(), (v) -> v));
        this.order = order;
    }

    public Variable getVarByName(String name){
        return this.variablesByNames.get(name);
    }

    public int getOrder(){
        return this.order;
    }
}
