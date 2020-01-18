package shakeanapple.backtracker.ui.infrasructure.control.diagram;

public class IdGenerator {
    private long cur=-1;
    private static IdGenerator instance;

    private IdGenerator(){
    }

    private static IdGenerator instance(){
        if (instance == null){
            instance = new IdGenerator();
        }
        return instance;
    }

    public static long next(){
        return ++instance().cur;
    }
}
