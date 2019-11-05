package shakeanapple.backtracker.core.diagramexplanation.model;

import shakeanapple.backtracker.core.diagramexplanation.Cause;
import shakeanapple.backtracker.parser.fblockdiagram.Parser;

import java.io.IOException;
import java.util.List;

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
    public void executeImpl(){
        this.internalDiagram.execute();
    }

    @Override
    protected List<Cause> explainImpl(OutputGate output, Integer timestamp) {
        return null;
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
