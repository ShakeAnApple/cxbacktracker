package shakeanapple.backtracker.core.diagramexplanation;

import shakeanapple.backtracker.ui.basiccomponentsconstructor.component.choice.AddChoiceDialogController;

import java.util.ArrayList;
import java.util.List;

public class Clocks {

    private int currentTime;

    private List<Runnable> nextTickActions = new ArrayList<>();
    private List<Runnable> permanentActions = new ArrayList<>();

    public Clocks(){
        this.currentTime = 0;
    }

    public int currentTime(){
        return this.currentTime;
    }

    public void tick(){
        this.currentTime ++;
        List<Runnable> actions = new ArrayList<>(this.nextTickActions);
        this.nextTickActions.clear();
        for (Runnable action : actions){
            action.run();
        }

        for (Runnable action : this.permanentActions){
            action.run();
        }
    }

    public void onTick(Runnable action){
        this.permanentActions.add(action);
    }

    public void onceOnNextTick(Runnable action) {
        this.nextTickActions.add(action);
    }

    public void reset() {
        this.currentTime = 0;
    }
}
