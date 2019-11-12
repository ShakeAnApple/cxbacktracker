package shakeanapple.backtracker.nusmvparsing;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

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

    private void typeCheck() throws TypeInferenceException {
        final Collection<ExpressionType> allTypes = Arrays.asList(leftArgument.type, rightArgument.type);
        boolean failure = !allTypes.contains(ExpressionType.UNKNOWN) && leftArgument.type != rightArgument.type;
        failure |= Arrays.asList("<", "<=", ">", ">=", "+", "-", "*", "/", "mod").contains(name)
                && allTypes.contains(ExpressionType.BOOL);
        failure |= Arrays.asList("<->", "->", "|", "&", "xor", "xnor").contains(name)
                && allTypes.contains(ExpressionType.INT);
        if (failure) {
            throw new TypeInferenceException("Type inference problem: " + leftArgument.type + " " + name + " "
                    + rightArgument.type + " in binary operator " + leftArgument + " " + name + " " + rightArgument);
        }
    }

    @Override
    public String toString() {
        return Util.par(leftArgument + " " + name + " " + rightArgument);
    }

    @Override
    public BinaryOperator forwardInferTypes(Map<String, Variable> allVarDeclarations) throws TypeInferenceException {
        final BinaryOperator result = new BinaryOperator(name, leftArgument.forwardInferTypes(allVarDeclarations),
                rightArgument.forwardInferTypes(allVarDeclarations));
        result.typeCheck();
        return result;
    }
}
