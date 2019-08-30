package shakeanapple.backtracker.core.fblockmapping.model;

public class ConnectionTemplate {
    private final FunctionBlock from;
    private final FunctionBlockVariableInfo fromVar;

    private final FunctionBlock to;
    private final FunctionBlockVariableInfo toVar;


    public ConnectionTemplate(FunctionBlock from, FunctionBlockVariableInfo fromVar, FunctionBlock to, FunctionBlockVariableInfo toVar) {
        this.from = from;
        this.fromVar = fromVar;
        this.to = to;
        this.toVar = toVar;
    }

    public FunctionBlock from() {
        return this.from;
    }

    public FunctionBlockVariableInfo fromVar() {
        return this.fromVar;
    }

    public FunctionBlock to() {
        return this.to;
    }

    public FunctionBlockVariableInfo toVar() {
        return this.toVar;
    }
}
