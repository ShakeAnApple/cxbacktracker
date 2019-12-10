package shakeanapple.backtracker.parser.ltlformula.rule;

import shakeanapple.backtracker.parser.ltlformula.Parser;
import shakeanapple.backtracker.parser.ltlformula.State;
import shakeanapple.backtracker.parser.ltlformula.tree.BinOpNode;
import shakeanapple.backtracker.parser.ltlformula.tree.Node;

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
