package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.parser.fblockdiagram.Parser;

import java.io.IOException;

public class FunctionBlockComplex extends FunctionBlockBase {

    private Diagram internalDiagram;

    public FunctionBlockComplex(String name, String type, FBInterface fbInterface, Diagram internalDiagram){
        super(name, type, fbInterface);

        this.internalDiagram = internalDiagram;
    }

    public Diagram getInternalDiagram() {
        return this.internalDiagram;
    }

    @Override
    public void execute(){
        this.internalDiagram.execute();
    }

    public static FunctionBlockComplex parse(String path, String blockDefsPath){
        try {
            Parser p = new Parser(path, blockDefsPath);
            return p.parse();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
