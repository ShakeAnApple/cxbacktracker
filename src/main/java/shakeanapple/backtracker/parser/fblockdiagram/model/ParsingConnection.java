package shakeanapple.backtracker.parser.fblockdiagram.model;

public class ParsingConnection {
    private ParsingModule from;
    private ParsingModuleVariable fromVar;
    
    private ParsingModule to;
    private ParsingModuleVariable toVar;

    private boolean isInverted;

    public ParsingConnection(ParsingModule from, ParsingModuleVariable fromVar, ParsingModule to, ParsingModuleVariable toVar, boolean isInverted) {
        this.from = from;
        this.fromVar = fromVar;
        this.to = to;
        this.toVar = toVar;

        this.isInverted = isInverted;
    }

    public ParsingModule from() {
        return from;
    }

    public ParsingModuleVariable fromVar() {
        return fromVar;
    }

    public ParsingModule to() {
        return to;
    }

    public ParsingModuleVariable toVar() {
        return toVar;
    }

    public boolean isInverted() {
        return this.isInverted;
    }
}
