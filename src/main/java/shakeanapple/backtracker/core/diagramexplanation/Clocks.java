package shakeanapple.backtracker.core.diagramexplanation;

import java.util.ArrayList;
import java.util.List;

public class Clocks {
    private static Clocks instance;

    private int currentTime;

    List<Runnable> nextTickActions = new ArrayList<>();

    private Clocks(){
        this.currentTime = 0;
    }

    public static Clocks instance(){
        if (instance == null){
            instance = new Clocks();
        }
        return instance;
    }

    public int currentTime(){
        return this.currentTime;
    }

    public void tick(){
        this.currentTime ++;
        for (Runnable action : this.nextTickActions){
            action.run();
        }
        this.nextTickActions.clear();
    }

    public void onNextTick(Runnable action) {
        this.nextTickActions.add(action);
    }
}
