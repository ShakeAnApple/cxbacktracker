package shakeanapple.backtracker.core.diagramexplanation.model.basiccomponents;


import java.util.HashMap;
import java.util.Map;

public class BasicBlocksIdGenerator {
    private static BasicBlocksIdGenerator instance;

    private Map<String, Integer> blocksLastIds = new HashMap<>();

    private BasicBlocksIdGenerator(){
    }

    private static BasicBlocksIdGenerator instance(){
        if (instance == null){
            instance = new BasicBlocksIdGenerator();
        }
        return instance;
    }

    public static long next(String name){
        int id;
        if (instance().blocksLastIds.containsKey(name)){
            id = instance().blocksLastIds.get(name) + 1;
            instance().blocksLastIds.replace(name, id);
        } else {
            id = 0;
            instance().blocksLastIds.put(name, id);
        }
        return id;
    }
}
