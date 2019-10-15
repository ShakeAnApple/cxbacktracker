package shakeanapple.backtracker.core.diagramexplanation.model.snapshot;

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

    private final FBInterfaceSnapshot diagramInterface;

    private DiagramSnapshot(List<String> inputs, List<String> outputs, List<FunctionBlockSnapshot> blocks, List<ConnectionSnapshot> connections) {
        this.blocks = blocks;
        this.connections = connections;

        this.diagramInterface = new FBInterfaceSnapshot(inputs, outputs);
    }

    public static DiagramSnapshot fromDiagram(FunctionBlockComplex diagram) {

        Map<String, FunctionBlockSnapshot> blockSnapshots = new HashMap<>();

        List<String> inputs = diagram.fbInterface().getInputs().values().stream().map(in -> in.input().getName()).collect(Collectors.toList());
        List<String> outputs = diagram.fbInterface().getOutputs().values().stream().map(out -> out.output().getName()).collect(Collectors.toList());

        for (DiagramElement fblock : diagram.getInternalDiagram().getFunctionBlocks()) {
            blockSnapshots.put(fblock.getName(), FunctionBlockSnapshot.fromFunctionBlock((FunctionBlockBase) fblock));
        }

        for (DiagramElement gate: diagram.fbInterface().getInputs().values()){
            blockSnapshots.put(gate.getName(), FunctionBlockSnapshot.fromGate((Gate)gate));
        }

        for (DiagramElement gate: diagram.fbInterface().getOutputs().values()){
            blockSnapshots.put(gate.getName(), FunctionBlockSnapshot.fromGate((Gate)gate));
        }

        List<ConnectionSnapshot> connections = new ArrayList<>();
        for(InputGate inGate: diagram.fbInterface().getInputs().values()){
            for (Object con: inGate.output().getOutgoingConnections()){
                Connection connection = (Connection) con;

                FunctionBlockSnapshot from = blockSnapshots.get(connection.from().getName());
                FunctionBlockSnapshot to = blockSnapshots.get(connection.to().getName());

                connections.add(new ConnectionSnapshot(from, connection.fromVar().getName(), to, connection.toVar().getName(), connection.isInverted(), connection.fromVar().getValue()));
            }
        }

        for (DiagramElement fblock : diagram.getInternalDiagram().getFunctionBlocks()) {
            for (OutputGate outputGate : ((FunctionBlockComplex)fblock).fbInterface().getOutputs().values()) {
                for (Object connectionObj : outputGate.output().getOutgoingConnections()) {

                    Connection connection = (Connection) connectionObj;

                    FunctionBlockSnapshot from = blockSnapshots.get(connection.from().getName());
                    FunctionBlockSnapshot to = blockSnapshots.get(connection.to().getName());

                    connections.add(new ConnectionSnapshot(from, connection.fromVar().getName(), to, connection.toVar().getName(), connection.isInverted(), connection.fromVar().getValue()));
                }
            }
        }

        return new DiagramSnapshot(inputs, outputs, new ArrayList<>(blockSnapshots.values()), connections);
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
