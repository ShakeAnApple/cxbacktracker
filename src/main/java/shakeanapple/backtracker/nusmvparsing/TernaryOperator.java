package shakeanapple.backtracker.nusmvparsing;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by buzhinsky on 11/20/17.
 */
public class TernaryOperator extends Expression {
    private final Expression condition;
    private final Expression leftOption;
    private final Expression rightOption;

    public TernaryOperator(Expression condition, Expression leftOption,
                           Expression rightOption) {
        super("?:");
        this.condition = condition;
        this.leftOption = leftOption;
        this.rightOption = rightOption;
    }

    @Override
    public String toString() {
        return Util.par(condition + " ? " + leftOption + " : " + rightOption);
    }

    @Override
    public Set<String> variableSet() {
        final Set<String> vars = new TreeSet<>();
        vars.addAll(condition.variableSet());
        vars.addAll(leftOption.variableSet());
        vars.addAll(rightOption.variableSet());
        return vars;
    }
}
