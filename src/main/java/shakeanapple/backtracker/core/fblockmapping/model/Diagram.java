package shakeanapple.backtracker.core.fblockmapping.model;

import shakeanapple.backtracker.parser.fblockdiagram.Parser;

import java.util.List;

public class Diagram {
    private final List<FunctionBlock> fblocks;

    private final FunctionBlock root;

    public Diagram(List<FunctionBlock> fblocks, FunctionBlock root) {
        this.fblocks = fblocks;
        this.root = root;
    }

    public static Diagram load(String path){
        try {
            Parser p = new Parser(path);
            return p.parse();
        }
        catch (Exception e){
            throw new RuntimeException("Can't load diagram");
        }
    }
}
