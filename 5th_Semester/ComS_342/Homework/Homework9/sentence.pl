s --> f; t, n, t.
f --> "if", b, "then", s;
    	"if", b, "then", s, "else", s.
b --> t, e, t.

t --> [x]; [y]; [z]; [1]; [0].
e --> [>]; [<].
n --> [-]; [+]; [=].



% Student exercise profile
:- set_prolog_flag(occurs_check, error).        % disallow cyclic terms
:- set_prolog_stack(global, limit(8 000 000)).  % limit term space (8Mb)
:- set_prolog_stack(local,  limit(2 000 000)).  % limit environment space

% Your program goes here


s --> f.
s --> t, n, t.
f --> "if", b, "then", s.
f --> "if", b, "then", s, "else", s.
b --> t, e, t.

t --> [x].
t --> [y].
t --> [z].
t --> [1].
t --> [0].

e --> [>].
e --> [<].

n --> [-].
n --> [+].
n --> [=].




/** <examples> Your example queries go here, e.g.
?- member(X, [cat, mouse]).
*/

