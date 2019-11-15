package shakeanapple.backtracker.nusmvparsing;

import shakeanapple.backtracker.nusmvparsing.exceptions.TooDeepNextException;
import shakeanapple.backtracker.nusmvparsing.exceptions.TypeInferenceException;
import shakeanapple.backtracker.nusmvparsing.exceptions.UndeclaredVariableException;
import shakeanapple.backtracker.nusmvparsing.expression.Expression;
import shakeanapple.backtracker.nusmvparsing.expression.ExpressionType;
import shakeanapple.backtracker.nusmvparsing.expression.Variable;

import java.util.Map;

/**
 * Created by buzhinsky on 11/11/19.
 */
public class Assignment {
    public enum Type {
        INIT, NEXT;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public final Type type;
    private final Variable left;
    private final Expression right;

    public Assignment(Type type, Variable left, Expression right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    public Variable getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return type + Util.par(left.name) + " := " + right + ";";
    }

    Assignment forwardInferTypes(Map<String, Variable> allVarDeclarations)
            throws TypeInferenceException, UndeclaredVariableException {
        final Variable newLeft = left.forwardInferTypes(allVarDeclarations);
        final Expression newRight = right.forwardInferTypes(allVarDeclarations);
        if (newLeft.type != newRight.type && newRight.type != ExpressionType.UNKNOWN) {
            throw new RuntimeException("Incompatible types " + newLeft.type + " := " + newRight.type
                    + " in assignment " + this);
        }
        return new Assignment(type, newLeft, newRight);
    }

    Assignment propagateNext() throws TooDeepNextException {
        return new Assignment(type, left, right.propagateNext(false, type == Type.NEXT, this));
    }
}
