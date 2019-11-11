package shakeanapple.backtracker.nusmvparsing;

import java.util.*;

/**
 * Created by buzhinsky on 4/18/17.
 */
public abstract class Expression {
    public final String name;

    public Expression(String name) {
        this.name = name;
    }

    public abstract Set<String> variableSet();

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
