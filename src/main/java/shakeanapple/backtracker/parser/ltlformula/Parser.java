package shakeanapple.backtracker.parser.ltlformula;

import shakeanapple.backtracker.parser.ltlformula.rule.Rule;
import shakeanapple.backtracker.parser.ltlformula.tree.Node;

public class Parser {

    private final Grammar grammar;

    public Parser(Grammar grammar) {
        this.grammar = grammar;
    }

    public Node parseExpr(String str) {
        return this.parseParam(new State(str));
    }

    public Node parseId(String str){
        return this.parseParam(new State(str));
    }

    public Node parseParam(State state) {

        Node left = null;
        while (!state.isEol()) {
            state.skip();
            String test = state.pickNext(2);
            Rule discoveredRule = this.grammar.defineRule(test);
            if (discoveredRule == null){
                test = state.pickNext(1);
                discoveredRule = this.grammar.defineRule(test);
            }
            if (discoveredRule == null) {
                throw new RuntimeException("Can't parse " + test);
            }

            Node node = discoveredRule.apply(state, this);
            left = node;
            state.replaceRoot(left);
            /* if (node instanceof IdNode && state.getRoot() == null){
                return node;
            } */
        }
        return left;
    }

    public String extractParam(State state) {
        state.skip();
        if (state.pickNext(1).equals("(")) {
            return extractBracketGroup(state);
        } else if ("W M X F G ".contains(state.pickNext(2))) {
            return extractOpExpr(state);
        } else if (!"-<>!UR&|=+*/!()".contains(state.pickNext(1))){
            return extractId(state);
        } else {
            return extractOpExpr(state);
        }
    }

    private String extractOpExpr(State state) {
        // Unary op
        String opname = state.readNext(1);
        return opname.concat(extractParam(state));
    }

    private String extractId(State state) {
        state.skip();
        String forbiddenChars = "-<>!&|=+*/!() ";
        StringBuilder name = new StringBuilder();
        String next = state.pickNext(1);
        while (!forbiddenChars.contains(next) && !state.isEol()){
            next = state.readNext(1);
            name.append(next);
            next = state.pickNext(1);
        }
        return name.toString();
    }

    private String extractBracketGroup(State state) {
        int opCount = 1;
        state.readNext(1);

        int clCount = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("(");
        String next = "";
        while(opCount != clCount){
            next = state.readNext(1);
            if (next.equals("(")){
                opCount++;
            } else if (next.equals(")")){
                clCount++;
            }
            sb.append(next);
        }
        return sb.toString();
    }


}
