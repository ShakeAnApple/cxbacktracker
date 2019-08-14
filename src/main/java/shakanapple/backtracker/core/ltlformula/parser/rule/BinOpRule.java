package shakeanapple.backtracker.core.ltlformula.parser.rule;

import shakeanapple.backtracker.core.ltlformula.parser.Parser;
import shakeanapple.backtracker.core.ltlformula.parser.State;
import shakeanapple.backtracker.core.ltlformula.parser.tree.BinOpNode;
import shakeanapple.backtracker.core.ltlformula.parser.tree.Node;

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
        state.readNext(name.length());
        return name;
    }
}
