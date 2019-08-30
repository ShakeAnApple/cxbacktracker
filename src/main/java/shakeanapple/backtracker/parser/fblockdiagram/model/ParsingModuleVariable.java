package shakeanapple.backtracker.parser.fblockdiagram.model;

public class ParsingModuleVariable {
    private ParsingVariableInfo info;

    public ParsingModuleVariable(ParsingVariableInfo info) {
        this.info = info;
    }

    public ParsingVariableInfo getInfo(){
        return this.info;
    }
}
