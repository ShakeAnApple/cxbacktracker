package shakeanapple.backtracker.core.counterexample;

import shakeanapple.backtracker.common.variable.Variable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class State {
    private Map<String, Variable> variablesByNames;
    private final int order;

    public State(List<Variable> variableValues, int order) {
        this.variablesByNames = variableValues.stream().collect(Collectors.toMap(Variable::getName, (v) -> v));
        this.order = order;
    }

    public Map<String, Variable> getVarsByNames(){
        return this.variablesByNames;
    }

    public Variable getVarByName(String name){
        return this.variablesByNames.get(name);
    }

    public int getOrder(){
        return this.order;
    }
}
