grammar nusmv;

options {
  language = Java;
}

@header {
import java.util.*;
import shakeanapple.backtracker.nusmvparsing.*;
}

@parser::members {
}

WS : (' ' | '\t' | ('\r'? '\n'))+ -> channel(HIDDEN);

INT_CONST : '-'? ('0' | ('1'..'9' ('0'..'9')*));

TRUE : 'TRUE';

FALSE : 'FALSE';

INIT : 'init';

NEXT : 'next';

MODULE : 'MODULE';

ASSIGN : 'ASSIGN';

VAR : 'VAR';

BOOLEAN : 'boolean';

ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

constant
    : INT_CONST | TRUE | FALSE
    ;

composite_id
    : ID ('.' ID)* ('[' INT_CONST ']')*
    ;

unary_operator_sign
    : '!' | '-'
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

binary_operator_sign5
    : '=' | '!=' | '>' | '>=' | '<' | '<='
    ;

type
    : BOOLEAN | (INT_CONST '..' INT_CONST) | ('{' constant (',' constant)* '}')
    ;

////////////////////

atom returns[Expression f]
    : '(' inside=binary_operator1 ')' { $f = $inside.f; }
    | constant { $f = new Constant($constant.text); }
    | composite_id { $f = new Variable($composite_id.text, Variable.ReferenceType.CURRENT); }
    | NEXT '(' composite_id ')' { $f = new Variable($composite_id.text, Variable.ReferenceType.NEXT); }
    ;

// unary !, -
unary_operator returns[Expression f]
    : atom { $f = $atom.f; }
    | unary_operator_sign inside=unary_operator { $f = new UnaryOperator($unary_operator_sign.text, $inside.f); }
    ;

// binary *, /, mod
binary_operator7 returns[Expression f]
    : f1=unary_operator { $f = $f1.f; String op; }
        (('*' { op = "*"; } | '/' { op = "/"; } | 'mod' { op = "mod"; })
      f2=unary_operator { $f = new BinaryOperator(op, $f, $f2.f); })*
    ;

// binary +, -
binary_operator6 returns[Expression f]
    : f1=binary_operator7 { $f = $f1.f; String op; } (('+' { op = "+"; } | '-' { op = "-"; })
      f2=binary_operator7 { $f = new BinaryOperator(op, $f, $f2.f); })*
    ;

// comparisons
binary_operator5 returns[Expression f]
    : f1=binary_operator6 { $f = $f1.f; } (inside=binary_operator_sign5 f2=binary_operator6
        { $f = new BinaryOperator($inside.text, $f, $f2.f); })?
    ;

// &
binary_operator4 returns[Expression f]
    : f1=binary_operator5 { $f = $f1.f; } (inside=binary_operator_sign4 f2=binary_operator5
      { $f = new BinaryOperator($inside.text, $f, $f2.f); })*
    ;

// |, xor, xnor
binary_operator3 returns[Expression f]
    : f1=binary_operator4 { $f = $f1.f; } (inside=binary_operator_sign3 f2=binary_operator4
      { $f = new BinaryOperator($inside.text, $f, $f2.f); })*
    ;

// ? :, no type checking
ternary_operator returns[Expression f]
    : f1=binary_operator3 { $f = $f1.f; } ('?' f2=binary_operator3 ':' f3=binary_operator3
      { $f = new TernaryOperator($f1.f, $f2.f, $f3.f); })?
    ;

// <->
binary_operator2 returns[Expression f]
    : f1=binary_operator3 { $f = $f1.f; } (inside=binary_operator_sign2 f2=binary_operator3
      { $f = new BinaryOperator($inside.text, $f, $f2.f); })*
    ;

// ->
binary_operator1 returns[Expression f]
    : f1=binary_operator2 { $f = $f1.f; } (inside=binary_operator_sign1 f2=binary_operator1
      { $f = new BinaryOperator($inside.text, $f, $f2.f); })?
    ;

assignment returns[Assignment a]
    : { Assignment.Type type; }
      (INIT { type = Assignment.Type.INIT; } | NEXT { type = Assignment.Type.NEXT; })
      '(' left=composite_id ')' ':=' right=binary_operator1 ';'
      { $a = new Assignment(type, new Variable($left.text), $right.f); }
    ;

var_declaration returns[Variable v]
    : composite_id ':' type ';' { $v = new Variable($composite_id.text); }
    ;

module returns[Module m]
    : MODULE ID {
        List<Variable> inputVariables = new ArrayList<>();
        List<Variable> internalVariables = new ArrayList<>();
        List<Assignment> assignments = new ArrayList<>();
      }
      ('(' (id1=composite_id { inputVariables.add(new Variable($id1.text)); }
      (',' id2=composite_id { inputVariables.add(new Variable($id2.text)); })*)? ')')?
      ((ASSIGN (assignment { assignments.add($assignment.a); })*)
      | (VAR (var_declaration { internalVariables.add($var_declaration.v); })*))*
      { $m = new Module($ID.text, inputVariables, internalVariables, assignments); }
    ;
