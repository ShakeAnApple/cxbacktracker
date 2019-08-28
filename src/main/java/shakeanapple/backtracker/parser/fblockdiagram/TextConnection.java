package shakeanapple.backtracker.parser.fblockdiagram;

public class TextConnection {
    private TextModule from;
    private TextVariable fromOut;
    private TextModule to;
    private TextVariable toIn;

    public TextConnection(TextModule from, TextVariable fromOut, TextModule to, TextVariable toIn) {
        this.from = from;
        this.fromOut = fromOut;
        this.to = to;
        this.toIn = toIn;
    }

    public TextModule from() {
        return this.from;
    }

    public TextVariable fromVar() {
        return this.fromOut;
    }

    public TextModule to() {
        return this.to;
    }

    public TextVariable toVar() {
        return this.toIn;
    }
}
