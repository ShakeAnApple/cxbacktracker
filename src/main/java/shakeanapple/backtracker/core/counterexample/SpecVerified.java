package shakeanapple.backtracker.core.counterexample;

import shakeanapple.backtracker.core.counterexample.Counterexample;

public class SpecVerified {
    private String formulaStr;
    private Counterexample cx;

    public SpecVerified(String formulaStr, Counterexample cx) {
        this.formulaStr = formulaStr;
        this.cx = cx;
    }

    public String getFormulaStr() {
        return this.formulaStr;
    }

    public Counterexample getCx() {
        return this.cx;
    }

    @Override
    public String toString() {
        return this.formulaStr + (this.cx == null ? " is true" : " is false");
    }
}
