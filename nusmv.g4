grammar nusmv;

options {
  language = Java;
}

@header {
import java.util.*;
import shakeanapple.backtracker.nusmvparsing.*;
import shakeanapple.backtracker.nusmvparsing.expression.*;
}

@parser::members {
}

WS : (' ' | '\t' | ('\r'? '\n'))+ -> channel(HIDDEN);

LINE_COMMENT : ('--' ~( '\r' | '\n' )*)+ -> channel(HIDDEN);

INT_CONST : '-'? ('0' | ('1'..'9' ('0'..'9')*));

TRUE : 'TRUE'; FALSE : 'FALSE';

INIT : 'init'; NEXT : 'next';

MODULE : 'MODULE'; VAR : 'VAR'; ASSIGN : 'ASSIGN'; DEFINE : 'DEFINE';

BOOLEAN : 'boolean';

COUNT : 'count'; MOD : 'mod'; XOR : 'xor'; XNOR : 'xnor';

CASE : 'case'; ESAC : 'esac';

ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

constant
    : INT_CONST | TRUE | FALSE
    ;

composite_id
    : ID ('.' ID)* ('[' INT_CONST ']')*
    ;

////////////////////

atom returns[Expression f]
    : '(' inside=binary_operator1 ')' { $f = $inside.f; }
    | constant { $f = new Constant($constant.text); }
    | composite_id { $f = new Variable($composite_id.text, false); }
    | NEXT '(' composite_id ')' { $f = new Variable($composite_id.text, true); }
    | CASE {
        List<Expression> conditions = new ArrayList<>();
        List<Expression> options = new ArrayList<>();
     } (c=binary_operator1 { conditions.add($c.f); } ':' o=binary_operator1 { options.add($o.f); } ';')+ ESAC
     { $f = new CaseOperator(conditions, options); }
    | COUNT {
        List<Expression> arguments = new ArrayList<>();
     } '(' a1=binary_operator1 { arguments.add($a1.f); } (',' a2=binary_operator1 { arguments.add($a2.f); })*')'
     { $f = new CountOperator(arguments); }
    ;

unary_operator_sign : '!' | '-';
unary_operator returns[Expression f]
    : atom { $f = $atom.f; }
    | unary_operator_sign inside=unary_operator { $f = new UnaryOperator($unary_operator_sign.text, $inside.f); }
    ;

// binary *, /, mod
binary_operator7 returns[Expression f]
    : f1=unary_operator { $f = $f1.f; String op; }
        (('*' { op = "*"; } | '/' { op = "/"; } | MOD { op = "mod"; })
      f2=unary_operator { $f = new BinaryOperator(op, $f, $f2.f); })*
    ;

// binary +, -
binary_operator6 returns[Expression f]
    : f1=binary_operator7 { $f = $f1.f; String op; } (('+' { op = "+"; } | '-' { op = "-"; })
      f2=binary_operator7 { $f = new BinaryOperator(op, $f, $f2.f); })*
    ;

binary_operator_sign5 : '=' | '!=' | '>' | '>=' | '<' | '<=';
binary_operator5 returns[Expression f]
    : f1=binary_operator6 { $f = $f1.f; } (inside=binary_operator_sign5 f2=binary_operator6
        { $f = new BinaryOperator($inside.text, $f, $f2.f); })?
    ;

binary_operator_sign4 : '&';
binary_operator4 returns[Expression f]
    : f1=binary_operator5 { $f = $f1.f; } (inside=binary_operator_sign4 f2=binary_operator5
      { $f = new BinaryOperator($inside.text, $f, $f2.f); })*
    ;

binary_operator_sign3 : '|' | XOR | XNOR;
binary_operator3 returns[Expression f]
    : f1=binary_operator4 { $f = $f1.f; } (inside=binary_operator_sign3 f2=binary_operator4
      { $f = new BinaryOperator($inside.text, $f, $f2.f); })*
    ;

// ? :, no type checking
ternary_operator returns[Expression f]
    : f1=binary_operator3 { $f = $f1.f; } ('?' f2=binary_operator3 ':' f3=binary_operator3
      { $f = CaseOperator.ternaryOperator($f1.f, $f2.f, $f3.f); })?
    ;

binary_operator_sign2 : '<->';
binary_operator2 returns[Expression f]
    : f1=ternary_operator { $f = $f1.f; } (inside=binary_operator_sign2 f2=ternary_operator
      { $f = new BinaryOperator($inside.text, $f, $f2.f); })*
    ;

binary_operator_sign1 : '->';
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

type : BOOLEAN | (INT_CONST '..' INT_CONST) | ('{' constant (',' constant)* '}');
internal_var_declaration returns[Variable v]
    : composite_id ':' type { $v = new Variable($composite_id.text, $type.text); }
    ;

input_var_declaration returns[Variable v]
    : typeless=composite_id { $v = new Variable($typeless.text); }
    | typeful=internal_var_declaration { $v = $typeful.v; }
    ;

module returns[NuSMVModule m]
    : MODULE ID {
        List<Variable> inputVariables = new ArrayList<>();
        List<Variable> internalVariables = new ArrayList<>();
        List<Assignment> assignments = new ArrayList<>();
      }
      ('(' (v1=input_var_declaration { inputVariables.add($v1.v); }
      (',' v2=input_var_declaration { inputVariables.add($v2.v); })*)? ')')?
      ((ASSIGN (assignment { assignments.add($assignment.a); })*)
      | (DEFINE (composite_id ':=' binary_operator1 ';')*) // TODO add support
      | (VAR (internal_var_declaration ';' { internalVariables.add($internal_var_declaration.v); })*))*
      { $m = new NuSMVModule($ID.text, inputVariables, internalVariables, assignments); }
    ;
