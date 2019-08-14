package shakeanapple.backtracker;

import shakeanapple.backtracker.core.ltlformula.model.LtlFormula;
import shakeanapple.backtracker.core.systemmodel.Block;

public class Main {

    public static void main(String[] args) {
        LtlFormula.parse("G(((!(alarm) & !(criteria)) & X (criteria & !(ack_button))) -> X (alarm))");
    }
}
