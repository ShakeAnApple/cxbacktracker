package shakeanapple.backtracker.nusmvparsing;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by buzhinsky on 11/11/19.
 */
public class Module {
    public final String name;
    private final List<Variable> inputVariables;
    private final List<Variable> internalVariables;
    private final List<Assignment> assignments;

    public Module(String name, List<Variable> inputVariables, List<Variable> internalVariables,
                  List<Assignment> assignments) {
        this.name = name;
        this.inputVariables = inputVariables;
        this.internalVariables = internalVariables;
        this.assignments = assignments;
    }

    @Override
    public String toString() {
        return String.join(Util.NL,
                "MODULE " + name +
                Util.par(inputVariables.stream().map(v -> v.name).collect(Collectors.joining(", "))),
                "VAR",
                internalVariables.stream().map(v -> "    " + v.name + ": ?;").collect(Collectors.joining(Util.NL)),
                "ASSIGN",
                assignments.stream().map(a -> "    " + a).collect(Collectors.joining(Util.NL)));
    }
}
