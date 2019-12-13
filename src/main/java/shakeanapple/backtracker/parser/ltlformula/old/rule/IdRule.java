package shakeanapple.backtracker.parser.ltlformula.old.rule;

import shakeanapple.backtracker.parser.ltlformula.old.Parser;
import shakeanapple.backtracker.parser.ltlformula.old.State;
import shakeanapple.backtracker.parser.ltlformula.old.tree.IdNode;
import shakeanapple.backtracker.parser.ltlformula.old.tree.Node;

public class IdRule extends Rule{

    @Override
    public Node apply(State state, Parser parser) {
        String name = readIdentifier(state);
        return new IdNode(name);
    }

    private String readIdentifier(State state) {
        String forbiddenChars = "&|-<>=+*/!() ";
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
