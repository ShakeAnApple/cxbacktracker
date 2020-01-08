grammar ltl;

options {
  language = Java;
}

@header {
import shakeanapple.backtracker.parser.ltlformula.recent.tree.*;
}

@parser::members {
}

WS : (' ' | '\t' | ('\r'? '\n'))+ -> channel(HIDDEN);

INT_CONST : '-'? ('0' | ('1'..'9' ('0'..'9')*));

TRUE : 'TRUE';

FALSE : 'FALSE';

COUNT : 'count';

ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

constant
    : INT_CONST | TRUE | FALSE
    ;

composite_id
    : ID ('.' ID)* ('[' INT_CONST ']')*
    ;

unary_operator_sign
    : '!' | 'X' | 'G' | 'F' | 'Y' | 'Z' | 'O' | 'H'
    ;

binary_operator_sign5
    : 'U' | 'V'
    ;

binary_operator_sign4
    : '&'
    ;

binary_operator_sign3
    : '|' | 'xnor' | 'xor'
    ;

binary_operator_sign2
    : '<->'
    ;

binary_operator_sign1
    : '->'
    ;

comparison_operator_sign
    : '=' | '!=' | '>' | '>=' | '<' | '<='
    ;

////////////////////

arithmetic_atomic_value returns[Node f]
    : constant { $f = new IdNode($constant.text); }
    | composite_id { $f = new IdNode($composite_id.text); }
    ;

arithmetic_atom returns[Node f]
    : arithmetic_atomic_value { $f = $arithmetic_atomic_value.f; }
    | '(' implies_arithmetic_expression ')' { $f = $implies_arithmetic_expression.f; }
    ;

arithmetic_expression3 returns[Node f]
    : arithmetic_atom { $f = $arithmetic_atom.f; }
    | { String op; } ('-' { op = "-"; } | '+' { op = "+"; } | '!' { op = "!"; }) arithmetic_expression3
      { $f = new UnOpNode($arithmetic_expression3.f, op); }
    ;

arithmetic_expression2 returns[Node f]
    : f1=arithmetic_expression3 { $f = $f1.f; String op; }
        (('*' { op = "*"; } | '/' { op = "/"; } | 'mod' { op = "mod"; })
      f2=arithmetic_expression3 { $f = new BinOpNode($f, $f2.f, op); })*
    ;

arithmetic_expression1 returns[Node f]
    : f1=arithmetic_expression2 { $f = $f1.f; String op; } (('+' { op = "+"; } | '-' { op = "-"; })
      f2=arithmetic_expression2 { $f = new BinOpNode($f, $f2.f, op); })*
    ;

comparison_expression returns[Node f]
    : f1=arithmetic_expression1 { $f = $f1.f; } (comparison_operator_sign f2=arithmetic_expression1
        { $f = new BinOpNode($f, $f2.f, $comparison_operator_sign.text); })?
    ;

////////////////////

and_arithmetic_expression returns[Node f]
    : f1=comparison_expression { $f = $f1.f; } ('&' f2=comparison_expression
        { $f = new BinOpNode($f, $f2.f, "&"); })?
    ;

or_arithmetic_expression returns[Node f]
    : f1=and_arithmetic_expression { $f = $f1.f; String op; }
        (('|' { op = "|"; } | 'xor' { op = "xor"; } | 'xnor' { op = "xnor"; })
        f2=and_arithmetic_expression { $f = new BinOpNode($f, $f2.f, op); })?
    ;

//ternary_arithmetic_expression returns[ArithmeticExpression f]
//    : f1=or_arithmetic_expression { $f = $f1.f; } ('?' f2=or_arithmetic_expression ':' f3=or_arithmetic_expression
//      { $f = new TernaryArithmeticOperator($f1.f, $f2.f, $f3.f); })?
//    ;

eq_arithmetic_expression returns[Node f]
    : f1=or_arithmetic_expression { $f = $f1.f; } ('<->' f2=or_arithmetic_expression
        { $f = new BinOpNode($f, $f2.f, "<->"); })?
    ;

implies_arithmetic_expression returns[Node f]
    : f1=eq_arithmetic_expression { $f = $f1.f; } ('->' f2=implies_arithmetic_expression
        { $f = new BinOpNode( $f, $f2.f, "->"); })?
    ;

////////////////////

proposition returns[Node f]
    : f1=arithmetic_expression1 comparison_operator_sign f2=arithmetic_expression1
        { $f = new BinOpNode($f1.f, $f2.f, $comparison_operator_sign.text); }
//    | o1=or_arithmetic_expression '?' o2=or_arithmetic_expression ':' o3=or_arithmetic_expression
//        { $f = new TernaryArithmeticOperator($o1.f, $o2.f, $o3.f); }
    | arithmetic_atomic_value { $f = $arithmetic_atomic_value.f; }
    ;

atom returns[Node f]
    : '(' formula ')' { $f = $formula.f; }
    | proposition { $f = $proposition.f; }
    ;

unary_operator returns[Node f]
    : atom { $f = $atom.f; }
    | unary_operator_sign unary_operator { $f = new UnOpNode($unary_operator.f, $unary_operator_sign.text); }
    ;

binary_operator5 returns[Node f]
    : f1=unary_operator { $f = $f1.f; } (binary_operator_sign5 f2=unary_operator
      { $f = new BinOpNode($f, $f2.f, $binary_operator_sign5.text); })*
    ;

binary_operator4 returns[Node f]
    : f1=binary_operator5 { $f = $f1.f; } (binary_operator_sign4 f2=binary_operator5
      { $f = new BinOpNode($f, $f2.f, $binary_operator_sign4.text); })*
    ;

binary_operator3 returns[Node f]
    : f1=binary_operator4 { $f = $f1.f; } (binary_operator_sign3 f2=binary_operator4
      { $f = new BinOpNode($f, $f2.f, $binary_operator_sign3.text); })*
    ;

binary_operator2 returns[Node f]
    : f1=binary_operator3 { $f = $f1.f; } (binary_operator_sign2 f2=binary_operator3
      { $f = new BinOpNode($f, $f2.f, $binary_operator_sign2.text); })*
    ;

binary_operator1 returns[Node f]
    : f1=binary_operator2 { $f = $f1.f; } (binary_operator_sign1 f2=binary_operator1
      { $f = new BinOpNode($f, $f2.f, $binary_operator_sign1.text); })?
    ;

formula returns[Node f]
    : binary_operator1 { $f = $binary_operator1.f; } EOF
    ;
