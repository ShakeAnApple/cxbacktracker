package shakeanapple.backtracker.parser.fblockdiagram;

import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlock;
import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlockInfo;

public class TextModule {
    private String name;
    private TextModuleDescription description;

    public TextModule(String name, TextModuleDescription description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public TextModuleDescription getDescription() {
        return this.description;
    }

    public FunctionBlock translate() {
        FunctionBlockInfo info = this.description.translate();
        return new FunctionBlock(this.getName(), info);
    }
}
