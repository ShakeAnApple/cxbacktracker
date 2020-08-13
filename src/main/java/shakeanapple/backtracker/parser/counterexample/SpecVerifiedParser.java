package shakeanapple.backtracker.parser.counterexample;

import shakeanapple.backtracker.common.variable.*;
import shakeanapple.backtracker.core.counterexample.Counterexample;
import shakeanapple.backtracker.core.counterexample.SpecVerified;
import shakeanapple.backtracker.core.counterexample.State;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecVerifiedParser {
    private String path;
    private boolean loopIndicator;
    private boolean nextSpecIndicator;
    private String nextSpecLine;

    public SpecVerifiedParser(String path) {
        this.path = path;
    }

    public List<SpecVerified> parse(){
            BufferedReader reader;
            List<SpecVerified> specs = new ArrayList<>();

            try {
                reader = new BufferedReader(new FileReader(this.path));
                String line = reader.readLine();
                while (!line.contains("specification")){
                    line = reader.readLine();
                }
                this.nextSpecLine = line;
                while (reader.ready()) {
                    if (this.nextSpecLine != null) {
                        specs.add(this.parseSpec(this.nextSpecLine, reader));
                        this.nextSpecIndicator = false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return specs;
    }

    private SpecVerified parseSpec(String specLine, BufferedReader reader) throws IOException {
        String formulaStr = specLine
                .replace("-- specification", "")
                .replace("is true", "")
                .replace("is false", "").trim();
        if (specLine.contains("is true")){
            String line = reader.readLine();
            while (!line.contains("specification")){
                line = reader.readLine();
            }
            this.nextSpecLine = line;
            return new SpecVerified(formulaStr, null);
        }
        return new SpecVerified(formulaStr, this.parseCounterexample(reader));
    }

    private Counterexample parseCounterexample(BufferedReader reader) {
        List<State> states = new ArrayList<>();

        int loopStart = -1;
        this.loopIndicator = false;

        try {
            String line = reader.readLine();
            int cnt = -1;
            while (!line.trim().startsWith("->")){
                line = reader.readLine();
            }
            State prevState = null;
            while (reader.ready()) {
                cnt++;
                State state = this.extractState(reader, cnt, prevState);
                prevState = state;
                if (this.loopIndicator){
                    loopStart = cnt + 1;
                    this.loopIndicator = false;
                    reader.readLine();
                }
                states.add(state);
                if (this.nextSpecIndicator){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (loopStart == -1){
            loopStart = states.size() - 1;
        }
        return new Counterexample(states, loopStart);
    }


    private State extractState(BufferedReader reader, int order, State prevState) throws IOException {
        Map<String, Variable> variables = new HashMap<>();
        String line = reader.readLine();
        while(line != null && !line.trim().startsWith("->")){
            line = line.trim();

            if (line.contains("Loop starts here")){
                this.loopIndicator = true;
                break;
            } else if (line.startsWith("-- specification")){
                this.nextSpecLine = line;
                this.nextSpecIndicator = true;
                break;
            }

            Variable var = this.parseVariable(line);
            variables.put(var.getName(), var);
            line = reader.readLine();
        }
        if (prevState != null){
            for (String varName : prevState.getVarsByNames().keySet()){
                if (!variables.containsKey(varName)){
                    Variable prevVar = prevState.getVarByName(varName);
                    Variable repeatedVar = prevVar.getValue() instanceof BooleanValueHolder ?
                            new BooleanVariable(new BooleanValueHolder(((BooleanValueHolder) prevVar.getValue()).getValue()), prevVar.getName()) :
                            new IntegerVariable(new IntegerValueHolder(((IntegerValueHolder) prevVar.getValue()).getValue()), prevVar.getName());
                    variables.put(repeatedVar.getName(), repeatedVar);
                }
            }
        }

        return new State(new ArrayList<>(variables.values()), order);
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
