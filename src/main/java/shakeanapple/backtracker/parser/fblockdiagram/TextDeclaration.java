package shakeanapple.backtracker.parser.fblockdiagram;

public class TextDeclaration {
    private String name;
    private String value;

    public TextDeclaration(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
