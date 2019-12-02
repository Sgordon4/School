likes(dan, sally).
likes(sally, dan).
likes(fuck, off).

dating(X, Y) :-
	likes(X, Y),
	likes(Y, X).
	
friendship(X, Y) :-
	likes(X, Y);
	likes(Y, X).