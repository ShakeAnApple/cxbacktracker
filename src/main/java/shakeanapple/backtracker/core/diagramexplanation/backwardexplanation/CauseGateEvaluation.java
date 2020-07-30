package shakeanapple.backtracker.core.diagramexplanation.backwardexplanation;

import shakeanapple.backtracker.core.diagramexplanation.model.Gate;

import java.util.*;

public class CauseGateEvaluation {
    private Gate gate;
    private Set<String> evaluations = new HashSet<>();

    public CauseGateEvaluation(Gate gate) {
        this.gate = gate;
    }

    public CauseGateEvaluation(Gate gate, String evaluation) {
        this.gate = gate;
        this.evaluations.add(evaluation);
    }

    public void addEvaluation(String valueString){
        this.evaluations.add(valueString);
    }

    public void addEvaluations(Collection<String> valuesString){
        this.evaluations.addAll(valuesString);
    }

    public Set<String> getEvaluations(){
        return this.evaluations;
    }

    public Gate getGate() {
        return this.gate;
    }
}
