package shakeanapple.backtracker.parser.fblockdiagramold.model;

public class ParsingOutputConstant<VType> extends ParsingOutput {

    private final VType value;

    public ParsingOutputConstant(ParsingVariableInfo info, VType value) {
        super(info);
        this.value = value;
    }

    public VType getValue() {
        return this.value;
    }
}
