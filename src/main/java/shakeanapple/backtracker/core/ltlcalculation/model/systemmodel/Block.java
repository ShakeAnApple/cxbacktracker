package shakeanapple.backtracker.core.ltlcalculation.model.systemmodel;

import shakeanapple.backtracker.common.variable.Variable;

import java.util.List;

public interface Block {
    // description
//    List<VariableInfo> getInputsInfo();
//    List<VariableInfo> getOutputsInfo();

    // function
    List<Variable> getInputs();
    List<Variable> getOutputs();
    void calculate();
}
