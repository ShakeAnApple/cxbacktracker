package shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.expression;

import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.BasicComponent;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.ComponentType;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.OutputVariable;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Assignment;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.NuSMVModule;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Util;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.TooDeepNextException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.TypeInferenceException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.UndeclaredVariableException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.UnresolvedTypeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MaxOperator extends Expression {
    private final List<Expression> arguments;

    public MaxOperator(List<Expression> arguments) {
        super("max", ExpressionType.INT);
        this.arguments = arguments;
    }

    private void typeCheck() throws TypeInferenceException {
        for (Expression argument : arguments) {
            if (argument.type == ExpressionType.BOOL) {
                throw new TypeInferenceException("Type inference problem: argument " + argument + " inside " + this
                        + " has type BOOL");
            }
        }
    }

    @Override
    public String toString() {
        return "max" + Util.par(arguments.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }

    @Override
    public MaxOperator forwardInferTypes(Map<String, Variable> allVarDeclarations) throws TypeInferenceException,
            UndeclaredVariableException {
        final List<Expression> newArguments = new ArrayList<>();
        for (Expression e : arguments) {
            newArguments.add(e.forwardInferTypes(allVarDeclarations));
        }
        final MaxOperator result = new MaxOperator(newArguments);
        result.typeCheck();
        return result;
    }

    @Override
    public Expression propagateNext(boolean propagating, boolean nextAllowed, Assignment topLevelAssignment)
            throws TooDeepNextException {
        return new MaxOperator(nextPropagateList(arguments, propagating, nextAllowed, topLevelAssignment));
    }

    @Override
    public InputVariable generate(NuSMVModule.FunctionBlockNetworkContext context, int order)
            throws UndeclaredVariableException, UnresolvedTypeException {
        final List<InputVariable> inputs = new ArrayList<>();
        for (int i = 0; i < arguments.size(); i++) {
            inputs.add(arguments.get(i).generate(context, i));
        }
        final OutputVariable output = context.newOutputVariable(getVarType());
        context.createComponent(new BasicComponent(ComponentType.MAX, context.newID(), inputs,
                Collections.singletonList(output)));
        return context.createWire(output, order);
    }
}
