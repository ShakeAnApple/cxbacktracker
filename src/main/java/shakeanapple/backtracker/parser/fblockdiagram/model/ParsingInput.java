package shakeanapple.backtracker.parser.fblockdiagram.model;

public class ParsingInput extends ParsingModuleVariable {

    private ParsingConnection connectedFrom;

    public ParsingInput(ParsingVariableInfo info) {
        super(info);
    }

    public void connect(ParsingOutput fromVar, ParsingModule from, ParsingModule to, boolean isInverted){
        this.connectedFrom = new ParsingConnection(from, fromVar, to, this, isInverted);
    }
}
