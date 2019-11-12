package shakeanapple.backtracker.parser.counterexample;

import shakeanapple.backtracker.common.variable.*;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.State;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final String path;
    private boolean loopIndicator = false;

    public Parser(String path) {
        this.path = path;
    }

    public Counterexample parse() {
        List<State> states = new ArrayList<>();

        BufferedReader reader;
        int loopStart = -1;

        try {
            reader = new BufferedReader(new FileReader(this.path));
            String line = reader.readLine();
            int cnt = -1;
            while (!line.trim().startsWith("->")){
                line = reader.readLine();
            }
            while (reader.ready()) {
                cnt++;
                State state = this.extractState(reader, cnt);
                if (this.loopIndicator){
                    loopStart = cnt + 1;
                    this.loopIndicator = false;
                }
                states.add(state);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Counterexample(states, loopStart);
    }

    private State extractState(BufferedReader reader, int order) throws IOException {
        List<Variable> variables = new ArrayList<>();
        String line = reader.readLine();
        while(line != null && !line.trim().startsWith("->")){
            line = line.trim();

            if (line.contains("Loop starts here")){
                this.loopIndicator = true;
                reader.readLine();
                break;
            } else if (line.startsWith("--")){
                reader.readLine();
                break;
            }

            Variable var = this.parseVariable(line);
            variables.add(var);
            line = reader.readLine();
        }

        return new State(variables, order);
    }

    private Variable parseVariable(String line) {
        String[] varParts = line.split(" = ");
        String varName = varParts[0];
        String varValStr = varParts[1];
        if (varValStr.toLowerCase().equals("true") || varValStr.toLowerCase().equals("false")){
            return new BooleanVariable(new BooleanValueHolder(varValStr.toLowerCase().equals("true")), varName);
        } else{
            // TODO only two var types are supported by now
            return new IntegerVariable(new IntegerValueHolder(Integer.parseInt(varValStr)), varName);
        }
    }
}
