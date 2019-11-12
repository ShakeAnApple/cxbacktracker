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

    public abstract Expression forwardInferTypes(Map<String, Variable> allVarDeclarations) throws TypeInferenceException;
}
