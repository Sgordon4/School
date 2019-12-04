% House: [Color, Man, Pet, Drink, Cig]
next(A,B,L):-nextto(A,B,L) ; nextto(B,A,L).
write_list(L):-forall(member(Mem,L),(write(Mem),nl)).

start(Houses):- length(Houses,5),
    member([red,english,_,_,_],Houses),
	member([_,spaniard,dog,_,_],Houses),
    member([green,_,_,coffee,_],Houses),
    member([_,ukranian,_,tea,_],Houses),
    
    next([ivory,_,_,_,_],[green,_,_,_,_], Houses),
    
    member([_,_,snails,_,oldGold],Houses),
    member([yellow,_,_,_,kools],Houses),
    
    Houses= [_,_,[_,_,_,milk,_],_,_],
    Houses= [[_,norwegian,_,_,_],_,_,_,_],
    
    next([_,_,_,_,camel],[_,_,fox,_,_], Houses),
    next([_,_,_,_,kools],[_,_,horse,_,_], Houses),
    
    member([_,_,_,orangeJuice,luckyStrike],Houses),
    member([_,japanese,_,_,parliaments],Houses),
    
    next([_,norwegian,_,_,_],[blue,_,_,_,_], Houses),
    
    member([_,_,_,water,_],Houses),
    member([_,_,zebra,_,_],Houses),
    
	write_list(Houses).