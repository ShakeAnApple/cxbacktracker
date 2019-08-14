package shakeanapple.backtracker.core.ltlformula.parser.rule;

import shakeanapple.backtracker.core.ltlformula.parser.Parser;
import shakeanapple.backtracker.core.ltlformula.parser.State;
import shakeanapple.backtracker.core.ltlformula.parser.tree.Node;
import shakeanapple.backtracker.core.ltlformula.parser.tree.UnOpNode;

public class UnOpRule extends Rule{

    @Override
    public Node apply(State state, Parser parser) {
        String name = readUnOp(state);
        String str = parser.extractParam(state);
        Node child = parser.parseExpr(str);
        return new UnOpNode(child, name);
    }

    private String readUnOp(State state) {
        String name = state.readNext(1);
        return name;
    }
}
