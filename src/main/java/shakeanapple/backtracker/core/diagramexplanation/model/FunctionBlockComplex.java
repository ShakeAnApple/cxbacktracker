package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.parser.fblockdiagram.Parser;

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
    public void evaluate(){
        this.internalDiagram.evaluate();
    }

    public static FunctionBlockComplex parse(String path, String blockDefsPath){
        try {
            Parser p = new Parser(path, blockDefsPath);
            return p.parse();
        }
        catch (Exception e){
            throw new RuntimeException("Can't load diagram");
        }
    }
}
