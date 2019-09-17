grammar ArithLang;

 // Grammar of this Programming Language
 //  - grammar rules start with lowercase
 program returns [Program ast] : 
		e=exp { $ast = new Program($e.ast); }
		;

 exp returns [Exp ast]:
		c=charexp  { $ast = $c.ast; }
        | a=addexp { $ast = $a.ast; }
        | m=multexp { $ast = $m.ast; }
        ;


 charexp returns [CharExp ast]:
        c0=Letter { $ast = new CharExp($c0.text); };



  
 addexp returns [AddExp ast]
        locals [ArrayList<Exp> list]
 		@init { $list = new ArrayList<Exp>(); } :
 		'(' '+'
 		    e=exp { $list.add($e.ast); } 
 		    ( e=exp { $list.add($e.ast); } )+
 		')' { $ast = new AddExp($list); }
 		;

 multexp returns [MultExp ast] 
        locals [ArrayList<Exp> list]
 		@init { $list = new ArrayList<Exp>(); } :
 		'(' '*'
 		    e=exp { $list.add($e.ast); } 
 		    ( e=exp { $list.add($e.ast); } )+ 
 		')' { $ast = new MultExp($list); }
 		;


 // Lexical Specification of this Programming Language
 //  - lexical specification rules start with uppercase

 Define : 'define' ;
 Let : 'let' ;
 Dot : '.' ;

 Letter : LETTER;

 fragment LETTER : ('e' | 'o' | 'u');

 AT : '@';
 ELLIPSIS : '...';
 WS  :  [ \t\r\n\u000C]+ -> skip;
 Comment :   '/*' .*? '*/' -> skip;
 Line_Comment :   '//' ~[\r\n]* -> skip;
