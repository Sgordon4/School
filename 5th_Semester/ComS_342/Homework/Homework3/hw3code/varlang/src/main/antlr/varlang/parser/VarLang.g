grammar VarLang;

import ArithLang; //Import all rules from Arithlang grammar.
 
 // New elements in the Grammar of this Programming Language
 //  - grammar rules start with lowercase

 exp returns [Exp ast]: 
		v=varexp { $ast = $v.ast; }
		| b=decexp { $ast = $b.ast; }
		| n=numexp { $ast = $n.ast; }
        | a=addexp { $ast = $a.ast; }
        | s=subexp { $ast = $s.ast; }
        | m=multexp { $ast = $m.ast; }
        | d=divexp { $ast = $d.ast; }
        | l=letexp { $ast = $l.ast; }
        | c=constexp { $ast = $c.ast; }
        | le=leteexp { $ast = $le.ast; }
        ;

 varexp returns [VarExp ast]:
 		id=Identifier { $ast = new VarExp($id.text); }
 		;
 /*
 decexp returns [DecExp ast]:
  		id=Identifier key=Number { $ast = new DecExp(Integer.parseInt($key.text), $id.text); }
  		;
  		*/

 decexp returns [DecExp ast]:
         '(' Dec (num=Number id=Identifier)
         ')' { $ast = new DecExp(Integer.parseInt($num.text), $id.text); }
         ;

 letexp  returns [LetExp ast]
        locals [ArrayList<String> names, ArrayList<Exp> value_exps]
 		@init { $names = new ArrayList<String>(); $value_exps = new ArrayList<Exp>(); } :
 		'(' Let
 			'(' ( '(' id=Identifier e=exp ')' { $names.add($id.text); $value_exps.add($e.ast); } )+  ')'
 			body=exp
 			')' { $ast = new LetExp($names, $value_exps, $body.ast); }
 		;

 constexp returns [ConstExp ast]:
        '(' Define (id=Identifier num=Number)
        ')' { $ast = new ConstExp($id.text, Integer.parseInt($num.text)); }
        ;

 leteexp  returns [LetExp ast]
         locals [ArrayList<String> names, ArrayList<Exp> value_exps]
  		@init2 { $names = new ArrayList<String>(); $value_exps = new ArrayList<Exp>(); } :
  		'(' Lete
  			'(' ( '(' id=Identifier e=exp ')' { $names.add($id.text); $value_exps.add($e.ast); } )+  ')'
  			body=exp
  			')' { $ast = new LetExp($names, $value_exps, $body.ast); }
  		;

 // Lexical Specification of this Programming Language
 //  - lexical specification rules start with uppercase

Lete : 'lete' ;
Dec : 'dec' ;