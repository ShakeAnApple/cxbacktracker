package shakeanapple.backtracker.parser.fblockdiagram.model;

import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlockDiagram;

import java.util.Map;

public class ParsingDiagram {
    ParsingModule root;
    Map<String, ParsingModule> modules;

    public ParsingDiagram(ParsingModule root, Map<String, ParsingModule> modules) {
        this.root = root;
        this.modules = modules;
    }

    public FunctionBlockDiagram translate() {
        return null;
    }
}
