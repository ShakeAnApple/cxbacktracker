package shakeanapple.backtracker.nusmvparsing.expression;

import shakeanapple.backtracker.nusmvparsing.NuSMVModule;
import shakeanapple.backtracker.nusmvparsing.Util;
import shakeanapple.backtracker.nusmvparsing.exceptions.UndeclaredVariableException;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.InputVariable;

import java.util.Map;

/**
 * Created by buzhinsky on 11/20/17.
 */
public class Variable extends Expression {
    public enum ReferenceType {
        CURRENT, NEXT, DECLARATION
    }

    public final ReferenceType referenceType;
    private final String strType;

    /**
     * Create a variable used in an expression.
     * @param name: name of the variable.
     * @param isNext: whether the next() of the variable is used in the expression.
     */
    public Variable(String name, boolean isNext) {
        this(name, isNext, ExpressionType.UNKNOWN);
    }

    private Variable(String name, boolean isNext, ExpressionType type) {
        super(name, type);
        this.referenceType = isNext ? ReferenceType.NEXT : ReferenceType.CURRENT;
        strType = null;
    }

    /**
     * Create a declaration with type information.
     * @param name: name of the variable.
     * @param strType: type of the variable.
     */
    public Variable(String name, String strType) {
        super(name, strType.equals("boolean") ? ExpressionType.BOOL : ExpressionType.INT);
        this.referenceType = ReferenceType.DECLARATION;
        this.strType = strType;
    }

    /**
     * Create a declaration without type information.
     * @param name: name of the variable.
     */
    public Variable(String name) {
        super(name, ExpressionType.UNKNOWN);
        this.referenceType = ReferenceType.DECLARATION;
        this.strType = null;
    }

    @Override
    public Variable forwardInferTypes(Map<String, Variable> allVarDeclarations) throws UndeclaredVariableException {
        final Variable declaration = allVarDeclarations.get(name);
        if (declaration == null) {
            throw new UndeclaredVariableException("Undeclared variable: " + name);
        }
        return declaration.type == ExpressionType.UNKNOWN ? this
                : new Variable(name, referenceType == ReferenceType.NEXT, declaration.type);
    }

    @Override
    public String toString() {
        switch (referenceType) {
            case CURRENT:
                return name;
            case NEXT:
                return "next" + Util.par(name);
            case DECLARATION:
                return name + ": " + strType + ";";
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public InputVariable generate(NuSMVModule.FunctionBlockNetworkContext context, int order)
            throws UndeclaredVariableException {
        return context.referenceVariable(this, order);
    }
}
