package shakeanapple.backtracker.nusmvparsing;

import java.util.*;
import java.util.function.Function;

/**
 * Created by buzhinsky on 4/18/17.
 */
public class BinaryOperator extends Expression {
    public final String name;
    private final Expression leftArgument;
    private final Expression rightArgument;

    public BinaryOperator(String name, Expression leftArgument, Expression rightArgument) {
        super(name, correctType(name));
        this.name = name;
        this.leftArgument = leftArgument;
        this.rightArgument = rightArgument;
        typeCheck();
    }

    private static ExpressionType correctType(String name) {
        if (Arrays.asList("<", "<=", ">", ">=", "<->", "->", "|", "&", "xor", "xnor", "=", "!=").contains(name)) {
            return ExpressionType.BOOL;
        } else if (Arrays.asList("+", "-", "*", "/", "mod").contains(name)) {
            return ExpressionType.INT;
        } else {
            throw new RuntimeException("Unknown binary operator " + name);
        }
    }

    private void typeCheck() {
        final Collection<ExpressionType> allTypes = Arrays.asList(leftArgument.type, rightArgument.type);
        boolean failure = !allTypes.contains(ExpressionType.UNKNOWN) && leftArgument.type != rightArgument.type;
        failure |= Arrays.asList("<", "<=", ">", ">=", "+", "-", "*", "/", "mod").contains(name)
                && allTypes.contains(ExpressionType.BOOL);
        failure |= Arrays.asList("<->", "->", "|", "&", "xor", "xnor").contains(name)
                && allTypes.contains(ExpressionType.INT);
        if (failure) {
            throw new RuntimeException("Type inference problem: " + leftArgument.type + " " + name + " "
                    + rightArgument.type + " in binary operator " + leftArgument + " " + name + " " + rightArgument);
        }
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

    @Override
    public BinaryOperator clarifyTypes(Map<String, Variable> allVarDeclarations) {
        return new BinaryOperator(name, leftArgument.clarifyTypes(allVarDeclarations),
                rightArgument.clarifyTypes(allVarDeclarations));
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
