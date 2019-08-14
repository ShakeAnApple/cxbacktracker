package shakeanapple.backtracker.core.systemmodel;

import shakeanapple.backtracker.core.variable.Variable;
import shakeanapple.backtracker.core.variable.VariableInfo;

import java.util.List;

public interface Block {
    // description
    List<VariableInfo> getInputsInfo();
    List<VariableInfo> getOutputsInfo();

    // function
    List<Variable> getInputs();
    List<Variable> getOutputs();
    void calculate();
}
