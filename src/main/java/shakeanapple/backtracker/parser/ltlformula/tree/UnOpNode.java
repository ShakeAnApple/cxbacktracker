package shakeanapple.backtracker.parser.ltlformula.tree;

import shakeanapple.backtracker.core.ltl.formula.model.tree.*;

public class UnOpNode extends Node{
    private final Node child;
    private final String op;

    public UnOpNode(Node child, String op) {
        this.child = child;
        this.op = op;
    }

    @Override
    public FormulaNode translate() {
        switch(this.op){
            case"X": return new XNode(this.child.translate());
            case"F": return new FNode(this.child.translate());
            case"G": return new GNode(this.child.translate());
            case"!": return new NotNode(this.child.translate());
            default: throw new RuntimeException("Not implemented unary operator " + this.op);
        }
    }
}
