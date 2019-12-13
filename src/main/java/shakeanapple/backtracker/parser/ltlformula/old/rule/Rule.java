package shakeanapple.backtracker.parser.ltlformula.old.rule;

import shakeanapple.backtracker.parser.ltlformula.old.State;
import shakeanapple.backtracker.parser.ltlformula.old.Parser;
import shakeanapple.backtracker.parser.ltlformula.old.tree.Node;

public abstract class Rule {
    private String name;

    public abstract Node apply(State state, Parser parser);
}

