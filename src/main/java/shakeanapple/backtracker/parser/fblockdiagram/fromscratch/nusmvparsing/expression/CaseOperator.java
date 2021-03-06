package shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.expression;

import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Assignment;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.NuSMVModule;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.TooDeepNextException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.TypeInferenceException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.UndeclaredVariableException;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.UnresolvedTypeException;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by buzhinsky on 11/20/17.
 */
public class CaseOperator extends Expression {
    private final List<Expression> conditions;
    private final List<Expression> options;

    public static CaseOperator ternaryOperator(Expression condition, Expression leftOption, Expression rightOption) {
        return new CaseOperator(Arrays.asList(condition, new Constant("TRUE")), Arrays.asList(leftOption, rightOption));
    }

    public CaseOperator(List<Expression> conditions, List<Expression> options) {
        this(conditions, options, ExpressionType.UNKNOWN);
    }

    private CaseOperator(List<Expression> conditions, List<Expression> options, ExpressionType type) {
        super("case", type);
        this.conditions = conditions;
        this.options = options;
    }

    private static ExpressionType inferType(List<Expression> conditions, List<Expression> options)
            throws TypeInferenceException {
        if (conditions.size() != options.size() || conditions.isEmpty()) {
            throw new RuntimeException("Invalid sizes of CaseOperator arguments");
        }
        // check that there are no integers in conditions
        if (conditions.stream().anyMatch(c -> c.type == ExpressionType.INT)) {
            throw new TypeInferenceException("Type inference problem: one of condition types is INT in "
                    + toString(conditions, options));
        }
        // check that option types are consistent
        final Collection<ExpressionType> optionTypes = options.stream().map(o -> o.type).collect(Collectors.toSet());
        if (optionTypes.contains(ExpressionType.BOOL) && optionTypes.contains(ExpressionType.INT)) {
            throw new TypeInferenceException("Type inference problem: both BOOL and INT are possible in outcomes of "
                    + toString(conditions, options));
        }
        return optionTypes.contains(ExpressionType.BOOL) ? ExpressionType.BOOL :
                optionTypes.contains(ExpressionType.INT) ? ExpressionType.INT : ExpressionType.UNKNOWN;
    }

    private static String toString(List<Expression> conditions, List<Expression> options) {
        final StringBuilder sb = new StringBuilder();
        sb.append("case ");
        for (int i = 0; i < conditions.size(); i++) {
            sb.append(conditions.get(i)).append(": ").append(options.get(i)).append("; ");
        }
        sb.append("esac");
        return sb.toString();
    }

    @Override
    public String toString() {
        return toString(conditions, options);
    }

    @Override
    public CaseOperator forwardInferTypes(Map<String, Variable> allVarDeclarations)
            throws TypeInferenceException, UndeclaredVariableException {
        final List<Expression> newConditions = new ArrayList<>();
        for (Expression e : conditions) {
            newConditions.add(e.forwardInferTypes(allVarDeclarations));
        }
        final List<Expression> newOptions = new ArrayList<>();
        for (Expression e : options) {
            newOptions.add(e.forwardInferTypes(allVarDeclarations));
        }
        return new CaseOperator(newConditions, newOptions, inferType(newConditions, newOptions));
    }

    @Override
    public Expression propagateNext(boolean propagating, boolean nextAllowed, Assignment topLevelAssignment) throws TooDeepNextException {
        return new CaseOperator(
                nextPropagateList(conditions, propagating, nextAllowed, topLevelAssignment),
                nextPropagateList(options, propagating, nextAllowed, topLevelAssignment),
                type);
    }

    @Override
    public InputVariable generate(NuSMVModule.FunctionBlockNetworkContext context, int order)
            throws UnresolvedTypeException, UndeclaredVariableException {
        final List<Choice> choices = new ArrayList<>();
        for (int i = 0; i < conditions.size(); i++) {
            choices.add(new Choice(conditions.get(i).generate(context, i),
                    options.get(i).generate(context, i)));
        }
        final OutputVariable output = context.newOutputVariable(getVarType());
        context.createComponent(new ChoiceComponent(getChoiceType(), context.newID(), choices, output));
        return context.createWire(output, order);
    }
}
