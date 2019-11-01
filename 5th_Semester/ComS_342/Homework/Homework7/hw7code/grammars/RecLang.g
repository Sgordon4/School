grammar RecLang;
 
import FuncLang; //Import all rules from FuncLang grammar.
 
 // New elements in the Grammar of this Programming Language
 //  - grammar rules start with lowercase

 exp returns [Exp ast]: 
		va=varexp { $ast = $va.ast; }
		| num=numexp { $ast = $num.ast; }
		| str=strexp { $ast = $str.ast; }
		| bl=boolexp { $ast = $bl.ast; }
        | add=addexp { $ast = $add.ast; }
        | sub=subexp { $ast = $sub.ast; }
        | mul=multexp { $ast = $mul.ast; }
        | div=divexp { $ast = $div.ast; }
        | let=letexp { $ast = $let.ast; }
        | lam=lambdaexp { $ast = $lam.ast; }
        | call=callexp { $ast = $call.ast; }
        | i=ifexp { $ast = $i.ast; }
        | less=lessexp { $ast = $less.ast; }
        | eq=equalexp { $ast = $eq.ast; }
        | gt=greaterexp { $ast = $gt.ast; }
        | car=carexp { $ast = $car.ast; }
        | cdr=cdrexp { $ast = $cdr.ast; }
        | cons=consexp { $ast = $cons.ast; }
        | list=listexp { $ast = $list.ast; }
        | nl=nullexp { $ast = $nl.ast; }
        | lrec=letrecexp { $ast = $lrec.ast; }
        ;
 
 letrecexp returns [LetrecExp ast] 
        locals [ArrayList<String> ids = new ArrayList<String>(), ArrayList<Exp> funs = new ArrayList<Exp>(); ] :
 		'(' Letrec 
 			'(' ( '(' id=Identifier fun=exp ')' { $ids.add($id.text); $funs.add($fun.ast); } )+  ')'
 			body=exp 
 		')' { $ast = new LetrecExp($ids, $funs, $body.ast); }
 		;
