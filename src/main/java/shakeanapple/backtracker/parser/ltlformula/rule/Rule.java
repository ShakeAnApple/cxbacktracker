package shakeanapple.backtracker.parser.ltlformula.rule;

import shakeanapple.backtracker.parser.ltlformula.State;
import shakeanapple.backtracker.parser.ltlformula.Parser;
import shakeanapple.backtracker.parser.ltlformula.tree.Node;

public abstract class Rule {
    private String name;

    public abstract Node apply(State state, Parser parser);
}

