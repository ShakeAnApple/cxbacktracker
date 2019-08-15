package shakeanapple.backtracker.parser.ltlformula.rule;

import shakeanapple.backtracker.parser.ltlformula.Parser;
import shakeanapple.backtracker.parser.ltlformula.State;
import shakeanapple.backtracker.parser.ltlformula.tree.IdNode;
import shakeanapple.backtracker.parser.ltlformula.tree.Node;

public class IdRule extends Rule{

    @Override
    public Node apply(State state, Parser parser) {
        String name = readIdentifier(state);
        return new IdNode(name);
    }

    private String readIdentifier(State state) {
        String forbiddenChars = "URWM&|-<>=+*/XFG!()";
        StringBuilder name = new StringBuilder();
        String next = state.pickNext(1);
        while (!forbiddenChars.contains(next) && !state.isEol()){
            next = state.readNext(1);
            name.append(next);
            next = state.pickNext(1);
        }
        return name.toString();
    }
}
