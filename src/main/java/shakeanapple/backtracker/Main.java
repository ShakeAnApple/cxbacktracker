package shakeanapple.backtracker;

import shakeanapple.backtracker.core.model.ltlformula.model.LtlFormula;

public class Main {

    public static void main(String[] args) {
        LtlFormula.parse("G(((!(alarm) & !(criteria)) & X (criteria & !(ack_button))) -> X (alarm))");
    }
}
