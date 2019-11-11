package shakeanapple.backtracker.nusmvparsing;

import java.util.Set;

/**
 * Created by buzhinsky on 11/20/17.
 */
public class UnaryArithmeticOperator extends ArithmeticExpression {
    private final ArithmeticExpression argument;

    public UnaryArithmeticOperator(String name, ArithmeticExpression argument) {
        super(name);
        this.argument = argument;
    }

    @Override
    public String toString() {
        return name + Util.par(argument.toString());
    }

    @Override
    public Set<String> variableSet() {
        return argument.variableSet();
    }
}
