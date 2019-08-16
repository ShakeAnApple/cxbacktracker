package shakeanapple.backtracker.core.calculation;

import shakeanapple.backtracker.core.model.counterexample.Counterexample;
import shakeanapple.backtracker.core.model.counterexample.State;
import shakeanapple.backtracker.core.model.ltlformula.model.LtlFormula;

public class SomeName {

    public void applyCounterexample(LtlFormula formula, Counterexample counterexample){
        CounterexampleWalker walker = new CounterexampleWalker(counterexample, formula);

        State state = walker.getStateFalsified();
    }
}
