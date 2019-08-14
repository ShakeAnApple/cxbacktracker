package shakeanapple.backtracker.core.ltlformula.parser.rule;

import shakeanapple.backtracker.core.ltlformula.parser.Parser;
import shakeanapple.backtracker.core.ltlformula.parser.State;
import shakeanapple.backtracker.core.ltlformula.parser.tree.Node;

public abstract class Rule {
    private String name;

    public abstract Node apply(State state, Parser parser);
}

