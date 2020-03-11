package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.common.variable.ValueHolder;

import java.util.HashMap;
import java.util.Map;

public class FunctionBlockInputsHistory {
    private Map<Integer, Map<String, ValueHolder>> inputsBySteps = new HashMap<>();

    public Map<String, ValueHolder> getVarsValuesByStep(int stepNum){
        return this.inputsBySteps.get(stepNum);
    }

    public void record(int stepNum, Map<String, ValueHolder> varsValues){
        this.inputsBySteps.put(stepNum, varsValues);
    }

}
