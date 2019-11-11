package shakeanapple.backtracker.nusmvparsing;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;

/**
 * Created by buzhinsky on 4/18/17.
 */
public class BinaryOperator extends Expression {
    public final String name;
    public final Expression leftArgument;
    public final Expression rightArgument;

    public BinaryOperator(String name, Expression leftArgument, Expression rightArgument) {
        super(name);
        this.name = name;
        this.leftArgument = leftArgument;
        this.rightArgument = rightArgument;
    }

    @Override
    public String toString() {
        return Util.par(leftArgument + " " + name + " " + rightArgument);
    }

    @Override
    public Set<String> variableSet() {
        final Set<String> result = leftArgument.variableSet();
        result.addAll(rightArgument.variableSet());
        return result;
    }

    private Expression recursion(Function<Expression, Expression> baseFunction,
                                 Function<BinaryOperator, Expression> transformation,
                                 String... specialNames) {
        final Expression processedLeft = baseFunction.apply(leftArgument);
        final Expression processedRight = baseFunction.apply(rightArgument);
        final BinaryOperator processed = new BinaryOperator(name, processedLeft, processedRight);
        return Arrays.asList(specialNames).contains(name) ? transformation.apply(processed) : processed;
    }
}
