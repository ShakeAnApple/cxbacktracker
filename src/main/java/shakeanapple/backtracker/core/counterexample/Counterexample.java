package shakeanapple.backtracker.core.counterexample;

import shakeanapple.backtracker.parser.counterexample.CounterexampleParser;
import shakeanapple.backtracker.parser.counterexample.SpecVerifiedParser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Counterexample {

    private Map<Integer, State> path;

    private int loopStart = -1;

    public Map<Integer, State> getPath(){
        return this.path;
    }

    public Counterexample(List<State> path, int loopStart) {
        this.path = path.stream().collect(Collectors.toMap(State::getOrder, (s) -> s ));
        this.loopStart = loopStart;
    }

    public int length() {
        return this.path.keySet().size();
    }

    public int getLoopStart(){
        return loopStart;
    }

    public static Counterexample load(String path){
        try {
            CounterexampleParser p = new CounterexampleParser(path);
            return p.parse();
        }
        catch (Exception e){
            throw new RuntimeException("Can't load counterexample");
        }
    }

    public static List<SpecVerified> loadFromNusmvOutput(String path){
        try {
            SpecVerifiedParser p = new SpecVerifiedParser(path);
            return p.parse();
        }
        catch (Exception e){
            throw new RuntimeException("Can't load failed formulas");
        }
    }
}
