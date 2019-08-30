package shakeanapple.backtracker.core.fblockmapping.model;

import shakeanapple.backtracker.parser.fblockdiagram.Parser;

import java.util.List;

public class FunctionBlockDiagram {
    private final List<FunctionBlock> fblocks;
    private List<ConnectionTemplate> connectionTemplates;

    public FunctionBlockDiagram(List<FunctionBlock> fblocks, List<ConnectionTemplate> connectionTemplates) {
        this.fblocks = fblocks;
        this.connectionTemplates = connectionTemplates;
    }


    public static FunctionBlockDiagram load(String path){
        try {
            Parser p = new Parser(path);
            return p.parse();
        }
        catch (Exception e){
            throw new RuntimeException("Can't load diagram");
        }
    }
}
