package shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.expression;

import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Assignment;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.NuSMVModule;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.Util;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.UndeclaredVariableException;
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

    private Variable(String name, ReferenceType referenceType, ExpressionType type) {
        super(name, type);
        this.referenceType = referenceType;
    }

    private Variable(String name, boolean isNext, ExpressionType type) {
        this(name, isNext ? ReferenceType.NEXT : ReferenceType.CURRENT, type);
    }

    /**
     * Create a variable used in an expression.
     * @param name: name of the variable.
     * @param isNext: whether the next() of the variable is used in the expression.
     */
    public Variable(String name, boolean isNext) {
        this(name, isNext, ExpressionType.UNKNOWN);
    }

    /**
     * Create a declaration with type information.
     * @param name: name of the variable.
     * @param type: type of the variable.
     */
    public Variable(String name, ExpressionType type) {
        super(name, type);
        this.referenceType = ReferenceType.DECLARATION;
    }

    public Variable clarifyType(ExpressionType newType) {
        if (newType == ExpressionType.UNKNOWN) {
            return this;
        }
        if (type != ExpressionType.UNKNOWN && type != newType) {
            throw new RuntimeException("Contradictory types " + type + " and " + newType + " in  type clarification of "
                + this);
        }
        return new Variable(name, referenceType, newType);
    }

    /**
     * Create a declaration with type information.
     * @param name: name of the variable.
     * @param strType: type of the variable.
     */
    public Variable(String name, String strType) {
        super(name, strType.equals("boolean") ? ExpressionType.BOOL : ExpressionType.INT);
        this.referenceType = ReferenceType.DECLARATION;
    }

    /**
     * Create a declaration without type information.
     * @param name: name of the variable.
     */
    public Variable(String name) {
        super(name, ExpressionType.UNKNOWN);
        this.referenceType = ReferenceType.DECLARATION;
    }

    @Override
    public Variable forwardInferTypes(Map<String, Variable> allVarDeclarations) throws UndeclaredVariableException {
        final Variable declaration = allVarDeclarations.get(name);
        if (declaration == null) {
            throw new UndeclaredVariableException("Undeclared variable: " + name);
        }
        return clarifyType(declaration.type);
    }

    @Override
    public Expression propagateNext(boolean propagating, boolean nextAllowed, Assignment topLevelAssignment) {
        if (referenceType == ReferenceType.DECLARATION) {
            throw new AssertionError("Trying to take next of variable declaration " + this);
        } else if (propagating && referenceType == ReferenceType.NEXT) {
            throw new AssertionError("Trying to take next(" + this + ")");
        }
        return new Variable(name, propagating);
    }

    @Override
    public String toString() {
        switch (referenceType) {
            case CURRENT:
                return name;
            case NEXT:
                return "next" + Util.par(name);
            case DECLARATION:
                return name + ": " + type + ";";
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
