package shakeanapple.backtracker.parser.ltlformula.recent.tree;

import shakeanapple.backtracker.core.ltl.formula.model.tree.FormulaNode;
import shakeanapple.backtracker.core.ltl.formula.model.tree.IntNode;
import shakeanapple.backtracker.core.ltl.formula.model.tree.VarNode;

public class IdNode extends Node {
    private final String value;

    public IdNode(String value) {
        this.value = value;
    }

    @Override
    public FormulaNode translate() {
        try {
            int intVal = Integer.parseInt(this.value);
            return new IntNode(intVal);
        } catch (NumberFormatException e) {
            return new VarNode(this.value);
        }
    }
}
