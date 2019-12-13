package shakeanapple.backtracker.parser.ltlformula.old.rule;

import shakeanapple.backtracker.parser.ltlformula.old.Parser;
import shakeanapple.backtracker.parser.ltlformula.old.State;
import shakeanapple.backtracker.parser.ltlformula.old.tree.BinOpNode;
import shakeanapple.backtracker.parser.ltlformula.old.tree.Node;

public class BinOpRule extends Rule{

    @Override
    public Node apply(State state, Parser parser) {
        String name = readBinOp(state);
        Node left = state.getRoot();
        String str = parser.extractParam(state);
        Node right = parser.parseExpr(str);
//        Node right = parser.parseParam(state);
        return new BinOpNode(left, right, name);
    }

    private String readBinOp(State state) {
        String name = state.getLastChecked();
        state.skip();
        state.readNext(name.length());
        return name;
    }
}
