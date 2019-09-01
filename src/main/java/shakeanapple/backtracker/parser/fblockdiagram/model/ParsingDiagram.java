package shakeanapple.backtracker.parser.fblockdiagram.model;

import shakeanapple.backtracker.core.fblockmapping.model.Connection;
import shakeanapple.backtracker.core.fblockmapping.model.Diagram;
import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlock;
import shakeanapple.backtracker.core.fblockmapping.model.variable.InputVariable;
import shakeanapple.backtracker.core.fblockmapping.model.variable.OutputVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParsingDiagram {
    ParsingModule root;
    Map<String, ParsingModule> modules;

    public ParsingDiagram(ParsingModule root, Map<String, ParsingModule> modules) {
        this.root = root;
        this.modules = modules;
    }

    public Diagram translate() {
        Map<String, FunctionBlock> blocks = new HashMap<>();

        FunctionBlock root = this.root.translate();

        for (ParsingModule module : modules.values()) {
            blocks.put(module.getName(), module.translate());
        }

        for (ParsingModule module : modules.values()) {
            for (ParsingOutput output: module.getOutputsMap().values()) {
                for (ParsingConnection connection: output.getIncommingConnections()){

                    FunctionBlock from = blocks.get(connection.from().getName());
                    OutputVariable fromVar = from.getOutputs().get(connection.fromVar().getInfo().getName());
                    FunctionBlock to = blocks.get(connection.to().getName());
                    InputVariable toVar = to.getInputs().get(connection.toVar().getInfo().getName());

                    fromVar.connect(toVar, from, to, connection.isInverted());
                    toVar.connect(fromVar, from, to, connection.isInverted());
                }
            }
        }

        return new Diagram((List<FunctionBlock>) blocks.values(), root);
    }
}
