package shakeanapple.backtracker.nusmvparsing;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Created by buzhinsky on 4/18/17.
 */
public class UnaryOperator extends Expression {
    public final String name;
    private final Expression argument;

    public UnaryOperator(String name, Expression argument) {
        super(name, correctType(name));
        this.name = name;
        this.argument = argument;
        typeCheck();
    }

    private static ExpressionType correctType(String name) {
        switch (name) {
            case "!":
                return ExpressionType.BOOL;
            case "-":
                return ExpressionType.INT;
            default:
                throw new RuntimeException("Unknown unary operator " + name);
        }
    }

    @Override
    public String toString() {
        return name + Util.par(argument.toString());
    }

    @Override
    public Set<String> variableSet() {
        return argument.variableSet();
    }

    @Override
    public UnaryOperator clarifyTypes(Map<String, Variable> allVarDeclarations) {
        return new UnaryOperator(name, argument.clarifyTypes(allVarDeclarations));
    }

    private void typeCheck() {
        if (argument.type != ExpressionType.UNKNOWN && name.equals("!") != (argument.type == ExpressionType.BOOL)) {
            throw new RuntimeException("Type inference problem: " + name + argument.type + " in unary operator "
                    + argument);
        }
    }

    private Expression recursion(Function<Expression, Expression> baseFunction,
                                 Function<UnaryOperator, Expression> transformation, String specialName) {
        final Expression processedArgument = baseFunction.apply(argument);
        final UnaryOperator processed = new UnaryOperator(name, processedArgument);
        return name.equals(specialName) ? transformation.apply(processed) : processed;
    }
}
