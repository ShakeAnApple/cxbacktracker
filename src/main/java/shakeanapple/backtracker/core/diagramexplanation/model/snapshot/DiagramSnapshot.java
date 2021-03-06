package shakeanapple.backtracker.core.diagramexplanation.model.snapshot;

import shakeanapple.backtracker.common.variable.ValueHolder;
import shakeanapple.backtracker.core.diagramexplanation.model.InputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.OutputGate;
import shakeanapple.backtracker.core.diagramexplanation.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiagramSnapshot {

    private final List<FunctionBlockSnapshot> blocks;
    private final List<ConnectionSnapshot> connections;

    private final String name;

    private final FBInterfaceSnapshot diagramInterface;

//    private DiagramSnapshot(List<String> inputs, List<String> outputs, List<FunctionBlockSnapshot> blocks, List<ConnectionSnapshot> connections) {
//        this.blocks = blocks;
//        this.connections = connections;
//
//        this.diagramInterface = new FBInterfaceSnapshot(inputs, outputs);
//    }

    private DiagramSnapshot(String name, Map<String, ValueHolder> inputs, Map<String, ValueHolder> outputs, List<FunctionBlockSnapshot> blocks, List<ConnectionSnapshot> connections) {
        this.blocks = blocks;
        this.connections = connections;

        this.diagramInterface = new FBInterfaceSnapshot(inputs, outputs);

        this.name = name;
    }

    public static DiagramSnapshot fromDiagram(FunctionBlockComplex diagram) {

        Map<String, FunctionBlockSnapshot> blockSnapshots = new HashMap<>();

        Map<String, ValueHolder> inputs = diagram.fbInterface().getInputs().values().stream().collect(Collectors.toMap(InputGate::getFullName, InputGate::getValue));
        Map<String, ValueHolder> outputs = diagram.fbInterface().getOutputs().values().stream().collect(Collectors.toMap(OutputGate::getFullName, OutputGate::getValue));

        for (DiagramElement fblock : diagram.getInternalDiagram().getFunctionBlocks()) {
            blockSnapshots.put(fblock.getName(), FunctionBlockSnapshot.fromFunctionBlock((FunctionBlockBase) fblock));
        }

        for (Gate gate: diagram.fbInterface().getInputs().values()){
            blockSnapshots.put(gate.getFullName(), FunctionBlockSnapshot.fromGate((Gate)gate));
        }

        for (Gate gate: diagram.fbInterface().getOutputs().values()){
            blockSnapshots.put(gate.getFullName(), FunctionBlockSnapshot.fromGate((Gate)gate));
        }

        List<ConnectionSnapshot> connections = new ArrayList<>();
        for(InputGate inGate: diagram.fbInterface().getInputs().values()){
            for (Connection connection: inGate.getOutgoingConnections()){

                FunctionBlockSnapshot from = blockSnapshots.get(connection.from().getName());
                FunctionBlockSnapshot to = blockSnapshots.get(connection.to().getName());

                connections.add(new ConnectionSnapshot(from, connection.fromGate().getFullName(), to, connection.toGate().getFullName(), connection.isInverted(), connection.fromGate().getValue()));
            }
        }

        for (DiagramElement fblock : diagram.getInternalDiagram().getFunctionBlocks()) {
            for (OutputGate outputGate : ((FunctionBlockBase)fblock).fbInterface().getOutputs().values()) {
                for (Connection connection : outputGate.getOutgoingConnections()) {

                    FunctionBlockSnapshot from = blockSnapshots.get(connection.from().getName());
                    FunctionBlockSnapshot to = blockSnapshots.get(connection.to().getName());

                    if (connection.fromGate() != null && connection.toGate() != null) {
                        connections.add(new ConnectionSnapshot(from, connection.fromGate().getFullName(), to, connection.toGate().getFullName(), connection.isInverted(), connection.fromGate().getValue()));
                    }
                }
            }
        }

        return new DiagramSnapshot(diagram.getName(), inputs, outputs, new ArrayList<>(blockSnapshots.values()), connections);
    }

    public String getName() {
        return this.name;
    }

    public List<FunctionBlockSnapshot> getBlocks() {
        return this.blocks;
    }

    public List<ConnectionSnapshot> getConnections() {
        return this.connections;
    }

    public FBInterfaceSnapshot getDiagramInterface() {
        return this.diagramInterface;
    }
}
