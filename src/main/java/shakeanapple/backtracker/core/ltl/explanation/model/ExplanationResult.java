package shakeanapple.backtracker.core.ltl.explanation.model;

import java.util.ArrayList;
import java.util.List;

public class ExplanationResult {
    private List<FormulaCause> causes;
    private int forStep;

    private ExplanationResult(int forStep){
        this.forStep = forStep;
    }

    public ExplanationResult(List<FormulaCause> causes, int forStep) {
        this(forStep);
        this.causes = causes;
    }

    public ExplanationResult(FormulaCause cause, int forStep) {
        this(forStep);
        this.causes = new ArrayList<>();
        this.causes.add(cause);
    }

    public static ExplanationResult empty(int forStep) {
        return new ExplanationResult(new ArrayList<>(), forStep);
    }

    public List<FormulaCause> getCauses() {
        return this.causes;
    }

    public int forStep() {
        return this.forStep;
    }

    public ExplanationResult addCauses(List<FormulaCause> causes){
        this.causes.addAll(causes);
        return this;
    }
}
