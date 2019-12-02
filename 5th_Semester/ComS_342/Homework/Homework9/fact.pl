fact(0, 1).
fact(N, F) :-
	N > 0,
	N1 is N-1,
	fact(N1, F1),
	F is N*F1.
	
	
	
	
rev(L, Res) :-
    revHelper(L, [], Res).

revHelper([], Accum, Accum).
revHelper([H|T], Accum, Res) :-
    number(H),
    revHelper(T, [H|Accum], Res);
    
    revHelper(H, [], Temp),
    revHelper(T, [Temp|Accum], Res).