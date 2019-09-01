package shakeanapple.backtracker.parser.fblockdiagram.model;

import java.util.ArrayList;
import java.util.List;

public class ParsingOutput extends ParsingModuleVariable {

    private List<ParsingConnection> connectedTo;

    public ParsingOutput(ParsingVariableInfo info) {
        super(info);
        this.connectedTo = new ArrayList<>();
    }

    public void connect(ParsingInput toVar, ParsingModule from, ParsingModule to, boolean isInverted){
        this.connectedTo.add(new ParsingConnection(from, this, to, toVar, isInverted));
    }

    public List<ParsingConnection> getIncommingConnections() {
        return this.connectedTo;
    }
}
