package shakeanapple.backtracker.nusmvparsing;

import java.util.*;

/**
 * Created by buzhinsky on 4/18/17.
 */
public abstract class Expression {
    public final String name;
    public final ExpressionType type;

    public Expression(String name, ExpressionType type) {
        this.name = name;
        this.type = type;
    }

    public abstract Set<String> variableSet();

    public abstract Expression clarifyTypes(Map<String, Variable> allVarDeclarations);

    public static Expression not(Expression other) {
        return new UnaryOperator("!", other);
    }

    static Expression and(Expression left, Expression right) {
        return new BinaryOperator("&", left, right);
    }

    static Expression or(Expression left, Expression right) {
        return new BinaryOperator("|", left, right);
    }
}
