package shakeanapple.backtracker.core.ltlformula.parser.tree;

import shakeanapple.backtracker.core.ltlformula.model.tree.*;

public class BinOpNode extends Node{
    private final Node left;
    private final Node right;

    private final String op;

    public BinOpNode(Node left, Node right, String op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public FormulaNode translate() {
        switch (this.op){
            case"U": return new UNode(this.left.translate(), this.right.translate());
            case"R": return new RNode(this.left.translate(), this.right.translate());
            case"W": return new WNode(this.left.translate(), this.right.translate());
            case"M": return new MNode(this.left.translate(), this.right.translate());
            case"&": return new AndNode(this.left.translate(), this.right.translate());
            case"|": return new OrNode(this.left.translate(), this.right.translate());
            case"->": return new ImplNode(this.left.translate(), this.right.translate());
            case"=": return new EqNode(this.left.translate(), this.right.translate());
            case"<": return new LessNode(this.left.translate(), this.right.translate());
            case">": return new GreaterNode(this.left.translate(), this.right.translate());
            case"+": return new PlusNode(this.left.translate(), this.right.translate());
            case"-": return new MinusNode(this.left.translate(), this.right.translate());
            case"*": return new MulNode(this.left.translate(), this.right.translate());
            case"/": return new DivNode(this.left.translate(), this.right.translate());
            case"!=": return new NotEqNode(this.left.translate(), this.right.translate());
            case"<>": return new NotEqNode(this.left.translate(), this.right.translate());
            case"<=": return new LessEqNode(this.left.translate(), this.right.translate());
            case">=": return new GreaterEqNode(this.left.translate(), this.right.translate());
            default: throw new RuntimeException("Not implememnted binary operator " + this.op);
        }
    }
}
