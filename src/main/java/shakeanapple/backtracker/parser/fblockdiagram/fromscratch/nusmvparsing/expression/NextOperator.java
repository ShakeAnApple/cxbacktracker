package shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.expression;

import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Assignment;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.NuSMVModule;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Util;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.TooDeepNextException;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable;

import java.util.Map;

/**
 * Created by buzhinsky on 4/18/17.
 */
public class NextOperator extends Expression {
    private final Expression argument;

    public NextOperator(Expression argument) {
        super("next", ExpressionType.UNKNOWN);
        this.argument = argument;
    }

    @Override
    public String toString() {
        return name + Util.par(argument.toString());
    }

    @Override
    public NextOperator forwardInferTypes(Map<String, Variable> allVarDeclarations) {
        throw new AssertionError("Next operators must be propagated to variables before type inference");
    }

    @Override
    public Expression propagateNext(boolean propagating, boolean nextAllowed, Assignment topLevelAssignment)
            throws TooDeepNextException {
        if (propagating || !nextAllowed) {
            throw new TooDeepNextException("next() nesting is too deep in " + topLevelAssignment);
        }
        return argument.propagateNext(true, false, topLevelAssignment);
    }

    @Override
    public InputVariable generate(NuSMVModule.FunctionBlockNetworkContext context, int order) {
        throw new AssertionError("Next operators must be propagated to variables before FBD generation");
    }
}
