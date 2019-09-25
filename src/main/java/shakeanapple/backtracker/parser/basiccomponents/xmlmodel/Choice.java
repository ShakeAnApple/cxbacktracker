package shakeanapple.backtracker.parser.basiccomponents.xmlmodel;

import com.ibm.icu.impl.TextTrieMap;

public class Choice {
    private InputVariable input;
    private OutputVariable output;

    public Choice() {
    }

    public Choice(InputVariable input, OutputVariable output) {
        this.input = input;
        this.output = output;
    }

    public InputVariable getInput() {
        return this.input;
    }

    public void setInput(InputVariable input) {
        this.input = input;
    }

    public OutputVariable getOutput() {
        return this.output;
    }

    public void setOutput(OutputVariable output) {
        this.output = output;
    }
}
