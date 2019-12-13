package shakeanapple.backtracker.parser.ltlformula.old.rule;

import shakeanapple.backtracker.parser.ltlformula.old.State;
import shakeanapple.backtracker.parser.ltlformula.old.Parser;
import shakeanapple.backtracker.parser.ltlformula.old.tree.Node;
import shakeanapple.backtracker.parser.ltlformula.old.tree.UnOpNode;

public class UnOpRule extends Rule{

    @Override
    public Node apply(State state, Parser parser) {
        String name = readUnOp(state);
        String str = parser.extractParam(state);
        Node child = parser.parseExpr(str);
        return new UnOpNode(child, name);
    }

    private String readUnOp(State state) {
        state.skip();
        String name = state.readNext(1);
        return name;
    }
}
