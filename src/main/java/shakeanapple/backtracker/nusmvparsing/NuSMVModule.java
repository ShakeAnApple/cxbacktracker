package shakeanapple.backtracker.nusmvparsing;

import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by buzhinsky on 11/11/19.
 */
public class NuSMVModule {
    public final String name;
    private final Map<String, Variable> inputVariables = new LinkedHashMap<>();
    private final Map<String, Variable> internalVariables = new LinkedHashMap<>();
    private final Map<String, Variable> allVariables = new LinkedHashMap<>();
    private final List<Assignment> assignments;

    public NuSMVModule(String name, List<Variable> inputVariables, List<Variable> internalVariables,
                       List<Assignment> assignments) {
        this.name = name;
        for (Variable v : inputVariables) {
            this.inputVariables.put(v.name, v);
            this.allVariables.put(v.name, v);
        }
        for (Variable v : internalVariables) {
            this.internalVariables.put(v.name, v);
            this.allVariables.put(v.name, v);
        }
        this.assignments = assignments;
    }

    void clarifyTypes() throws TypeInferenceException {
        final List<Assignment> clarifiedAssignments = new ArrayList<>();
        for (Assignment a : assignments) {
            clarifiedAssignments.add(a.forwardInferTypes(allVariables));
        }
        assignments.clear();
        assignments.addAll(clarifiedAssignments);
    }

    @Override
    public String toString() {
        return String.join(Util.NL,
                "MODULE " + name +
                Util.par(inputVariables.keySet().stream().collect(Collectors.joining(", "))),
                "VAR",
                internalVariables.values().stream().map(v -> "    " + v).collect(Collectors.joining(Util.NL)),
                "ASSIGN",
                assignments.stream().map(a -> "    " + a).collect(Collectors.joining(Util.NL)));
    }

    public Block toFunctionBlockNetwork() {
        final List<InputVariable> inputs = new ArrayList<>();
        final List<OutputVariable> outputs = new ArrayList<>();
        final List<BasicComponentAbstract> components = new ArrayList<>();
        final List<Connection> connections = new ArrayList<>();
        return new Block(name, inputs, outputs, components, connections);
    }
}
