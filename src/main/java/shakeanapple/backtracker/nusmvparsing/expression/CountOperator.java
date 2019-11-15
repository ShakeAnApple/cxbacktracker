package shakeanapple.backtracker.nusmvparsing.expression;

import shakeanapple.backtracker.nusmvparsing.Assignment;
import shakeanapple.backtracker.nusmvparsing.NuSMVModule;
import shakeanapple.backtracker.nusmvparsing.Util;
import shakeanapple.backtracker.nusmvparsing.exceptions.TooDeepNextException;
import shakeanapple.backtracker.nusmvparsing.exceptions.TypeInferenceException;
import shakeanapple.backtracker.nusmvparsing.exceptions.UndeclaredVariableException;
import shakeanapple.backtracker.nusmvparsing.exceptions.UnresolvedTypeException;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.BasicComponent;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.ComponentType;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.OutputVariable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by buzhinsky on 4/18/17.
 */
public class CountOperator extends Expression {
    private final List<Expression> arguments;

    public CountOperator(List<Expression> arguments) {
        super("count", ExpressionType.INT);
        this.arguments = arguments;
    }

    private void typeCheck() throws TypeInferenceException {
        for (Expression argument : arguments) {
            if (argument.type == ExpressionType.INT) {
                throw new TypeInferenceException("Type inference problem: argument " + argument + " inside " + this
                        + " has type INT");
            }
        }
    }

    @Override
    public String toString() {
        return "count" + Util.par(arguments.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }

    @Override
    public CountOperator forwardInferTypes(Map<String, Variable> allVarDeclarations) throws TypeInferenceException,
            UndeclaredVariableException {
        final List<Expression> newArguments = new ArrayList<>();
        for (Expression e : arguments) {
            newArguments.add(e.forwardInferTypes(allVarDeclarations));
        }
        final CountOperator result = new CountOperator(newArguments);
        result.typeCheck();
        return result;
    }

    @Override
    public Expression propagateNext(boolean propagating, boolean nextAllowed, Assignment topLevelAssignment)
            throws TooDeepNextException {
        return new CountOperator(nextPropagateList(arguments, propagating, nextAllowed, topLevelAssignment));
    }

    @Override
    public InputVariable generate(NuSMVModule.FunctionBlockNetworkContext context, int order)
            throws UndeclaredVariableException, UnresolvedTypeException {
        final List<InputVariable> inputs = new ArrayList<>();
        for (int i = 0; i < arguments.size(); i++) {
            inputs.add(arguments.get(i).generate(context, i));
        }
        final OutputVariable output = context.newOutputVariable(getVarType());
        context.createComponent(new BasicComponent(ComponentType.COUNT, context.newID(), inputs,
                Collections.singletonList(output)));
        return context.createWire(output, order);
    }
}
