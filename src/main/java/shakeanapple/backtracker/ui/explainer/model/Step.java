package shakeanapple.backtracker.ui.explainer.model;

public class Step {
    private int number;
    private boolean isLoopStart;

    public Step(int number, boolean isLoopStart) {
        this.number = number;
        this.isLoopStart = isLoopStart;
    }

    public int getNumber() {
        return this.number;
    }

    public boolean getIsLoopStart() {
        return this.isLoopStart;
    }
}
