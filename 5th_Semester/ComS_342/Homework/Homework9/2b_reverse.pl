rev(L, Res) :-
    revHelper(L, [], Res).

revHelper([], Accum, Accum).
revHelper([H|T], Accum, Res) :-
    number(H),
    revHelper(T, [H|Accum], Res);
    
    revHelper(H, [], Temp),
    revHelper(T, [Temp|Accum], Res).
