package shakeanapple.backtracker.nusmvparsing;

import java.util.*;

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
    public Set<String> variableSet() {
        return Collections.emptySet();
    }

    @Override
    public Expression clarifyTypes(Map<String, Variable> allVarDeclarations) {
        return this;
    }
}
