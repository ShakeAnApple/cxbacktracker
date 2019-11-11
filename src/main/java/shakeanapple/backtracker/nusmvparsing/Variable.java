package shakeanapple.backtracker.nusmvparsing;

import java.util.Collections;
import java.util.Set;

/**
 * Created by buzhinsky on 11/20/17.
 */
public class Variable extends Expression {
    public enum ReferenceType {
        CURRENT, NEXT, UNDEFINED
    }

    public ReferenceType referenceType;

    public Variable(String name, ReferenceType referenceType) {
        super(name);
        this.referenceType = referenceType;
    }

    public Variable(String name) {
        this(name, ReferenceType.UNDEFINED);
    }

    @Override
    public String toString() {
        switch (referenceType) {
            case CURRENT:
                return name;
            case NEXT:
                return "next" + Util.par(name);
            case UNDEFINED:
                return "Variable[" + name + "]";
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public Set<String> variableSet() {
        return Collections.singleton(name);
    }
}
