package shakeanapple.backtracker.nusmvparsing;

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

    @Override
    public String toString() {
        return type + Util.par(left.name) + " := " + right + ";";
    }

    Assignment clarifyTypes(Map<String, Variable> allVarDeclarations) {
        final Variable newLeft = left.clarifyTypes(allVarDeclarations);
        final Expression newRight = right.clarifyTypes(allVarDeclarations);
        if (newLeft.type != newRight.type && newRight.type != ExpressionType.UNKNOWN) {
            throw new RuntimeException("Incompatible types " + newLeft.type + " := " + newRight.type
                    + " in assignment " + this);
        }
        return new Assignment(type, newLeft, newRight);
    }
}
