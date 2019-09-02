package shakeanapple.backtracker.core.counterexample;

import org.w3c.dom.css.Counter;
import shakeanapple.backtracker.parser.counterexample.Parser;

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

    public static Counterexample load(String path){
        try {
            Parser p = new Parser(path);
            return p.parse();
        }
        catch (Exception e){
            throw new RuntimeException("Can't load counterexample");
        }
    }
}
