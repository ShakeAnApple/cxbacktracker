package shakeanapple.backtracker.parser.ltlformula;

import shakeanapple.backtracker.core.ltl.formula.model.tree.FormulaNode;
import shakeanapple.backtracker.core.ltl.formula.model.LtlFormula;
import shakeanapple.backtracker.parser.ltlformula.rule.*;
import shakeanapple.backtracker.parser.ltlformula.tree.Node;

import java.util.HashMap;
import java.util.Map;

public class LtlModelBuilder {

    private final Grammar grammar;

    public LtlModelBuilder() {
        Map<String, Rule> rules = fillRules();
        this.grammar = new Grammar(rules);
    }

    // TODO could be smarter
    private Map<String, Rule> fillRules(){
        Map<String, Rule> rules = new HashMap<>();

        rules.put("U", new BinOpRule());
        rules.put("R", new BinOpRule());
        rules.put("W", new BinOpRule());
        rules.put("M", new BinOpRule());
        rules.put("&", new BinOpRule());
        rules.put("|", new BinOpRule());
        rules.put("->", new BinOpRule());
        rules.put("=", new BinOpRule());
        rules.put("<", new BinOpRule());
        rules.put(">", new BinOpRule());
        rules.put("+", new BinOpRule());
        rules.put("-", new BinOpRule());
        rules.put("*", new BinOpRule());
        rules.put("/", new BinOpRule());
        rules.put("!=", new BinOpRule());
        rules.put("<>", new BinOpRule());
        rules.put("<=", new BinOpRule());
        rules.put(">=", new BinOpRule());

        rules.put("X", new UnOpRule());
        rules.put("F", new UnOpRule());
        rules.put("G", new UnOpRule());
        rules.put("!", new UnOpRule());

        rules.put("(", new BracketGroupRule());

        rules.put("id", new IdRule());

        return rules;
    }

    public LtlFormula complete(String str){
        Parser parser = new Parser(this.grammar);

//        str = str.trim().replace(" ", "");
        str = str.trim();
        Node root = parser.parseExpr(str);
        FormulaNode formulaRoot = root.translate();
        return new LtlFormula(formulaRoot);
    }
}
