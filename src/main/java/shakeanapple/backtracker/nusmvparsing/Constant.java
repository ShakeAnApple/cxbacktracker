package shakeanapple.backtracker.nusmvparsing;

import java.util.Collections;
import java.util.Set;

/**
 * Created by buzhinsky on 11/20/17.
 */
public class Constant extends Expression {
    public Constant(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Set<String> variableSet() {
        return Collections.emptySet();
    }
}
