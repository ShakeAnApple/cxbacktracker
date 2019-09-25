package shakeanapple.backtracker.core.diagramexplanation.model.snapshot;

import shakeanapple.backtracker.core.diagramexplanation.model.Connection;
import shakeanapple.backtracker.core.diagramexplanation.model.Diagram;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlock;
import shakeanapple.backtracker.core.diagramexplanation.model.variable.OutputVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagramSnapshot {
    private final FunctionBlockSnapshot root;

    private final List<FunctionBlockSnapshot> blocks;
    private final List<ConnectionSnapshot> connections;

    private DiagramSnapshot(FunctionBlockSnapshot root, List<FunctionBlockSnapshot> blocks, List<ConnectionSnapshot> connections) {
        this.root = root;
        this.blocks = blocks;
        this.connections = connections;
    }

    public static DiagramSnapshot fromDiagram(Diagram diagram){

        FunctionBlockSnapshot root = FunctionBlockSnapshot.fromFunctionBlock(diagram.getRoot());

        Map<String, FunctionBlockSnapshot> blockSnapshots = new HashMap<>();

        for (FunctionBlock fblock : diagram.getFblocks()){
            blockSnapshots.put(fblock.getName(), FunctionBlockSnapshot.fromFunctionBlock(fblock));
        }

        List<ConnectionSnapshot> connections = new ArrayList<>();
        for (FunctionBlock fblock : diagram.getFblocks()) {
            for (OutputVariable output: fblock.getOutputs().values()) {
                for (Object connectionObj: output.getOutcomingConnections()){

                    Connection connection = (Connection) connectionObj;

                    FunctionBlockSnapshot from = blockSnapshots.get(connection.from().getName());
                    FunctionBlockSnapshot to = blockSnapshots.get(connection.to().getName());

                    connections.add(new ConnectionSnapshot(from, connection.fromVar().getName(), to, connection.toVar().getName(), connection.isInverted(), connection.fromVar().getValue()));
                }
            }
        }

        return new DiagramSnapshot(root, new ArrayList<>(blockSnapshots.values()), connections);
    }

    public FunctionBlockSnapshot getRoot() {
        return this.root;
    }

    public List<FunctionBlockSnapshot> getBlocks() {
        return this.blocks;
    }

    public List<ConnectionSnapshot> getConnections() {
        return this.connections;
    }
}
