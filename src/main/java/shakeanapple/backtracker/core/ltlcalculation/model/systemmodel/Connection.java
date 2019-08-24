package shakeanapple.backtracker.core.ltlcalculation.model.systemmodel;

import shakeanapple.backtracker.common.variable.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Connection {
    private final Block _from;
    private final Block _to;
    private final List<Variable> _variables;

    public Connection(Block from, Block to, List<Variable> variables) {
        checkCompatibility(from, to, variables);

        _from = from;
        _to = to;
        _variables = variables;
    }

    private void checkCompatibility(Block from, Block to, List<Variable> variables) {
        List<String> varNames = variables.stream().map(v -> v.getName()).collect(Collectors.toList());
//        List<String> fromVarNames = from.getOutputsInfo().stream().map(vi -> vi).collect(Collectors.toList());
//        List<String> toVarNames = from.getInputsInfo().stream().map(vi -> vi.getName()).collect(Collectors.toList());
        List<String> errors = new ArrayList<>();
        for (String name: varNames) {
//            if (!fromVarNames.contains(name) || !toVarNames.contains(name)){
//                errors.add(name);
//            }
        }
        if (errors.size() != 0){
            throw new RuntimeException("Connection can't be established on variables: " + Arrays.toString(errors.toArray()));
        }
    }
}
