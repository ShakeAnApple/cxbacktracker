package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.common.variable.Variable;
import shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents.FunctionBlockBasic;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.List;

public class Diagram {
    private List<DiagramElement> functionBlocks;

    private List<InputVariable> inputs;
    private List<OutputVariable> outputs;

    public Diagram(List<DiagramElement> functionBlocks, List<InputVariable> inputs, List<OutputVariable> outputs) {
        this.functionBlocks = functionBlocks;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public List<DiagramElement> getFunctionBlocks() {
        return this.functionBlocks;
    }

    public List<InputVariable> getInputs() {
        return this.inputs;
    }

    public List<OutputVariable> getOutputs() {
        return this.outputs;
    }

    public void evaluate() {

    }
}
