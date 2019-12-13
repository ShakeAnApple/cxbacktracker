package shakeanapple.backtracker.parser.ltlformula.old.rule;

import shakeanapple.backtracker.parser.ltlformula.old.Parser;
import shakeanapple.backtracker.parser.ltlformula.old.State;
import shakeanapple.backtracker.parser.ltlformula.old.tree.Node;

public class BracketGroupRule extends Rule{

    @Override
    public Node apply(State state, Parser parser) {
        int opCount = 0;
        int clCount = 0;
        if (state.pickNext(1).equals("(")){
            opCount++;
            state.readNext(1);
        } else{
            throw new RuntimeException("not bracket group");
        }

        StringBuilder sb = new StringBuilder();
        String next = "";
        while(opCount != clCount){
            sb.append(next);
            next = state.readNext(1);
            if (next.equals("(")){
                opCount++;
            } else if (next.equals(")")){
                clCount++;
            }
        }
        return parser.parseExpr(sb.toString());
    }
}
