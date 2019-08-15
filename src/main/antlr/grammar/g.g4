grammar g;

@lexer::header {

}

@parser::header {
}

add
    :    NUMBER PLUS NUMBER
    ;

NUMBER
    :    ('0'..'9')+
    ;

PLUS
    :    ('+')
    ;
