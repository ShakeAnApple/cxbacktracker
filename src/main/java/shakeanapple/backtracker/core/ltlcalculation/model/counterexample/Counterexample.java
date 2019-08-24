package shakeanapple.backtracker.core.ltlcalculation.model.counterexample;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Counterexample {

    private Map<Integer, State> path;

    public Map<Integer, State> getPath(){
        return this.path;
    }

    public Counterexample(List<State> path) {
        this.path = path.stream().collect(Collectors.toMap(State::getOrder, (s) -> s ));
    }

    public int length() {
        return this.path.keySet().size();
    }
}
