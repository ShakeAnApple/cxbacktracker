package shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.expression;

import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Assignment;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.NuSMVModule;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Util;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.TooDeepNextException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.TypeInferenceException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.UndeclaredVariableException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.UnresolvedTypeException;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * Created by buzhinsky on 4/18/17.
 */
public class UnaryOperator extends Expression {
    private final Expression argument;

    public UnaryOperator(String name, Expression argument) {
        super(name, correctType(name));
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
        return name + Util.par(argument);
    }

    @Override
    public UnaryOperator forwardInferTypes(Map<String, Variable> allVarDeclarations) throws TypeInferenceException,
            UndeclaredVariableException {
        final UnaryOperator result = new UnaryOperator(name, argument.forwardInferTypes(allVarDeclarations));
        result.typeCheck();
        return result;
    }

    @Override
    public Expression propagateNext(boolean propagating, boolean nextAllowed, Assignment topLevelAssignment)
            throws TooDeepNextException {
        return new UnaryOperator(name, argument.propagateNext(propagating, nextAllowed, topLevelAssignment));
    }

    private void typeCheck() throws TypeInferenceException {
        if (argument.type != ExpressionType.UNKNOWN && name.equals("!") != (argument.type == ExpressionType.BOOL)) {
            throw new TypeInferenceException("Type inference problem: " + name + argument.type + " in unary operator "
                    + argument);
        }
    }

    @Override
    public InputVariable generate(NuSMVModule.FunctionBlockNetworkContext context, int order)
            throws UndeclaredVariableException, UnresolvedTypeException {
        final InputVariable argumentResult;
        final OutputVariable output;
        switch (name) {
            case "-":
                output = context.newOutputVariable(VarType.INTEGER);
                argumentResult = argument.generate(context, 1);
                context.createComponent(new BasicComponent(ComponentType.MINUS, context.newID(),
                        Arrays.asList(context.constantInt(0,0), argumentResult),
                        Collections.singletonList(output)));
                return context.createWire(output, order);
            case "!":
                output = context.newOutputVariable(VarType.BOOLEAN);
                argumentResult = argument.generate(context, 0);
                context.createComponent(new BasicComponent(ComponentType.ASSIGN, context.newID(),
                        Collections.singletonList(argumentResult), Collections.singletonList(output)));
                return context.createWire(output, order, true);
            default:
                throw new AssertionError();
        }
    }
}
