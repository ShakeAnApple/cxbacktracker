package shakeanapple.backtracker.parser.ltlformula.old;

import shakeanapple.backtracker.parser.ltlformula.old.rule.Rule;

import java.util.Map;

public class Grammar {
    Map<String, Rule> _rules;

    public Grammar(Map<String, Rule> rules) {
        _rules = rules;
    }

    public Rule defineRule(String str){
        if (!_rules.containsKey(str)){
            if (!"-<>!URWM&|=+*/XFG!()".contains(str.substring(0,1))){
                return _rules.get("id");
            }
            return null;
        }
        return _rules.get(str);
    }
}
