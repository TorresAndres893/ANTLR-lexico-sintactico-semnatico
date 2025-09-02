grammar Gramatica_Cal;

prog:   stat+ ;

stat
    : expr NEWLINE      #printExpr
    | NEWLINE           #blank
    ;

// expresiones permitidas
expr
    :   expr op=(MUL|DIV) expr      #MulDiv
    |   expr op=(ADD|SUB) expr      #AddSub
    |   atom FACT                   #FactorialExpr   // <-- aquí el cambio
    |   atom                        #ToAtom
    ;

atom
    :   SUB atom                    #UnaryMinus
    |   INT                         #Int
    |   DOUBLE                      #Double
    |   '(' expr ')'                #Parens
    |   SIN '(' expr ')'            #SinExpr
    |   COS '(' expr ')'            #CosExpr
    |   TAN '(' expr ')'            #TanExpr
    |   SQRT '(' expr ')'           #SqrtExpr
    |   LN '(' expr ')'             #LnExpr
    |   LOG '(' expr ')'            #LogExpr
    ;

// funciones
SIN  : 'sin' ;
COS  : 'cos' ;
TAN  : 'tan' ;
SQRT : 'sqrt' ;
LN   : 'ln' ;
LOG  : 'log' ;

// operadores
MUL  : '*' ;
DIV  : '/' ;
ADD  : '+' ;
SUB  : '-' ;
FACT : '!' ;

// literales
DOUBLE  : [0-9]+ '.' [0-9]* ;
INT     : [0-9]+ ;

NEWLINE : '\r'? '\n' ;
WS      : [ \t]+ -> skip ;
