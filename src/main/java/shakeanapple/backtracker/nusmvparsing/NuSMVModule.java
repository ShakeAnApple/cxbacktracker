package shakeanapple.backtracker.nusmvparsing;

import shakeanapple.backtracker.nusmvparsing.exceptions.*;
import shakeanapple.backtracker.nusmvparsing.expression.Variable;
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
    private final Map<AssignmentInfo, Assignment> assignments = new LinkedHashMap<>();

    private static class AssignmentInfo {
        final String varName;
        final Assignment.Type type;

        AssignmentInfo(String varName, Assignment.Type type) {
            this.varName = varName;
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AssignmentInfo that = (AssignmentInfo) o;
            if (varName != null ? !varName.equals(that.varName) : that.varName != null) return false;
            return type == that.type;
        }

        @Override
        public int hashCode() {
            int result = varName != null ? varName.hashCode() : 0;
            result = 31 * result + (type != null ? type.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return type + Util.par(varName);
        }
    }

    private final List<DuplicateAssignmentException> savedExceptions = new ArrayList<>();

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
        for (Assignment a : assignments) {
            final AssignmentInfo info = new AssignmentInfo(a.getLeft().name, a.type);
            if (this.assignments.put(info, a) != null) {
                savedExceptions.add(new DuplicateAssignmentException("Duplicate assignment " + info));
            }
        }
    }

    public void clarifyTypes() throws TypeInferenceException, UndeclaredVariableException, DuplicateAssignmentException,
            TooDeepNextException {
        if (!savedExceptions.isEmpty()) {
            throw savedExceptions.get(0);
        }
        // push next() to variables
        for (AssignmentInfo key : new LinkedHashSet<>(assignments.keySet())) {
            assignments.put(key, assignments.get(key).propagateNext());
        }
        // perform forward type inference based on the types of variables and operators
        for (AssignmentInfo key : new LinkedHashSet<>(assignments.keySet())) {
            assignments.put(key, assignments.get(key).forwardInferTypes(allVariables));
        }
        // clarify types of all variables based on assignments
        for (Map<String, Variable> map : Arrays.asList(internalVariables, allVariables)) {
            for (Assignment a : assignments.values()) {
                final String name = a.getLeft().name;
                map.put(name, map.get(name).clarifyType(a.getLeft().type));
            }
        }
    }

    @Override
    public String toString() {
        return String.join(Util.NL,
                "MODULE " + name + Util.par(inputVariables.values().stream()
                        .map(v -> v.toString().replace(";", ""))
                        .collect(Collectors.joining(", "))),
                "VAR", internalVariables.values().stream().map(v -> "    " + v).collect(Collectors.joining(Util.NL)),
                "ASSIGN", assignments.values().stream().map(a -> "    " + a).collect(Collectors.joining(Util.NL)));
    }

    public class FunctionBlockNetworkContext {
        private long currentId = 0;

        private final List<BasicComponentAbstract> components = new ArrayList<>();
        private final List<Connection> connections = new ArrayList<>();
        private final Map<String, VarInfo> varInfo = new HashMap<>();

        public long newID() {
            return currentId++;
        }

        private InputVariable constant(Object value, int order) {
            final long id = newID();
            return new ConstantInput(id, value instanceof Integer ? VarType.INTEGER : VarType.BOOLEAN,
                    "const_input_" + id, String.valueOf(value).toUpperCase(), order);
        }

        public OutputVariable newOutputVariable(VarType type) {
            final long id = newID();
            return new OutputVariable(id, type, "output_variable_" + id);
        }

        public InputVariable constantBool(boolean value, int order) {
            return constant(value, order);
        }

        public InputVariable constantInt(int value, int order) {
            return constant(value, order);
        }

        public void createComponent(BasicComponentAbstract d) {
            components.add(d);
        }

        private OutputVariable delay(InputVariable vIn, InputVariable vDefault) {
            if (vIn.getType() != vDefault.getType()) {
                throw new AssertionError();
            }
            final OutputVariable output = newOutputVariable(vIn.getType());
            createComponent(new DelayComponent(newID(), vIn, vDefault, output, 1));
            return output;
        }

        // Delay where the default value does not matter.
        private OutputVariable delayWithDummyDefault(InputVariable vIn) {
            return delay(vIn, constant(vIn.getType() == VarType.BOOLEAN ? false : 0, 1));
        }

        public InputVariable referenceVariable(Variable v, int order) throws UndeclaredVariableException {
            final VarInfo info = varInfo.get(v.name);
            if (info == null) {
                throw new UndeclaredVariableException("Undeclared variable: " + v.name);
            }
            return createWire(v.referenceType == Variable.ReferenceType.NEXT
                    ? info.undelayedSource : info.delayedSource, order);
        }

        public InputVariable createWire(Object from, int order) {
            return createWire(from, order, false);
        }

        public InputVariable createWire(Object from, int order, boolean inverted) {
            final VarType type;
            final String name;
            final long id;
            if (from instanceof InputVariable) {
                type = ((InputVariable) from).getType();
                name = ((InputVariable) from).getName();
                id = ((InputVariable) from).getId();
            } else if (from instanceof OutputVariable) {
                type = ((OutputVariable) from).getType();
                name = ((OutputVariable) from).getName();
                id = ((OutputVariable) from).getId();
            } else {
                throw new AssertionError();
            }
            final InputVariable result = new InputVariable(newID(), type, name, order);
            if (id == result.getId()) {
                throw new RuntimeException("Attempt to connect a variable to itself");
            }
            connections.add(new Connection(id, result.getId(), inverted ? "True" : "False"));
            return result;
        }

        class VarInfo {
            final Variable originalVariable;
            final Object undelayedSource;
            final Object delayedSource;

            VarInfo(Variable originalVariable, Object undelayedSource, Object delayedSource) {
                this.originalVariable = originalVariable;
                this.undelayedSource = undelayedSource;
                this.delayedSource = delayedSource;
            }
        }

        private Block transform() throws UnresolvedTypeException, MissingAssignmentException,
                UndeclaredVariableException {
            // 1. "First cycle" block
            final OutputVariable firstCycleOutput = delay(
                    constantBool(false, 0),
                    constantBool(true, 1)
            );

            // 2. For each input variable, create its delayed version. Store both delayed and undelayed versions.
            final List<InputVariable> inputs = new ArrayList<>();
            int order = 0;
            for (Variable v : inputVariables.values()) {
                final InputVariable inputVariable = new InputVariable(newID(), v.getVarType(), v.name, order++);
                inputs.add(inputVariable);
                // create the delayed version
                final OutputVariable delayOutput = delayWithDummyDefault(createWire(inputVariable, 0));
                varInfo.put(v.name, new VarInfo(v, inputVariable, delayOutput));
            }

            // 3. For each internal variable, define its value as a choice between next and init values.
            // Create a delayed version. Store both delayed and undelayed versions.
            // Output all internal variables (no DEFINEs so far).
            final List<OutputVariable> outputs = new ArrayList<>();
            final Map<String, List<Choice>> internalVariableChoices = new HashMap<>();
            for (Variable v : internalVariables.values()) {
                final List<Choice> choiceList = new ArrayList<>();
                internalVariableChoices.put(v.name, choiceList);
                // create choice (concrete choices will be filled later)
                final OutputVariable choiceOutput = newOutputVariable(v.getVarType());
                createComponent(new ChoiceComponent(v.getChoiceType(), newID(), choiceList, choiceOutput));
                // create delay after choice
                final OutputVariable delayOutput = delayWithDummyDefault(createWire(choiceOutput, 0));
                // fill variable information
                varInfo.put(v.name, new VarInfo(v, choiceOutput, delayOutput));
                // connect choice output to the output of the block
                final OutputVariable wholeBlockOutput = new OutputVariable(newID(), v.getVarType(), v.name);
                outputs.add(wholeBlockOutput);
                connections.add(new Connection(choiceOutput.getId(), wholeBlockOutput.getId()));
            }

            // 4. Connect next() and init() expressions
            for (Variable v : internalVariables.values()) {
                final List<InputVariable> expressionsToAttach = new ArrayList<>();
                for (Assignment.Type type : Arrays.asList(Assignment.Type.INIT, Assignment.Type.NEXT)) {
                    final AssignmentInfo info = new AssignmentInfo(v.name, type);
                    final Assignment a = assignments.get(info);
                    if (a == null) {
                        throw new MissingAssignmentException("Missing assignment " + info);
                    }
                    expressionsToAttach.add(a.getRight().generate(this, expressionsToAttach.size()));
                }
                final Choice cInit = new Choice(createWire(firstCycleOutput, 0), expressionsToAttach.get(0));
                final Choice cNext = new Choice(constantBool(true, 1), expressionsToAttach.get(1));
                Arrays.asList(cInit, cNext).forEach(c -> internalVariableChoices.get(v.name).add(c));
            }

            return new Block(name, inputs, outputs, components, connections);
        }
    }

    public Block toFunctionBlockNetwork() throws UnresolvedTypeException, MissingAssignmentException,
            UndeclaredVariableException {
        // FIXME ensure that all types are inferred
        return (this.new FunctionBlockNetworkContext()).transform();
    }
}
