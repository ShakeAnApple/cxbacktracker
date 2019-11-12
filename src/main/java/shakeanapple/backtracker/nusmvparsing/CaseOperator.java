package shakeanapple.backtracker.nusmvparsing;

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
        super("case", inferType(conditions, options));
        this.conditions = conditions;
        this.options = options;
    }

    private static ExpressionType inferType(List<Expression> conditions, List<Expression> options) {
        if (conditions.size() != options.size() || conditions.isEmpty()) {
            throw new RuntimeException("Invalid sizes of CaseOperator arguments");
        }
        // check that there are no integers in conditions
        if (conditions.stream().anyMatch(c -> c.type == ExpressionType.INT)) {
            throw new RuntimeException("Type inference problem: one of condition types is INT in "
                    + toString(conditions, options));
        }
        // check that option types are consistent
        final Collection<ExpressionType> optionTypes = options.stream().map(o -> o.type).collect(Collectors.toSet());
        if (optionTypes.contains(ExpressionType.BOOL) && optionTypes.contains(ExpressionType.INT)) {
            throw new RuntimeException("Type inference problem: both BOOL and INT are possible in outcomes of "
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
    public Set<String> variableSet() {
        final Set<String> vars = new TreeSet<>();
        conditions.forEach(x -> vars.addAll(x.variableSet()));
        options.forEach(x -> vars.addAll(x.variableSet()));
        return vars;
    }

    @Override
    public CaseOperator clarifyTypes(Map<String, Variable> allVarDeclarations) {
        return new CaseOperator(
                conditions.stream().map(x -> x.clarifyTypes(allVarDeclarations)).collect(Collectors.toList()),
                options.stream().map(x -> x.clarifyTypes(allVarDeclarations)).collect(Collectors.toList()));
    }
}
