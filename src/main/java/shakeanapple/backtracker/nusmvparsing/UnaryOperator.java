package shakeanapple.backtracker.nusmvparsing;

import java.util.Set;
import java.util.function.Function;

/**
 * Created by buzhinsky on 4/18/17.
 */
public class UnaryOperator extends Expression {
    public final String name;
    public final Expression argument;

    public UnaryOperator(String name, Expression argument) {
        super(name);
        this.name = name;
        this.argument = argument;
    }

    @Override
    public String toString() {
        return name + Util.par(argument.toString());
    }

    @Override
    public Set<String> variableSet() {
        return argument.variableSet();
    }

    private Expression recursion(Function<Expression, Expression> baseFunction,
                                 Function<UnaryOperator, Expression> transformation, String specialName) {
        final Expression processedArgument = baseFunction.apply(argument);
        final UnaryOperator processed = new UnaryOperator(name, processedArgument);
        return name.equals(specialName) ? transformation.apply(processed) : processed;
    }
}
