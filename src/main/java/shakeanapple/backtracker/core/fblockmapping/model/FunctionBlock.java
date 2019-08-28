package shakeanapple.backtracker.core.fblockmapping.model;

public class FunctionBlock {
    private final String name;
    private final FunctionBlockInfo info;
    private final int delay;

    public FunctionBlock(String name, FunctionBlockInfo info) {
        this.name = name;
        this.info = info;

        // TODO
        this.delay = 1;
    }

    public String getName() {
        return this.name;
    }

    public FunctionBlockInfo getInfo() {
        return this.info;
    }

    public int getDelay() {
        return this.delay;
    }

    public boolean isImmediate(){
        return this.delay == 0;
    }
}
