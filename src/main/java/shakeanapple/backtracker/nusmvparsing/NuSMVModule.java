package shakeanapple.backtracker.nusmvparsing;

import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.*;

import java.util.*;
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

    private class ToFunctionBlockNetworkTransformation {
        long currentId = 0;

        final List<BasicComponentAbstract> components = new ArrayList<>();
        final List<Connection> connections = new ArrayList<>();

        long newID() {
            return currentId++;
        }

        InputVariable constantBool(boolean value, int order) {
            final long id = newID();
            return new ConstantInput(id, VarType.BOOLEAN, "const_input_" + id,
                    String.valueOf(value).toUpperCase(), order);
        }

        <T extends BasicComponentAbstract>T createComponent(T d) {
            components.add(d);
            return d;
        }

        class VarInfo {
            final Variable originalVariable;
            final InputVariable inputVariable;
            final OutputVariable delayedInputVariable;

            VarInfo(Variable originalVariable, InputVariable inputVariable, OutputVariable delayedInputVariable) {
                this.originalVariable = originalVariable;
                this.inputVariable = inputVariable;
                this.delayedInputVariable = delayedInputVariable;
            }
        }

        Block transform() throws UnresolvedTypeException {
            // 1. Output all internal variables (no DEFINEs so far)
            final List<OutputVariable> outputs = new ArrayList<>();
            for (Map.Entry<String, Variable> entry : internalVariables.entrySet()) {
                final Variable v = entry.getValue();
                outputs.add(new OutputVariable(newID(), v.getVarType(), v.name));
            }

            // 2. "First cycle" block
            final OutputVariable firstCycleOutput = new OutputVariable(newID(), VarType.BOOLEAN, "firstCycleOutput");
            final BasicComponentAbstract firstCycle = createComponent(new DelayComponent(newID(),
                    constantBool(false, 0), constantBool(true, 1), firstCycleOutput, 1));

            // 3. For each input variable, create its delayed version. Store both delayed and undelayed versions.
            final Map<String, VarInfo> varInfoMap = new HashMap<>();
            final List<InputVariable> inputs = new ArrayList<>();
            int order = 0;
            for (Map.Entry<String, Variable> entry : inputVariables.entrySet()) {
                final Variable v = entry.getValue();
                final InputVariable inputVariable = new InputVariable(newID(), v.getVarType(), v.name, order++);
                inputs.add(inputVariable);

                // create the delayed version
                final OutputVariable delayOutput = new OutputVariable(newID(), v.getVarType(), "delayOutput_"
                        + inputVariable.getId());
                createComponent(new DelayComponent(newID(), inputVariable, constantBool(false, 1), delayOutput, 1));
                varInfoMap.put(v.name, new VarInfo(v, inputVariable, delayOutput));
            }

            return new Block(name, inputs, outputs, components, connections);
        }
    }

    public Block toFunctionBlockNetwork() throws UnresolvedTypeException {
        // FIXME ensure that all types are inferred
        return (this.new ToFunctionBlockNetworkTransformation()).transform();
    }
}
