package shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.expression;

import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Assignment;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.NuSMVModule;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by buzhinsky on 11/20/17.
 */
public class Constant extends Expression {
    public Constant(String name) {
        super(name, inferType(name));
    }

    private static ExpressionType inferType(String name) {
        return Arrays.asList("TRUE", "FALSE").contains(name) ? ExpressionType.BOOL : ExpressionType.INT;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Expression forwardInferTypes(Map<String, Variable> allVarDeclarations) {
        return this;
    }

    @Override
    public Expression propagateNext(boolean propagating, boolean nextAllowed, Assignment topLevelAssignment) {
        return this;
    }

    @Override
    public InputVariable generate(NuSMVModule.FunctionBlockNetworkContext context, int order) {
        return type == ExpressionType.INT
                ? context.constantInt(Integer.parseInt(name), order)
                : context.constantBool(Boolean.parseBoolean(name.toLowerCase()), order);
    }
}
