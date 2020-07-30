package shakeanapple.backtracker.core.diagramexplanation.model.snapshot;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockBase;
import shakeanapple.backtracker.core.diagramexplanation.model.Gate;
import shakeanapple.backtracker.core.diagramexplanation.model.InputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FunctionBlockSnapshot {
    private final String name;
    private final String type;

    private final FBInterfaceSnapshot fbInterface;

    public FunctionBlockSnapshot(String name, String type, List<String> inputs, List<String> outputs) {
        this.name = name;
        this.type = type;

        this.fbInterface = new FBInterfaceSnapshot(inputs, outputs);
    }

    public FunctionBlockSnapshot(String name, String type, Map<String, ValueHolder> inputs, Map<String, ValueHolder> outputs) {
        this.name = name;
        this.type = type;

        this.fbInterface = new FBInterfaceSnapshot(inputs, outputs);
    }

    public static FunctionBlockSnapshot fromFunctionBlock(FunctionBlockBase functionBlock) {
//        return new FunctionBlockSnapshot(functionBlock.getName(), functionBlock.getType(),
//                functionBlock.fbInterface().getInputs().values().stream().map(in -> in.input().getName()).collect(Collectors.toList()),
//                functionBlock.fbInterface().getOutputs().values().stream().map(out -> out.output().getName()).collect(Collectors.toList()));

        return new FunctionBlockSnapshot(functionBlock.getName(), functionBlock.getType(),
                functionBlock.fbInterface().getInputs().values().stream().collect(Collectors.toMap(InputGate::getFullName, InputGate::getValue)),
                functionBlock.fbInterface().getOutputs().values().stream().collect(Collectors.toMap(OutputGate::getFullName, OutputGate::getValue)));
    }

    public static FunctionBlockSnapshot fromGate(Gate gate) {
        return new FunctionBlockSnapshot(gate.getFullName(), gate.getType(), new ArrayList<>(){{add(gate.input().getName());}}, new ArrayList<>(){{add(gate.output().getName());}});
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public FBInterfaceSnapshot getFbInterface() {
        return this.fbInterface;
    }
}
