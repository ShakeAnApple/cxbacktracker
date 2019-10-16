package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.InputVariable;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

public class OutputGate extends Gate {
    private InputVariable input;

    private OutputVariable output;

    public OutputGate(OutputVariable output) {
        super(output.getName(), "OUTPUT_GATE");

        this.output = output;
        this.input = InputVariable.createSharedWithOutput(this.output);
    }

    public InputVariable input() {
        return this.input;
    }

    public OutputVariable output() {
        return this.output;
    }

    public void assignValue(ValueHolder value){
        this.output.setValue(value);
        for (Connection connection: super.getOutgoingConnections()) {
            if (connection.isInverted()){
                value.invert();
            }
            connection.toGate().input().setValue(value);
        }
    }
}
