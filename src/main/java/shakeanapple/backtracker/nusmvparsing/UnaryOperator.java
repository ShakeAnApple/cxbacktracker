package shakeanapple.backtracker.nusmvparsing;

import java.util.Map;

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
    public UnaryOperator forwardInferTypes(Map<String, Variable> allVarDeclarations) throws TypeInferenceException {
        final UnaryOperator result = new UnaryOperator(name, argument.forwardInferTypes(allVarDeclarations));
        result.typeCheck();
        return result;
    }

    private void typeCheck() throws TypeInferenceException {
        if (argument.type != ExpressionType.UNKNOWN && name.equals("!") != (argument.type == ExpressionType.BOOL)) {
            throw new TypeInferenceException("Type inference problem: " + name + argument.type + " in unary operator "
                    + argument);
        }
    }
}
