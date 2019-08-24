package shakeanapple.backtracker.parser.fblockdiagram;

import java.util.List;

public class TextModule {
    private final String name;
    private List<TextVariable> inputs;
    private List<TextVariable> outputs;
    private List<TextVariable> internals;

    public TextModule(String name, List<TextVariable> inputs, List<TextVariable> outputs, List<TextVariable> internals) {
        this.name = name;
        this.inputs = inputs;
        this.outputs = outputs;
        this.internals = internals;
    }

    public String getName() {
        return this.getName();
    }
}
