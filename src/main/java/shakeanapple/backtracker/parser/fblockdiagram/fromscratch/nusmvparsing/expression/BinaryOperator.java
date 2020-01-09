package shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.expression;

import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Assignment;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.NuSMVModule;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Util;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.TooDeepNextException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.TypeInferenceException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.UndeclaredVariableException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.UnresolvedTypeException;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.BasicComponent;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.ComponentType;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.OutputVariable;

import java.util.*;

/**
 * Created by buzhinsky on 4/18/17.
 */
public class BinaryOperator extends Expression {
    private final Expression leftArgument;
    private final Expression rightArgument;

    public BinaryOperator(String name, Expression leftArgument, Expression rightArgument) {
        super(name, correctType(name));
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
    public BinaryOperator forwardInferTypes(Map<String, Variable> allVarDeclarations) throws TypeInferenceException,
            UndeclaredVariableException {
        final BinaryOperator result = new BinaryOperator(name, leftArgument.forwardInferTypes(allVarDeclarations),
                rightArgument.forwardInferTypes(allVarDeclarations));
        result.typeCheck();
        return result;
    }

    @Override
    public Expression propagateNext(boolean propagating, boolean nextAllowed, Assignment topLevelAssignment) throws TooDeepNextException {
        final List<Expression> propagated = nextPropagateList(
                Arrays.asList(leftArgument, rightArgument),
                propagating, nextAllowed, topLevelAssignment);
        return new BinaryOperator(name, propagated.get(0), propagated.get(1));
    }

    @Override
    public InputVariable generate(NuSMVModule.FunctionBlockNetworkContext context, int order)
            throws UndeclaredVariableException, UnresolvedTypeException {
        ComponentType componentType;
        switch (name) {
            case "<":
                componentType = ComponentType.LESS;
                break;
            case "<=":
                componentType = ComponentType.LESS_EQ;
                break;
            case ">":
                componentType = ComponentType.GREATER;
                break;
            case ">=":
                componentType = ComponentType.GREATER_EQ;
                break;
            case "<->":
            case "=":
            case "xnor":
                componentType = ComponentType.EQ;
                break;
            case "->":
                return new BinaryOperator("|", new UnaryOperator("!", leftArgument), rightArgument)
                        .generate(context, order);
            case "|":
                componentType = ComponentType.OR;
                break;
            case "&":
                componentType = ComponentType.AND;
                break;
            case "xor":
                return new UnaryOperator("!", new BinaryOperator("<->", leftArgument, rightArgument))
                        .generate(context, order);
            case "!=":
                return new UnaryOperator("!", new BinaryOperator("=", leftArgument, rightArgument))
                        .generate(context, order);
            case "+":
                componentType = ComponentType.PLUS;
                break;
            case "-":
                return new BinaryOperator("+", leftArgument, new UnaryOperator("-", rightArgument))
                        .generate(context, order);
            case "*":
                componentType = ComponentType.MUL;
                break;
            case "/":
                componentType = ComponentType.DIV;
                break;
            case "mod":
                componentType = ComponentType.MOD;
                break;
//                throw new RuntimeException("Conversion of operator " + name + " to FBD is not supported yet"); // FIXME
            default:
                throw new RuntimeException("Unknown binary operator " + name);
        }

        final InputVariable left = leftArgument.generate(context, 0);
        final InputVariable right = rightArgument.generate(context, 1);
        final OutputVariable output = context.newOutputVariable(getVarType());
        context.createComponent(new BasicComponent(componentType, context.newID(), Arrays.asList(left, right),
                Collections.singletonList(output)));
        return context.createWire(output, order);
    }
}
